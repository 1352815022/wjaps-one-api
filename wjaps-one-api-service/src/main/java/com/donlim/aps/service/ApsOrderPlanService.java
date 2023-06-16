package com.donlim.aps.service;

import cn.hutool.extra.spring.SpringUtil;
import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.dto.serach.SearchFilter;
import com.changhong.sei.core.log.LogUtil;
import com.changhong.sei.core.service.BaseEntityService;
import com.changhong.sei.exception.ServiceException;
import com.donlim.aps.dao.*;
import com.donlim.aps.dto.ApsOrderDto;
import com.donlim.aps.dto.CalcByCapactityDto;
import com.donlim.aps.dto.OrderStatusType;
import com.donlim.aps.dto.pull.ModifyFinishQtyParamDto;
import com.donlim.aps.entity.*;
import com.donlim.aps.util.ReflectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 生产计划表(内部)(ApsOrderPlan)业务逻辑实现类
 *
 * @author sei
 * @since 2022-05-11 15:12:30
 */
@Service
public class ApsOrderPlanService extends BaseEntityService<ApsOrderPlan> {
   @Autowired
   private ApsOrderDao apsOrderDao;
    @Autowired
    private ApsOrderPlanDao dao;
    @Autowired
    private U9MaterialDao materialDao;
    @Autowired
    private ApsOrderPlanDetailDao apsOrderPlanDetailDao;
    @Autowired
    private ApsHolidayDao apsHolidayDao;
    @Autowired
    private ApsOrderService apsOrderService;
    @Autowired
    private ApsProductionProcessScheduleDao apsProductionProcessScheduleDao;
    @Autowired
    private McasProcessDao mcasProcessDao;
    @Autowired
    private McasYieldDao mcasYieldDao;
    @Autowired
    private U9MoFinishDao u9MoFinishDao;
    @Autowired
    private U9ProduceOrderDao u9ProduceOrderDao;


    @Override
    protected BaseEntityDao<ApsOrderPlan> getDao() {
        return dao;
    }

    //解决事务失效
    private ApsOrderPlanService getSelfService(){
        Class<? extends ApsOrderPlanService> aClass = this.getClass();
        return SpringUtil.getBean(aClass);   //SpringUtil工具类见下面代码
    }

    private static BigDecimal maxCapactity = new BigDecimal(999999);

    /**
     * 修改排产单状态
     *
     * @param apsOrder
     */
    public void changeStatus(ApsOrder apsOrder) {
        List<ApsOrderPlan> plans = dao.findByOrderId(apsOrder.getId());
        for (ApsOrderPlan plan : plans) {
            plan.setStatus(apsOrder.getStatus());
        }
        this.save(plans);
    }

    /**
     * 根据工单号获排产计划
     *
     * @param orderList
     * @return
     */
    public List<ApsOrderPlan> getOrderByOrderNo(List<String> orderList) {
        return dao.findByOrderNo(orderList);
    }


    /**
     * 分页查询，携带排产信息
     *
     * @param search 过滤参数
     * @param cols   显示排产日期数 从当天开始往后推
     * @return PageResult
     */
    @Transactional
    public PageResult findPlanByPage(Search search, Integer cols) {
        LocalDate startDate = LocalDate.now().plusDays(-1);
        search.addFilter(new SearchFilter("startDate", startDate, SearchFilter.Operator.GE));
        search.addFilter(new SearchFilter("endDate", startDate.plusDays(cols + 15), SearchFilter.Operator.LE));
        PageResult<ApsOrderPlan> plans = this.findByPage(search);
        ArrayList<ApsOrderPlan> plansRows = plans.getRows();
        List<Map> colsRows = new ArrayList<>();
        for (ApsOrderPlan plan : plansRows) {
            Map<String, Object> map = toMapDto(plan);
            List<ApsOrderPlanDetail> orderPlanDetails = plan.getOrderPlanDetails();
            for (ApsOrderPlanDetail detail : orderPlanDetails) {
                LocalDate planDate = detail.getPlanDate();
                map.put(planDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), detail.getPlanQty());
            }
            BigDecimal finishQty = plan.getOrder().getFinishQty();
            map.put("hasQty",finishQty);
            map.remove("orderPlanDetails");
            colsRows.add(map);
        }
        PageResult result = new PageResult(plans);
        result.setRows(colsRows);
        return result;
    }

    /**
     * 1.根据车间、班组
     * 2.无记录则下达至当天 ，有则根据产能追加
     * 3.下达日期无记录则取物料产能的数量，有则计算 产能负荷比
     * 4.跳过节假日
     * * 如 ：
     * * 一、 料A产能1000 | 7.4 A 1000
     * * 二、 料A产能1000 料B产能 1200 | 7.4 A 800 800/1000=80% | 7.4 B  240 1200*20% = 240
     *
     * @param apsOrderDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void orderPlan(ApsOrderDto apsOrderDto) throws Exception {
        boolean flag = false;
        //获取料品产能
        Optional<U9Material> capacityOptional = materialDao.findByCode(apsOrderDto.getMaterialCode());
        if (capacityOptional.get().getCapacity() == null || capacityOptional.get().getCapacity().compareTo(BigDecimal.ZERO) == 0) {
            apsOrderDto.setCapacity(maxCapactity);
        } else {
            apsOrderDto.setCapacity(capacityOptional.get().getCapacity());
        }
        List<ApsOrderPlanDetail> details = new ArrayList<>();
        LocalDate startDate = LocalDate.now();
        BigDecimal planQty = apsOrderDto.getPlanQty();
        // 按日期下达模式
        if ("byDate".equals(apsOrderDto.getPlanTypeCode())) {
            ApsOrderPlanDetail detail = new ApsOrderPlanDetail();
            detail.setPlanDate(startDate);
            detail.setPlanQty(planQty);
            details.add(detail);
        } else if ("byCapacity".equals(apsOrderDto.getPlanTypeCode())) {
            // 按产能下达模式不指定日期下达
            if (apsOrderDto.getPlanStartDate() == null) {
                CalcByCapactityDto calcByCapactityDto = byCapacity(apsOrderDto);
                apsOrderDto.setPlanStartDate(calcByCapactityDto.getStartDate());
                details = calcByCapactityDto.getApsOrderPlanDetailList();
            } else {
                //插单，按产能下达模式并且指定日期下达，如遇到已经排后的，原排产顺延
                CalcByCapactityDto calcByCapactityDto = insertPlanOrderByCapacity(apsOrderDto);
                details = calcByCapactityDto.getApsOrderPlanDetailList();
                //oldDetails=calcByCapactityDto.getOldPlanList();
                flag = true;
            }
        }
        if (details.size() == 0) {
            throw new ServiceException("单号" + apsOrderDto.getOrderNo() + "数据异常，请重新下达！！");
        }
        String planId = savePlan(apsOrderDto, details);
        //更新挪到后面的计划
        if (flag) {
            uPdateOldPlan(apsOrderDto, details, planId);
        }

    }

    /**
     * 保存排产计划
     *
     * @param details 重排的计划
     * @param details
     */
    private void savePlanDelay(List<ApsOrderPlanDetail> details) {
        ApsOrderPlan apsOrderPlan = dao.getOne(details.get(0).getPlanId());
        //计算计划结束日期
        ApsOrderPlanDetail maxDetail = details.stream().max(Comparator.comparing(ApsOrderPlanDetail::getPlanDate)).get();
        apsOrderPlan.setEndDate(maxDetail.getPlanDate());
        apsOrderPlan = dao.save(apsOrderPlan);
        apsOrderPlanDetailDao.save(details);
        apsOrderService.updateOrderStartDateAndEndDate(apsOrderPlan);
    }

    /**
     * 保存排产计划
     *
     * @param apsOrderDto
     * @param details
     */
    private String savePlan(ApsOrderDto apsOrderDto, List<ApsOrderPlanDetail> details) {
        //取出已经存在的
        List<ApsOrderPlanDetail> existsPlan = apsOrderPlanDetailDao.queryPlanByOrderNo(apsOrderDto.getOrderNo());
        ApsOrderPlan apsOrderPlan = new ApsOrderPlan();
        if (existsPlan.size() > 0) {
            apsOrderPlan.setId(existsPlan.get(0).getPlanId());
        }
        apsOrderPlan.setOrderId(apsOrderDto.getId());
        apsOrderPlan.setMaterialId(apsOrderDto.getMaterialId());
        Optional<U9Material> materialDaoById = materialDao.findById(apsOrderDto.getMaterialId() + "");
        if (materialDaoById.isPresent()) {
            apsOrderPlan.setMaterialCode(materialDaoById.get().getCode());
            apsOrderPlan.setMaterialName(materialDaoById.get().getName());
            apsOrderPlan.setMaterialSpec(materialDaoById.get().getSpec());
            apsOrderPlan.setWorkGroupId(apsOrderDto.getWorkGroupId());
            apsOrderPlan.setWorkGroupName(apsOrderDto.getWorkGroupName());
            apsOrderPlan.setStatus(OrderStatusType.Normal);
            apsOrderPlan.setLineId(apsOrderDto.getWorkLineId());
            apsOrderPlan.setLineName(apsOrderDto.getWorkLineName());
            apsOrderPlan.setOrderDate(apsOrderDto.getOrderDate());
            Optional<BigDecimal> existsPlanQtyOptional = existsPlan.stream().map(a -> a.getPlanQty()).reduce(BigDecimal::add);
            BigDecimal existsPlanQty = existsPlanQtyOptional.orElse(BigDecimal.ZERO);
            apsOrderPlan.setPlanQty(apsOrderDto.getPlanQty().add(existsPlanQty));
            apsOrderPlan.setAwaitQty(apsOrderDto.getOrderQty().subtract(apsOrderPlan.getPlanQty()));
            apsOrderPlan.setScmDeliveryDate(apsOrderDto.getDeliveryStartDate());
            apsOrderPlan.setStandardQty(apsOrderDto.getCapacity());
            apsOrderPlan.setStartDate(apsOrderDto.getPlanStartDate());
        } else {
            throw new ServiceException("单号" + apsOrderDto.getOrderNo() + "数据异常，请重新下达！！");
        }
        //计算计划结束日期
        List<ApsOrderPlanDetail> clac = new ArrayList<>();
        clac.addAll(details);
        clac.addAll(existsPlan);
        ApsOrderPlanDetail maxDetail = clac.stream().max(Comparator.comparing(ApsOrderPlanDetail::getPlanDate)).get();
        ApsOrderPlanDetail minDetail = clac.stream().min(Comparator.comparing(ApsOrderPlanDetail::getPlanDate)).get();
        apsOrderPlan.setEndDate(maxDetail.getPlanDate());
        apsOrderPlan.setStartDate(minDetail.getPlanDate());
        apsOrderPlan = dao.save(apsOrderPlan);
        for (ApsOrderPlanDetail detail : details) {
            detail.setPlanId(apsOrderPlan.getId());
        }
        apsOrderPlanDetailDao.save(details);

        //下达数量=订单数，已下达

        apsOrderService.updateOrder(apsOrderDto);
        return apsOrderPlan.getId();
    }

    /**
     * 保存排产计划
     *
     * @param apsOrderDto
     * @param details
     */
    private void updatePlan(ApsOrderDto apsOrderDto, List<ApsOrderPlanDetail> details) {
        ApsOrderPlan apsOrderPlan = new ApsOrderPlan();
        apsOrderPlan.setOrderId(apsOrderDto.getId());
        apsOrderPlan.setMaterialId(apsOrderDto.getMaterialId());
        Optional<U9Material> materialDaoById = materialDao.findById(apsOrderDto.getMaterialId() + "");
        if (materialDaoById.isPresent()) {
            apsOrderPlan.setMaterialCode(materialDaoById.get().getCode());
            apsOrderPlan.setMaterialName(materialDaoById.get().getName());
            apsOrderPlan.setMaterialSpec(materialDaoById.get().getSpec());
            apsOrderPlan.setWorkGroupId(apsOrderDto.getWorkGroupId());
            apsOrderPlan.setWorkGroupName(apsOrderDto.getWorkGroupName());
            apsOrderPlan.setStatus(OrderStatusType.Normal);
            apsOrderPlan.setLineId(apsOrderDto.getWorkLineId());
            apsOrderPlan.setLineName(apsOrderDto.getWorkLineName());
            apsOrderPlan.setOrderDate(apsOrderDto.getOrderDate());
            apsOrderPlan.setPlanQty(apsOrderDto.getPlanQty());
            apsOrderPlan.setAwaitQty(BigDecimal.ZERO);
            apsOrderPlan.setScmDeliveryDate(apsOrderDto.getDeliveryStartDate());
            apsOrderPlan.setStandardQty(apsOrderDto.getCapacity());
            apsOrderPlan.setStartDate(apsOrderDto.getPlanStartDate());
        } else {
            throw new ServiceException("单号" + apsOrderDto.getOrderNo() + "数据异常，请重新下达！！");
        }
        //计算计划结束日期
        if (details.size() > 0) {
            ApsOrderPlanDetail maxDetail = details.stream().max(Comparator.comparing(ApsOrderPlanDetail::getPlanDate)).get();
            apsOrderPlan.setEndDate(maxDetail.getPlanDate());
            apsOrderPlan = dao.save(apsOrderPlan);
            for (ApsOrderPlanDetail detail : details) {
                detail.setPlanId(apsOrderPlan.getId());
            }
            apsOrderPlanDetailDao.save(details);
            apsOrderService.updateOrderStartDateAndEndDate(apsOrderPlan);
        }

    }


    /**
     * 按产能计算排产
     *
     * @param apsOrderDto
     * @return
     */
    private CalcByCapactityDto byCapacity(ApsOrderDto apsOrderDto) {
        CalcByCapactityDto calcByCapactityDto = new CalcByCapactityDto();
        LocalDate startDate;
        BigDecimal planQty = apsOrderDto.getPlanQty();
        List<ApsOrderPlanDetail> details = new ArrayList<>();
        List<ApsOrderPlanDetail> lastPlans = apsOrderPlanDetailDao.queryLastRecordByWorkGroupAndMaterialCode(apsOrderDto.getWorkGroupId(), apsOrderDto.getMaterialCode());
        ApsOrderPlanDetail lastRecord = lastPlans.size() > 0 ? lastPlans.get(0) : null;
        if (lastRecord == null) {
            startDate = LocalDate.now();
            List<ApsHoliday> holidayList = apsHolidayDao.findByFilter(new SearchFilter("endDate", startDate, SearchFilter.Operator.LE));
            details = calcDetail(apsOrderDto.getPlanQty(), apsOrderDto.getCapacity(), startDate, holidayList);
        } else {
            // 计算当日剩余产能
            Optional<BigDecimal> allLastDayCapacityOptional = lastPlans.stream().filter(o -> o.getPlanDate().equals(lastRecord.getPlanDate())).map(o -> o.getPlanQty()).reduce(BigDecimal::add);
            BigDecimal allLastDayCapacity = allLastDayCapacityOptional.orElse(BigDecimal.ZERO);
            //判断日期是否早过当天，小于当前日期排到当天
            if (lastRecord.getPlanDate().isBefore(LocalDate.now())) {
                startDate = LocalDate.now();
            } else {
                startDate = lastRecord.getPlanDate();
            }
            List<ApsHoliday> holidayList = apsHolidayDao.findByFilter(new SearchFilter("endDate", startDate, SearchFilter.Operator.LE));
            // 当日生产还剩余产能
            if (allLastDayCapacity.compareTo(BigDecimal.ZERO) > 0) {
                BigDecimal dayRemainQty = apsOrderDto.getCapacity().subtract(allLastDayCapacity);
                if (dayRemainQty.compareTo(BigDecimal.ZERO) >= 0) {
                    ApsOrderPlanDetail detail = new ApsOrderPlanDetail();
                    detail.setPlanDate(calcPlanDate(startDate, holidayList));
                    if (planQty.compareTo(dayRemainQty) >= 0) {
                        detail.setPlanQty(dayRemainQty);
                        planQty = planQty.subtract(dayRemainQty);
                    } else {
                        detail.setPlanQty(planQty);
                        planQty = BigDecimal.ZERO;
                    }
                    details.add(detail);
                }
            }
            if (planQty.compareTo(BigDecimal.ZERO) > 0) {
                details = calcDetail(planQty, apsOrderDto.getCapacity(), startDate.plusDays(1), holidayList);
            }
        }
        calcByCapactityDto.setApsOrderPlanDetailList(details);
        calcByCapactityDto.setStartDate(startDate);
        calcByCapactityDto.setStandardQty(apsOrderDto.getCapacity());
        return calcByCapactityDto;
    }

    /**
     * 按产能计算生产计划
     *
     * @param apsOrderDto 要下达的计划
     * @param keepDetails 维持的计划
     * @return
     */
    private CalcByCapactityDto insertPlanOrderByCapacityDelay(ApsOrderDto apsOrderDto, List<ApsOrderPlanDetail> keepDetails) {
        CalcByCapactityDto calcByCapactityDto = new CalcByCapactityDto();
        LocalDate startDate = apsOrderDto.getPlanStartDate();
        BigDecimal planQty = apsOrderDto.getPlanQty();
        List<ApsHoliday> holidayList = apsHolidayDao.findByFilter(new SearchFilter("endDate", startDate, SearchFilter.Operator.LE));
        List<ApsOrderPlanDetail> details = calcDetailDelay(keepDetails, planQty, apsOrderDto, startDate, holidayList);
        calcByCapactityDto.setApsOrderPlanDetailList(details);
        calcByCapactityDto.setStandardQty(apsOrderDto.getCapacity());
        return calcByCapactityDto;
    }

    /**
     * 按日期产能下达
     *
     * @param apsOrderDto
     */
    private CalcByCapactityDto insertPlanOrderByCapacity(ApsOrderDto apsOrderDto) {
        //判断下达的日期有没重叠
        CalcByCapactityDto calcByCapactityDto = new CalcByCapactityDto();
        LocalDate startDate = apsOrderDto.getPlanStartDate();
        BigDecimal planQty = apsOrderDto.getPlanQty();
        List<ApsHoliday> holidayList = apsHolidayDao.findByFilter(new SearchFilter("endDate", startDate, SearchFilter.Operator.LE));
        List<ApsOrderPlanDetail> details = calcDetail(planQty, apsOrderDto.getCapacity(), startDate, holidayList);
        calcByCapactityDto.setApsOrderPlanDetailList(details);
        calcByCapactityDto.setStandardQty(apsOrderDto.getCapacity());
        return calcByCapactityDto;
    }

    /**
     * 更新挪到后面的计划
     *
     * @param apsOrderPlanDetailList
     */
    @Transactional(rollbackFor = Exception.class)
    public void uPdateOldPlan(ApsOrderDto apsOrderDto, List<ApsOrderPlanDetail> apsOrderPlanDetailList, String planId) {
        if (apsOrderPlanDetailList.size() > 0) {
            //获取需要重新排的订单
            List<ApsOrderPlanDetail> planDetailList = apsOrderPlanDetailDao.queryRecordPlan(apsOrderDto.getWorkGroupId(), apsOrderDto.getMaterialCode(), apsOrderDto.getPlanStartDate(), planId);
            //先把靠后的全部清除掉
            apsOrderPlanDetailDao.delete(planDetailList.stream().map(i -> i.getId()).collect(Collectors.toList()));
            //把相同的单号合并排
            List<ApsOrderPlanDetail> newPanList = new ArrayList<>();
            for (ApsOrderPlanDetail apsOrderPlanDetail : planDetailList) {
                ApsOrderPlanDetail addPlan = new ApsOrderPlanDetail();
                Optional<ApsOrderPlanDetail> apsOrderPlanDetailOptional = newPanList.stream().filter(a -> a.getPlanId().equals(apsOrderPlanDetail.getPlanId())).findFirst();
                if (apsOrderPlanDetailOptional.isPresent()) {
                    if (apsOrderPlanDetail.getPlanDate().isBefore(apsOrderPlanDetailOptional.get().getPlanDate())) {
                        apsOrderPlanDetailOptional.get().setPlanDate(apsOrderPlanDetail.getPlanDate());
                    }
                    apsOrderPlanDetailOptional.get().setPlanQty(apsOrderPlanDetailOptional.get().getPlanQty().add(apsOrderPlanDetail.getPlanQty()));
                } else {
                    BeanUtils.copyProperties(apsOrderPlanDetail, addPlan);
                    addPlan.setId(null);
                    newPanList.add(addPlan);
                }
            }
            for (ApsOrderPlanDetail newPlan : newPanList) {
                //获取最后记录的
                BigDecimal planQty = newPlan.getPlanQty();
                List<ApsOrderPlanDetail> saveList = new ArrayList<>();
                //Optional<ApsOrderPlanDetail> maxDate = apsOrderPlanDetailList.stream().max(Comparator.comparing(ApsOrderPlanDetail::getPlanDate));
                List<ApsOrderPlanDetail> lastPlans = apsOrderPlanDetailDao.queryLastRecordByWorkGroupAndMaterialCode(apsOrderDto.getWorkGroupId(), apsOrderDto.getMaterialCode());
                ApsOrderPlanDetail maxDate = lastPlans.get(0);
                //ApsOrderPlanDetail lastRecord = lastPlans.size() > 0 ? lastPlans.get(0) : null;
                if (maxDate != null) {
                    List<ApsHoliday> holidayList = apsHolidayDao.findByFilter(new SearchFilter("endDate", maxDate.getPlanDate(), SearchFilter.Operator.LE));
                    // 当日生产还剩余产能
                    Optional<BigDecimal> allLastDayCapacityOptional = lastPlans.stream().filter(o -> o.getPlanDate().equals(maxDate.getPlanDate())).map(o -> o.getPlanQty()).reduce(BigDecimal::add);
                    BigDecimal allLastDayCapacity = allLastDayCapacityOptional.orElse(BigDecimal.ZERO);
                    if (allLastDayCapacity.compareTo(BigDecimal.ZERO) > 0) {
                        BigDecimal dayRemainQty = apsOrderDto.getCapacity().subtract(allLastDayCapacity);
                        if (dayRemainQty.compareTo(BigDecimal.ZERO) >= 0) {
                            ApsOrderPlanDetail detail = new ApsOrderPlanDetail();
                            detail.setPlanDate(calcPlanDate(maxDate.getPlanDate(), holidayList));
                            if (planQty.compareTo(dayRemainQty) >= 0) {
                                detail.setPlanQty(dayRemainQty);
                                planQty = planQty.subtract(dayRemainQty);
                            } else {
                                detail.setPlanQty(planQty);
                                planQty = BigDecimal.ZERO;
                            }
                            saveList.add(detail);
                        }
                    }
                    //超出当天产量
                    if (planQty.compareTo(BigDecimal.ZERO) > 0) {
                        List<ApsOrderPlanDetail> planDetailList1 = calcDetail(planQty, apsOrderDto.getCapacity(), maxDate.getPlanDate().plusDays(1), holidayList);
                        saveList.addAll(planDetailList1);
                    }
                    if (saveList.size() > 0) {
                        ApsOrderPlan apsOrderPlan = dao.getOne(newPlan.getPlanId());
                        ApsOrderPlanDetail maxDetail = saveList.stream().max(Comparator.comparing(ApsOrderPlanDetail::getPlanDate)).get();
                        apsOrderPlan.setEndDate(maxDetail.getPlanDate());
                        apsOrderPlan = dao.save(apsOrderPlan);
                        for (ApsOrderPlanDetail detail : saveList) {
                            detail.setPlanId(apsOrderPlan.getId());
                        }
                        apsOrderPlanDetailDao.save(saveList);
                        apsOrderService.updateOrderStartDateAndEndDate(apsOrderPlan);
                    }
                }

            }

        }

    }

    /**
     * @param planDetails 已排明细
     * @param planQty     计划数量
     * @param apsOrderDto
     * @param planDate
     * @param holidayList
     * @return
     */
    private List<ApsOrderPlanDetail> calcDetailDelay(List<ApsOrderPlanDetail> planDetails, BigDecimal planQty, ApsOrderDto apsOrderDto, LocalDate planDate, List<ApsHoliday> holidayList) {
        List<ApsOrderPlanDetail> details = new ArrayList<>();
        //先计算当天生产数量
        BigDecimal actualQty = planDetails.stream().filter(c->c.getApsOrderPlan().getMaterialCode().equals(apsOrderDto.getMaterialCode())).filter(a -> a.getPlanDate().equals(planDate)).map(ApsOrderPlanDetail::getPlanQty).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
        if (apsOrderDto.getCapacity().subtract(actualQty).compareTo(planQty) > 0) {
            // 生产数 < 产能
            ApsOrderPlanDetail detail = new ApsOrderPlanDetail();
            detail.setPlanDate(calcPlanDate(planDate, holidayList));
            detail.setPlanQty(planQty);
            details.add(detail);
        } else {
            // 计划数 > 产能-已排数
            int i = 0;
            do {
                ApsOrderPlanDetail detail = new ApsOrderPlanDetail();
                detail.setPlanDate(calcPlanDate(planDate.plusDays(i), holidayList));
                actualQty = planDetails.stream().filter(c->c.getApsOrderPlan().getMaterialCode().equals(apsOrderDto.getMaterialCode())).filter(a -> a.getPlanDate().equals(detail.getPlanDate())).map(ApsOrderPlanDetail::getPlanQty).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
                BigDecimal overQty = apsOrderDto.getCapacity().subtract(actualQty);
                detail.setPlanQty(overQty);
                planQty = planQty.subtract(overQty);
                details.add(detail);
                i++;
            } while (planQty.compareTo(BigDecimal.ZERO) > 0);
        }
        return details;
    }

    /**
     * 计算生产计划明细
     *
     * @param planQty     排产数
     * @param standardQty 产能
     * @param planDate    排产时间
     * @param holidayList 假期
     */
    private List<ApsOrderPlanDetail> calcDetail(BigDecimal planQty, BigDecimal standardQty, LocalDate planDate, List<ApsHoliday> holidayList) {
        // 生产数 > 产能
        List<ApsOrderPlanDetail> details = new ArrayList<>();
        if (standardQty.compareTo(planQty) < 0) {
            int i = 0;
            do {
                ApsOrderPlanDetail detail = new ApsOrderPlanDetail();
                detail.setPlanDate(calcPlanDate(planDate.plusDays(i), holidayList));
                if (standardQty.compareTo(planQty) < 0) {
                    detail.setPlanQty(standardQty);
                    planQty = planQty.subtract(standardQty);
                } else {
                    detail.setPlanQty(planQty);
                    planQty = BigDecimal.ZERO;
                }
                details.add(detail);
                i++;
            } while (planQty.compareTo(BigDecimal.ZERO) > 0);
        } else {
            // 生产数 < 产能
            ApsOrderPlanDetail detail = new ApsOrderPlanDetail();
            detail.setPlanDate(calcPlanDate(planDate, holidayList));
            detail.setPlanQty(planQty);
            details.add(detail);
        }
        return details;
    }
    /**
     * 根据U9完工报告更新计划表
     */
    @Transactional(rollbackFor = Exception.class)
    public  void updatePlanFormU9(){
        LocalDate date = LocalDate.now().plusDays(-1);
        Map<String, BigDecimal> materialCodeYeildMap = new HashMap<>();
        List<U9MoFinish> moFinishList = u9MoFinishDao.findByFinishDateBetween(date,LocalDate.now());
        List<String> moList = moFinishList.stream().map(a -> a.getOrderNo()).collect(Collectors.toList());
        List<ApsOrder> apsOrderList = apsOrderDao.findByOrderNoIn(moList).stream().collect(Collectors.toList());
        for (ApsOrder apsOrder : apsOrderList) {
            BigDecimal finishQty = moFinishList.stream().filter(a -> a.getOrderNo().equals(apsOrder.getOrderNo())).map(a -> a.getFinishQty()).reduce(BigDecimal.ZERO, BigDecimal::add);
            if(materialCodeYeildMap.containsKey(apsOrder.getMaterialCode())){
                materialCodeYeildMap.put(apsOrder.getMaterialCode(),finishQty.add(materialCodeYeildMap.get(apsOrder.getMaterialCode())));
            }else{
                materialCodeYeildMap.put(apsOrder.getMaterialCode(),finishQty);
            }

        }
        //找出有排计划未生产部分赋值生产数为0
        for (ApsOrderPlanDetail apsOrderPlanDetail : apsOrderPlanDetailDao.findAllByPlanDate(date)) {
            if(materialCodeYeildMap.get(apsOrderPlanDetail.getApsOrderPlan().getMaterialCode())==null){
                materialCodeYeildMap.put(apsOrderPlanDetail.getApsOrderPlan().getMaterialCode(),BigDecimal.ZERO);
            }
        }
        List<String> planIds = new ArrayList<>();
        List<ApsOrderPlanDetail> planDetailList = getPlan(materialCodeYeildMap, date, planIds);
        //不用重排的订单
        List<ApsOrderPlanDetail> keepDetails = new ArrayList<>();
        List<ApsOrderPlan> byIdIn = dao.findByIdIn(planIds).stream().filter(a -> a.getStatus().name().equals("Normal")).collect(Collectors.toList());
        for (ApsOrderPlan apsOrderPlan : byIdIn) {
            keepDetails.addAll(apsOrderPlan.getOrderPlanDetails());
        }
        calcPlan(planDetailList, keepDetails);
    }
    /**
     * 根据MCAS当天报工数据更新计划表
     */
    @Transactional(rollbackFor = Exception.class)
    public void updatePlanFromMcas() {
        //每天7点半计算昨天数据，因些日期要减-1
        LocalDate date = LocalDate.now().plusDays(-1);
        Map<String, BigDecimal> materialCodeYeildMap = new HashMap<>();
        List<ApsProductionProcessSchedule> macsList = apsProductionProcessScheduleDao.findAllByProductionDateEquals(date);
        List<String> materialCodeList = macsList.stream().map(ApsProductionProcessSchedule::getMaterialCode).collect(Collectors.toList());

        List<U9Material> materialCodeInList = materialDao.findByCodeIn(materialCodeList);
        for (String materialCode : materialCodeList) {
            BigDecimal sumQty = BigDecimal.ZERO;
            String productName = materialCodeInList.stream().filter(c -> c.getCode().equals(materialCode)).findFirst().get().getProductName();
            Optional<McasProcess> topByItemNameAndType = mcasProcessDao.findTopByItemNameAndType(productName, "1");
            if (topByItemNameAndType.isPresent()) {
                int lastNo = topByItemNameAndType.get().getSortNo();
                List<ApsProductionProcessSchedule> processScheduleList = macsList.stream().filter(c -> c.getMaterialCode().equals(materialCode)).collect(Collectors.toList());
                for (ApsProductionProcessSchedule schedule : processScheduleList) {
                    Object fieldValue = ReflectUtils.getFieldValue(schedule, "process" + lastNo);
                    if (fieldValue != null) {
                        sumQty = sumQty.add((BigDecimal) fieldValue);
                    }
                }
            } else {
                LogUtil.bizLog(materialCode + "没有找到工艺");
            }
            if (sumQty.compareTo(BigDecimal.ZERO) > 0) {
                materialCodeYeildMap.put(materialCode, sumQty);
            }

        }
        //清冼线
        List<McasYield> mcasYieldsByDateAndProcess = mcasYieldDao.findMcasYieldsByDateAndProcess(date, "");
        for (McasYield qxYelid : mcasYieldsByDateAndProcess) {
            if (materialCodeYeildMap.containsKey(qxYelid.getMaterialCode())) {
                materialCodeYeildMap.put(qxYelid.getMaterialCode(), materialCodeYeildMap.get(qxYelid.getMaterialCode()).add(qxYelid.getQty()));
            } else {
                materialCodeYeildMap.put(qxYelid.getMaterialCode(), qxYelid.getQty());
            }
        }
        //找出有排计划未生产部分赋值生产数为0
        for (ApsOrderPlanDetail apsOrderPlanDetail : apsOrderPlanDetailDao.findAllByPlanDate(date)) {
            if(materialCodeYeildMap.get(apsOrderPlanDetail.getApsOrderPlan().getMaterialCode())==null){
                materialCodeYeildMap.put(apsOrderPlanDetail.getApsOrderPlan().getMaterialCode(),BigDecimal.ZERO);
            }
        }
        List<String> planIds = new ArrayList<>();
        List<ApsOrderPlanDetail> planDetailList = getPlan(materialCodeYeildMap, date, planIds);
        //不用重排的订单
        List<ApsOrderPlanDetail> keepDetails = new ArrayList<>();
        List<ApsOrderPlan> byIdIn = dao.findByIdIn(planIds).stream().filter(a -> a.getStatus().name().equals("Normal")).collect(Collectors.toList());
        for (ApsOrderPlan apsOrderPlan : byIdIn) {
            keepDetails.addAll(apsOrderPlan.getOrderPlanDetails());
        }
        calcPlan(planDetailList, keepDetails);
        //没有生产的重排


        //这里还要把后面的计划重排，不然会出现超产情况，目前暂不处理
    }

    /**
     * 计算出未完成的计划数，放到N+1天处理
     *
     * @param materialCodeYeildMap 实际料号生产数量
     * @param date                 生产日期
     */
    private List<ApsOrderPlanDetail> getPlan(Map<String, BigDecimal> materialCodeYeildMap, LocalDate date, List<String> reservePlanIds) {
        //先取出当天排产计划
        List<ApsOrderPlanDetail> list = new ArrayList<>();
        List<ApsOrderPlanDetail> allByPlanDate = apsOrderPlanDetailDao.findAllByPlanDate(date)
                .stream().filter(a -> a.getApsOrderPlan().getStatus().name().equals("Normal")).collect(Collectors.toList());
        materialCodeYeildMap.forEach((materialcode, actuQty) -> {
            List<ApsOrderPlanDetail> planDetails = allByPlanDate.stream().filter(a -> a.getApsOrderPlan().getMaterialCode().equals(materialcode)).collect(Collectors.toList());
            for (ApsOrderPlanDetail planDetail : planDetails) {
                if (actuQty.compareTo(planDetail.getPlanQty()) >= 0) {
                    //生产数量大于计划数量，不用处理，只记录
                    actuQty = actuQty.subtract(planDetail.getPlanQty());
                    reservePlanIds.add(planDetail.getPlanId());
                } else {
                    ApsOrderPlanDetail addPlan = new ApsOrderPlanDetail();
                    BeanUtils.copyProperties(planDetail, addPlan);
                    planDetail.setPlanQty(actuQty);
                    apsOrderPlanDetailDao.save(planDetail);
                    //未生产部分顺延一天
                    addPlan.setPlanDate(addPlan.getPlanDate().plusDays(1));
                    addPlan.setPlanQty(addPlan.getPlanQty().subtract(actuQty));
                    LogUtil.bizLog("料号:" + planDetail.getApsOrderPlan().getMaterialCode() + "，顺延数量:" + addPlan.getPlanQty());
                    addPlan.setId(null);
                    list.add(addPlan);
                    actuQty = BigDecimal.ZERO;
                }
            }
        });
        return list;
    }

    /**
     * 重新计算计划
     *
     * @param delayPlanDetailList 延后的计划
     * @param keepDetails         维持不变的计划
     */
    private void calcPlan(List<ApsOrderPlanDetail> delayPlanDetailList, List<ApsOrderPlanDetail> keepDetails) {
        ApsOrderDto apsOrderDto = new ApsOrderDto();
        List<String> materilCodes = delayPlanDetailList.stream().map(a -> a.getApsOrderPlan().getMaterialCode()).collect(Collectors.toList());
        List<U9Material> materialList = materialDao.findByCodeIn(materilCodes);
        for (ApsOrderPlanDetail delayPlanDetail : delayPlanDetailList) {
            Optional<U9Material> u9Material = materialList.stream().filter(a -> a.getCode().equals(delayPlanDetail.getApsOrderPlan().getMaterialCode())).findFirst();
            if (u9Material.get().getCapacity() == null || u9Material.get().getCapacity().compareTo(BigDecimal.ZERO) == 0) {
                apsOrderDto.setCapacity(maxCapactity);
            } else {
                apsOrderDto.setCapacity(u9Material.get().getCapacity());
            }
            apsOrderDto.setId(delayPlanDetail.getApsOrderPlan().getOrderId());
            apsOrderDto.setMaterialId(delayPlanDetail.getApsOrderPlan().getMaterialId());
            apsOrderDto.setMaterialCode(delayPlanDetail.getApsOrderPlan().getMaterialCode());
            apsOrderDto.setPlanStartDate(delayPlanDetail.getPlanDate());
            //先取出整张单加上延后数量
            List<ApsOrderPlanDetail> planDetail = apsOrderPlanDetailDao.queryRecordPlan(delayPlanDetail.getPlanDate(), delayPlanDetail.getPlanId()).stream().collect(Collectors.toList());
            BigDecimal sumQty = planDetail.stream().map(ApsOrderPlanDetail::getPlanQty).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
            apsOrderDto.setPlanQty(delayPlanDetail.getPlanQty().add(sumQty));
            apsOrderDto.setWorkGroupId(delayPlanDetail.getApsOrderPlan().getWorkGroupId());
            CalcByCapactityDto calcByCapactityDto = insertPlanOrderByCapacityDelay(apsOrderDto, keepDetails);
            List<ApsOrderPlanDetail> details = calcByCapactityDto.getApsOrderPlanDetailList();
            for (ApsOrderPlanDetail detail : details) {
                detail.setPlanId(delayPlanDetail.getPlanId());
            }
            savePlanDelay(details);
            apsOrderPlanDetailDao.delete(planDetail.stream().map(a -> a.getId()).collect(Collectors.toList()));

        }
    }



    /**
     * 计算排产日期（跳过节假日）
     *
     * @param now
     * @return
     */
    private LocalDate calcPlanDate(LocalDate now, List<ApsHoliday> holidayList) {
        for (ApsHoliday apsHoliday : holidayList) {
            if (now.compareTo(apsHoliday.getStartDate()) >= 0 && now.compareTo(apsHoliday.getEndDate()) <= 0) {
                return apsHoliday.getEndDate().plusDays(1);
            }
        }
        return now;

    }

    /**
     *
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateOrderPlanToFinish() {
        long s = System.currentTimeMillis();
        List<ApsOrderPlan> apsOrderPlanList = dao.findAllByStatus(OrderStatusType.Normal);
        List<String> orders = apsOrderPlanList.stream().map(a -> a.getOrder().getOrderNo()).collect(Collectors.toList());
        List<ApsOrderPlan>finsishPlan=new ArrayList<>();
        List<String> completeOrders = u9ProduceOrderDao.findCompleteOrder(orders);
        for (ApsOrderPlan apsOrderPlan : apsOrderPlanList) {
            if(completeOrders.contains(apsOrderPlan.getOrder().getOrderNo())) {
                apsOrderPlan.setStatus(OrderStatusType.Completed);
                finsishPlan.add(apsOrderPlan);
            }
        }
        save(finsishPlan);
        apsOrderDao.updateApsOrderStatus(OrderStatusType.Completed,completeOrders);
        long e = System.currentTimeMillis();
        LogUtil.bizLog("更新计划状态为完工耗时："+(e-s));
    }


    private Map<String, Object> toMapDto(ApsOrderPlan plan) {
        Map<String, Object> result = new HashMap<>();
        result.put("id", plan.getId());
        result.put("orderId", plan.getOrderId());
        result.put("orderNo", plan.getOrder().getOrderNo());
        result.put("orderQty", plan.getOrder().getOrderQty());
        result.put("deliveryDate", plan.getDeliveryDate());
        result.put("awaitQty", plan.getAwaitQty());
        result.put("hasQty", plan.getHasQty());
        result.put("actualQty", plan.getActualQty());
        result.put("standardQty", plan.getStandardQty());
        result.put("orderDate", plan.getOrderDate());
        result.put("endDate", plan.getEndDate());
        result.put("startDate", plan.getStartDate());
        result.put("scmDeliveryDate", plan.getScmDeliveryDate());
        result.put("status", plan.getStatus());
        result.put("planQty", plan.getPlanQty());
        result.put("lineName", plan.getLineName());
        result.put("lineId", plan.getLineId());
        result.put("workGroupName", plan.getWorkGroupName());
        result.put("workGroupId", plan.getWorkGroupId());
        result.put("materialCode", plan.getMaterialCode());
        result.put("materialId", plan.getMaterialId());
        result.put("materialName", plan.getMaterialName());
        result.put("materialSpec", plan.getMaterialSpec());
        result.put("oweQty", plan.getOweQty());
        result.put("productionDate", plan.getProductionDate());
        result.put("remark", plan.getRemark());
        result.put("u9Status", plan.getU9Status());
        result.put("lastEditorName",plan.getLastEditorName());
        result.put("lastEditedDate",plan.getLastEditedDate());
        return result;
    }


    /**
     * 批量修改完工数量
     * @param modifyFinishQtyParamDtos
     */
    public void BatchModifyHasQtyByParam(List<ModifyFinishQtyParamDto> modifyFinishQtyParamDtos) {
        //分片
        List<String> modifyOrderNos = modifyFinishQtyParamDtos.stream().map(ModifyFinishQtyParamDto::getOrderId).collect(Collectors.toList());
        //orderNo查询生产计划实体
        List<ApsOrderPlan> ApsOrderPlans = dao.findByOrderIdIn(modifyOrderNos);
        //批量更新
        ApsOrderPlans.forEach(m->{
            Optional<ModifyFinishQtyParamDto> findFirst = modifyFinishQtyParamDtos.stream().filter(i -> i.getOrderId().equals(m.getOrderId())).findFirst();
            if (findFirst.isPresent()) {
                m.setHasQty(findFirst.get().getQty());
            }
        });
        this.save(ApsOrderPlans);


    }
}
