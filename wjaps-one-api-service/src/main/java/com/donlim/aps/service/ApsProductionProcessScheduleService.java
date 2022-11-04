package com.donlim.aps.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.dto.serach.SearchFilter;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.dao.ApsProductionProcessScheduleDao;
import com.donlim.aps.dto.StampingReportDto;
import com.donlim.aps.dto.WashWorkshopScheduleDto;
import com.donlim.aps.entity.*;
import com.donlim.aps.util.DateUtils;
import com.donlim.aps.util.NumberUtils;
import com.donlim.aps.util.ReflectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 生产工序报工表
 * (ApsProductionProcessSchedule)业务逻辑实现类
 *
 * @author sei
 * @since 2022-06-20 11:34:57
 */
@Service
public class ApsProductionProcessScheduleService extends BaseEntityService<ApsProductionProcessSchedule> {
    @Autowired
    private ApsProductionProcessScheduleDao dao;
    @Autowired
    private McasProcessService mcasProcessService;
    @Autowired
    private McasYieldService mcasYieldService;
    @Autowired
    private ApsOrderPlanService apsOrderPlanService;
    @Autowired
    private ApsOrderPlanDetailService apsOrderPlanDetailService;
    @Autowired
    private U9ProduceOrderService u9ProduceOrderService;
    @Autowired
    private U9MaterialService u9MaterialService;

    @Autowired
    @Override
    protected BaseEntityDao<ApsProductionProcessSchedule> getDao() {
        return dao;
    }

    /**
     * 计算清洗喷粉报工报表
     * @param search
     * @return
     */
    public List<WashWorkshopScheduleDto> washWorkshopReport(Search search){
        List<McasYield> byWashAndPowder = mcasYieldService.findByWashAndPowder(search);
        Map<String, WashWorkshopScheduleDto> collect = byWashAndPowder.stream().collect(Collectors.groupingBy(McasYield::getMo, Collectors.collectingAndThen(Collectors.toList(), o -> {
            WashWorkshopScheduleDto washWorkshopScheduleDto = new WashWorkshopScheduleDto();
            StringBuilder reportDateBuilder = new StringBuilder();
            BigDecimal washQtySum = new BigDecimal(0);
            BigDecimal powderQtySum = new BigDecimal(0);
            for (McasYield mcasYield : o) {
                reportDateBuilder.append(DateUtils.LocalDateToString(mcasYield.getDate())).append(",");
                if (mcasYield.getLineName().contains("清洗")) {
                    washQtySum = washQtySum.add(mcasYield.getQty());
                } else if (mcasYield.getLineName().contains("喷粉")) {
                    powderQtySum = powderQtySum.add(mcasYield.getQty());
                }
            }

            McasYield mcasYield = o.get(0);
            List<ApsOrderPlan> plans = apsOrderPlanService.getOrderByOrderNo(Collections.singletonList(mcasYield.getMo()));
            List<U9Material> u9Materials = u9MaterialService.findByCodeIn(Collections.singletonList(mcasYield.getMaterialCode()));
            washWorkshopScheduleDto.setReportDate(reportDateBuilder.toString());
            washWorkshopScheduleDto.setOrderNo(mcasYield.getMo());
            washWorkshopScheduleDto.setMaterialCode(mcasYield.getMaterialCode());
            washWorkshopScheduleDto.setMaterialName(mcasYield.getMaterialName());
            washWorkshopScheduleDto.setPowderReportQty(powderQtySum);
            washWorkshopScheduleDto.setWashReportQty(washQtySum);
            if (plans.size() > 0) {
                ApsOrderPlan orderPlan = plans.get(0);
                washWorkshopScheduleDto.setOrderQty(orderPlan.getOrder().getOrderQty());
                washWorkshopScheduleDto.setPlanQty(orderPlan.getPlanQty());
                washWorkshopScheduleDto.setDeliveryDate(orderPlan.getScmDeliveryDate());
                washWorkshopScheduleDto.setCapacity(orderPlan.getStandardQty());
            }
            if (u9Materials.size() > 0) {
                U9Material material = u9Materials.get(0);

                washWorkshopScheduleDto.setMaterialSpec(material.getSpec());

                    washWorkshopScheduleDto.setPowderModel(material.getPowderModel());
                    washWorkshopScheduleDto.setPowderArea(NumberUtils.getBigDecimalValueAndDefault(material.getPowderArea(), BigDecimal.ZERO));
                    washWorkshopScheduleDto.setWashArea(NumberUtils.getBigDecimalValueAndDefault(material.getWashArea(), BigDecimal.ZERO));
                    washWorkshopScheduleDto.setLastQty(material.getEndQty());
                    washWorkshopScheduleDto.setSumPowderArea(washWorkshopScheduleDto.getPowderArea().multiply(washWorkshopScheduleDto.getPowderReportQty()));
                    washWorkshopScheduleDto.setSumWashArea(washWorkshopScheduleDto.getWashArea().multiply(washWorkshopScheduleDto.getWashReportQty()));

            }

            return washWorkshopScheduleDto;
        })));

        return  new ArrayList<>(collect.values());
    }

    /**
     * 按料号查询报工情况
     *
     * @param searchConfig
     * @return
     */
    public List<ApsProductionProcessSchedule> findByMaterialList(Search searchConfig) {
        List<ApsProductionProcessSchedule> list = new ArrayList<>();
        List<ApsProductionProcessSchedule> scheduleList = dao.findByFilters(searchConfig);
        for (ApsProductionProcessSchedule schedule : scheduleList) {
            Optional<ApsProductionProcessSchedule> oldProcessSchedule = list.stream()
                    .filter(a -> a.getProductionDate().equals(schedule.getProductionDate()))
                    .filter(b -> b.getMaterialCode().equals(schedule.getMaterialCode()))
                    .filter(c -> c.getMaterialName().equals(schedule.getMaterialName())).findFirst();
            if (oldProcessSchedule.isPresent()) {
                for (int sortNo = 1; sortNo <= 30; sortNo++) {
                    BigDecimal oldQty = (BigDecimal) ReflectUtils.getFieldValue(oldProcessSchedule.get(), "process" + sortNo);
                    if (oldQty == null) {
                        oldQty = BigDecimal.ZERO;
                    }
                    BigDecimal newQty = (BigDecimal) ReflectUtils.getFieldValue(schedule, "process" + sortNo);
                    if (newQty == null) {
                        newQty = BigDecimal.ZERO;
                    }
                    ReflectUtils.setFieldValue(oldProcessSchedule.get(), "process" + sortNo, newQty.add(oldQty));
                }
            } else {
                list.add(schedule);
            }
        }
        return list;
    }


    /**
     * 计算排产报工情况
     */
    @Transactional(rollbackFor = Exception.class)
    public void calcProductionProcessSchedule(LocalDate calcDate) {
        List<U9Material> u9MaterialList=u9MaterialService.findByProductModelIsNotNull();
        //取mcas报工记录
        List<ApsProductionProcessSchedule> saveList = new ArrayList<>();
        List<McasYield> mcasYieldList = mcasYieldService.findMcasYieldsByDate(calcDate);
        List<McasProcess> mcasProcessList = getMcasDayProductionProcess(mcasYieldList);
        List<ApsProductionProcessSchedule> scheduleList = new ArrayList<>();
        int maxSortNo = 0;
        for (McasYield mcasYield : mcasYieldList) {
            if (mcasYield.getProcess().isEmpty()) {
                continue;
            }
            ApsProductionProcessSchedule schedule = new ApsProductionProcessSchedule();
            schedule.setProductionDate(mcasYield.getDate());
            schedule.setMaterialCode(mcasYield.getMaterialCode());
            schedule.setMaterialName(mcasYield.getMaterialName());
            Optional<U9Material> u9Material = u9MaterialList.stream().filter(c -> c.getCode().equals(mcasYield.getMaterialCode())).findFirst();
            if(u9Material.isPresent()){
                schedule.setMaterialSpec(u9Material.get().getSpec());
            }
            schedule.setProductOrder(mcasYield.getMo());
            //用来判断该报产工序属于第几道工序
            int sortNo = getProcesesNo(mcasProcessList, mcasYield);
            if (sortNo > 0) {
                //先判断有没值，有值此则类加
                Optional<ApsProductionProcessSchedule> oldProcessSchedule = scheduleList.stream()
                        .filter(a -> a.getProductionDate().equals(mcasYield.getDate()))
                        .filter(b -> b.getMaterialCode().equals(mcasYield.getMaterialCode()))
                        .filter(c -> c.getMaterialName().equals(mcasYield.getMaterialName()))
                        .filter(d -> d.getProductOrder().equals(mcasYield.getMo())).findFirst();
                if (oldProcessSchedule.isPresent()) {
                    BigDecimal oldQty = (BigDecimal) ReflectUtils.getFieldValue(oldProcessSchedule.get(), "process" + sortNo);
                    if (oldQty == null) {
                        oldQty = BigDecimal.ZERO;
                    }
                    ReflectUtils.setFieldValue(oldProcessSchedule.get(), "process" + sortNo, mcasYield.getQty().add(oldQty));
                    if (sortNo > maxSortNo) {
                        ReflectUtils.setFieldValue(oldProcessSchedule.get(), "processLast", mcasYield.getQty().add(oldQty));
                        maxSortNo = sortNo;
                    }
                } else {
                    ReflectUtils.setFieldValue(schedule, "process" + sortNo, mcasYield.getQty());
                    ReflectUtils.setFieldValue(schedule, "processLast", mcasYield.getQty());
                    scheduleList.add(schedule);
                }
            }
        }

        dao.deleteByProductionDateEquals(calcDate);
        save(scheduleList);
    }

    /**
     * 查询冲压报工报表（按单）
     *
     * @param searchConfig
     * @return
     */
    public List<StampingReportDto> getStampingReport(Search searchConfig) {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();
        List<SearchFilter> filters = Optional.ofNullable(searchConfig.getFilters()).orElse(new ArrayList<>());
        for (SearchFilter filter : filters) {
            if ("productionDate".equals(filter.getFieldName())) {
                if (SearchFilter.Operator.GE.equals(filter.getOperator())) {
                    startDate = DateUtils.date2LocalDate((Date) filter.getValue());
                } else if (SearchFilter.Operator.LE.equals(filter.getOperator())) {
                    endDate = DateUtils.date2LocalDate((Date) filter.getValue());
                }
            }
        }
        List<StampingReportDto> reportlist = new ArrayList<>();
        List<ApsProductionProcessSchedule> scheduleList = dao.findByFilters(searchConfig);
        //存储生产订单号
        List<String> orderList;
        List<LocalDate> dateList = DateUtils.getAscDateList(startDate, endDate);
        for (LocalDate date : dateList) {
            orderList = new ArrayList<>();
            List<ApsProductionProcessSchedule> scheduleDateList = scheduleList.stream()
                    .filter(a -> a.getProductionDate().isEqual(date)).collect(Collectors.toList());
            for (ApsProductionProcessSchedule processSchedule : scheduleDateList) {
                StampingReportDto stampingReportDto = new StampingReportDto();
                stampingReportDto.setProductionDate(processSchedule.getProductionDate());
                stampingReportDto.setFirstProcessQty(processSchedule.getProcess1());
                stampingReportDto.setMaterialCode(processSchedule.getMaterialCode());
                stampingReportDto.setMaterialName(processSchedule.getMaterialName());
                stampingReportDto.setMaterialSpec(processSchedule.getMaterialSpec());
                stampingReportDto.setLastProcessQty(processSchedule.getProcessLast());
                stampingReportDto.setOrderNo(processSchedule.getProductOrder());
                orderList.add(processSchedule.getProductOrder());
                reportlist.add(stampingReportDto);
            }
            List<ApsOrderPlan> orderByOrderNo = apsOrderPlanService.getOrderByOrderNo(orderList);
            for (StampingReportDto stampingReportDto : reportlist) {
                //根据生产单号和生产日期匹配生产计划
                List<ApsOrderPlan> apsOrderPlanList = orderByOrderNo.stream()
                        .filter(a -> a.getOrder().getOrderNo().equals(stampingReportDto.getOrderNo()))
                        .collect(Collectors.toList());

                //累加排产数
                BigDecimal sumPlanQty = BigDecimal.ZERO;
                for (ApsOrderPlan apsOrderPlan : apsOrderPlanList) {
                    List<ApsOrderPlanDetail> orderPlanDetailList = apsOrderPlan.getOrderPlanDetails();
                    for (ApsOrderPlanDetail apsOrderPlanDetail : orderPlanDetailList) {
                        sumPlanQty.add(apsOrderPlanDetail.getPlanQty());
                    }
                }
                //累加报工数
                BigDecimal sumProduceQty = dao.findAllByProductOrder(stampingReportDto.getOrderNo()).stream()
                        .map(ApsProductionProcessSchedule::getProcessLast).reduce(BigDecimal::add).get();
                if (apsOrderPlanList.size() > 0) {
                    stampingReportDto.setLineName(apsOrderPlanList.get(0).getLineName());
                    stampingReportDto.setWorkGroupName(apsOrderPlanList.get(0).getWorkGroupName());
                    stampingReportDto.setMaterialName(apsOrderPlanList.get(0).getMaterialName());
                    stampingReportDto.setMaterialCode(apsOrderPlanList.get(0).getMaterialCode());
                    stampingReportDto.setMaterialSpec(apsOrderPlanList.get(0).getMaterialSpec());
                    stampingReportDto.setScmDeliveryDate(apsOrderPlanList.get(0).getScmDeliveryDate());
                    stampingReportDto.setOrderQty(apsOrderPlanList.get(0).getOrder().getOrderQty());
                    stampingReportDto.setPlanQty(sumPlanQty);
                    stampingReportDto.setOweQty(stampingReportDto.getOrderQty().subtract(sumProduceQty));
                    BigDecimal inStockQty=u9ProduceOrderService.getListByOrderNo(stampingReportDto.getOrderNo()).getTotalCompleteQty();
                    stampingReportDto.setInStockQty(inStockQty);
                }
            }
        }
        return reportlist;
    }

    /**
     * 获取工序所在的序号
     *
     * @param mcasProcessList 工序集合
     * @param mcasYield       产量
     * @return 工序序号
     */
    private int getProcesesNo(List<McasProcess> mcasProcessList, McasYield mcasYield) {
        Optional<McasProcess> first = mcasProcessList.stream()
                .filter(d -> d.getProcess().equals(mcasYield.getProcess()))
                .filter(a -> a.getItemName().equals(mcasYield.getMaterialName()))
                .filter(b -> b.getLineName().equals(mcasYield.getLineName()))
                .filter(c -> c.getCompanyCode().equals(mcasYield.getCompanyCode()))
                .findFirst();
        if (first.isPresent()) {
            return first.get().getSortNo();
        } else {
            return 0;
        }
    }

    /**
     * 获取当天报工的工序
     *
     * @param mcasYieldList
     * @return
     */
    private List<McasProcess> getMcasDayProductionProcess(List<McasYield> mcasYieldList) {
        //取出包含料品名称
        List<String> materialList = new ArrayList<>();
        //存储当天产量公司、部门、物料唯一性
        List<McasYield> calcMcasYeildList = new ArrayList<>();
        for (McasYield mcasYield : mcasYieldList) {
            //没工序的不计算
            if (mcasYield.getProcess().equals("")) {
                continue;
            }
            if (!materialList.contains(mcasYield.getMaterialName())) {
                materialList.add(mcasYield.getMaterialName());
            }
            if (calcMcasYeildList.size() > 0) {
                long count = calcMcasYeildList.stream()
                        .filter(a -> a.getCompanyCode().equals(mcasYield.getCompanyCode()))
                        .filter(b -> b.getLineName().equals(mcasYield.getLineName()))
                        .filter(c -> c.getMaterialCode().equals(mcasYield.getMaterialCode()))
                        .count();
                if (count == 0) {
                    calcMcasYeildList.add(mcasYield);
                }
            } else {
                calcMcasYeildList.add(mcasYield);
            }
        }
        //取出报工型号的所有工序
        List<McasProcess> mcasProcessList = mcasProcessService.findMcasProcessesByItemNameIn(materialList);
        List<McasProcess> tempMcasProcessList = new ArrayList<>();
        //筛选同公司，线别、产品型号的工序
        for (McasYield mcasYield : calcMcasYeildList) {
            List<McasProcess> processList = mcasProcessList.stream().filter(a -> a.getCompanyCode().equals(mcasYield.getCompanyCode()))
                    .filter(b -> b.getLineName().equals(mcasYield.getLineName()))
                    .filter(c -> c.getItemName().equals(mcasYield.getMaterialName())).collect(Collectors.toList());
            tempMcasProcessList.addAll(processList);
        }
        return tempMcasProcessList;
    }


}
