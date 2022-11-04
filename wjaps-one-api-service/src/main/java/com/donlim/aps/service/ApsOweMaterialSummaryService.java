package com.donlim.aps.service;

import com.changhong.sei.core.context.ContextUtil;
import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.dao.*;
import com.donlim.aps.dto.DateRange;
import com.donlim.aps.dto.MaterialType;
import com.donlim.aps.entity.*;
import com.donlim.aps.entity.cust.U9ProduceOrderAndMoPick;
import com.donlim.aps.util.NumberUtils;
import com.donlim.aps.vo.ApsOweMaterialPlanVO;
import com.donlim.aps.vo.ApsOweMaterialVO;
import com.donlim.aps.vo.PlanGroupByMaterialVO;
import com.donlim.aps.vo.PlanGroupByOrderVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 欠料汇总表(ApsOweMaterialSummary)业务逻辑实现类
 *
 * @author sei
 * @since 2022-06-14 11:52:45
 */
@Service
public class ApsOweMaterialSummaryService extends BaseEntityService<ApsOweMaterialSummary> {
    @Autowired
    private ApsOweMaterialSummaryDao dao;
    @Autowired
    private U9MoPickListDao moPickListDao;
    @Autowired
    private U9RpDao rpDao;
    @Autowired
    private U9ReceiveDao receiveDao;
    @Autowired
    private U9StockDao stockDao;
    @Autowired
    private ApsOweMaterialPlanDao apsOweMaterialPlanDao;
    @Autowired
    private ApsOweMaterialPlanDetailDao apsOweMaterialPlanDetailDao;
    @Autowired
    private ApsOweMaterialSummaryDetailDao apsOweMaterialSummaryDetailDao;
    @Autowired
    private ApsOrderPlanDetailService apsOrderPlanDetailService;

    @Override
    protected BaseEntityDao<ApsOweMaterialSummary> getDao() {
        return dao;
    }


    /**
     * 计算欠料
     * 1.查找日期范围内的排产单，获取工单
     * 2.根据工单号，查出所有排产单
     * 3.根据工单汇总
     * 4.查询备料表，获取已领数量
     * 5.查询收货单，获取暂收数量
     * 6.查询请购单，获取请求数量
     * 7.根据料号汇总
     * 输出结果
     * 1.根据原料料号汇总的集合
     * 2.根据原料料号汇总的每日排产明细
     * 3.根据工单汇总的集合
     * 4.根据工单汇总的每日排产明细
     */
    public List<ApsOweMaterialVO> calcOweMaterial(DateRange dateRange){
        String tenantCode = ContextUtil.getTenantCode();
        dao.deleteByTenantCode(tenantCode);
        apsOweMaterialPlanDao.deleteByTenantCode(tenantCode);
        apsOweMaterialPlanDetailDao.deleteByTenantCode(tenantCode);
        apsOweMaterialSummaryDetailDao.deleteByTenantCode(tenantCode);
        //1.根据工单汇总
        Map<String, PlanGroupByOrderVO> groupByOrderMap = groupByOrder(dateRange);

        //2.根据原料料号汇总
        List<PlanGroupByOrderVO> groupByOrders = new ArrayList<>(groupByOrderMap.values());
        Map<String, PlanGroupByMaterialVO> groupByMaterialMap = groupByMaterial(groupByOrders);
        List<PlanGroupByMaterialVO> groupByMaterials = new ArrayList<>(groupByMaterialMap.values());
        //3.生成 List<ApsOweMaterialVO>
        List<ApsOweMaterialVO> oweMaterialVOs = new ArrayList<>();
        for (PlanGroupByMaterialVO m : groupByMaterials) {
            List<ApsOweMaterialPlanVO> planVOs = new ArrayList<>();
            ApsOweMaterialVO materialVO = new ApsOweMaterialVO();
            ApsOweMaterialSummary apsOweMaterialSummary = new ApsOweMaterialSummary();
            for (PlanGroupByOrderVO o : groupByOrders) {
                if (!m.getMaterialId().equals(o.getOriginMaterialId())){
                    continue;
                }

                ApsOweMaterialPlanVO planVO = new ApsOweMaterialPlanVO();
                ApsOweMaterialPlan apsOweMaterialPlan = new ApsOweMaterialPlan();
                apsOweMaterialPlan.setOrderNo( o.getOrderNo());
                apsOweMaterialPlan.setMaterialId(o.getMaterialId());
                apsOweMaterialPlan.setMaterialCode(o.getMaterialCode());
                apsOweMaterialPlan.setMaterialName(o.getMaterialName());
                apsOweMaterialPlan.setMaterialSpec(o.getMaterialSpec());
                apsOweMaterialPlan.setOriginMaterial(o.getOriginMaterialId());
                apsOweMaterialPlan.setPlanQty(o.getPlanQty());
                apsOweMaterialPlan.setPullQty(o.getPullQty());
                apsOweMaterialPlan.setRequireQty(o.getPlanQty().multiply(o.getBomQty()).setScale(4,BigDecimal.ROUND_UP));
                //计算计划中的欠料数量
                BigDecimal oweQty = apsOweMaterialPlan.getPullQty().subtract(apsOweMaterialPlan.getRequireQty());
                apsOweMaterialPlan.setOweQty(oweQty);
                List<ApsOweMaterialPlanDetail> planDetails = new ArrayList<>();
                for (Map.Entry<LocalDate, BigDecimal> entry : o.getDetails().entrySet()) {
                    ApsOweMaterialPlanDetail apsOweMaterialPlanDetail = new ApsOweMaterialPlanDetail();
                    apsOweMaterialPlanDetail.setPlanDate(entry.getKey());
                    apsOweMaterialPlanDetail.setPlanQty(entry.getValue().multiply(o.getBomQty().setScale(4,BigDecimal.ROUND_UP)));
                    planDetails.add(apsOweMaterialPlanDetail);
                }

                planVO.setPlan(apsOweMaterialPlan);
                planVO.setPlanDetails(planDetails);
                planVOs.add(planVO);
            }
            apsOweMaterialSummary.setMaterialId(m.getMaterialId());
            apsOweMaterialSummary.setMaterialCode(m.getMaterialCode());
            apsOweMaterialSummary.setMaterialName(m.getMaterialName());
            apsOweMaterialSummary.setMaterialSpec(m.getMaterialSpec());
            apsOweMaterialSummary.setMaterialType(m.getMaterialType());
            apsOweMaterialSummary.setStockQty(m.getStockQty());
            apsOweMaterialSummary.setRequireQty(m.getRequireQty());
            apsOweMaterialSummary.setPoQty(m.getPoQty());
            apsOweMaterialSummary.setPullQty(m.getPullQty());
            apsOweMaterialSummary.setBeyondQty(m.getBeyondQty());
            apsOweMaterialSummary.setOweDate(m.getOweDate());
            apsOweMaterialSummary.setTempReceiveQty(m.getTempReceiveQty());
            List<ApsOweMaterialSummaryDetail> summaryDetails = new ArrayList<>();
            for (Map.Entry<LocalDate, BigDecimal> entry : m.getDetails().entrySet()) {
                ApsOweMaterialSummaryDetail apsOweMaterialSummaryDetail = new ApsOweMaterialSummaryDetail();
                apsOweMaterialSummaryDetail.setPlanDate(entry.getKey());
                apsOweMaterialSummaryDetail.setPlanQty(entry.getValue());
                summaryDetails.add(apsOweMaterialSummaryDetail);
            }
            materialVO.setSummaryDetails(summaryDetails);
            materialVO.setSummary(apsOweMaterialSummary);
            materialVO.setPlan(planVOs);
            oweMaterialVOs.add(materialVO);
        }

        return oweMaterialVOs;
    }

    /**
     * 根据计划汇总
     * @param dateRange
     * @return
     */
    private Map<String,PlanGroupByOrderVO> groupByOrder(DateRange dateRange){
        List<ApsOrderPlanDetail> plans = apsOrderPlanDetailService.findPlanBetweenDate(dateRange);
        Map<String, PlanGroupByOrderVO> groupByOrderMap = plans.stream().collect(Collectors.groupingBy(p -> p.getApsOrderPlan().getOrderId(), Collectors.collectingAndThen(Collectors.toList(), o -> {
            PlanGroupByOrderVO planGroupByOrderVO = new PlanGroupByOrderVO();
            ApsOrderPlanDetail apsOrderPlan = o.get(0);
            planGroupByOrderVO.setOrderId(apsOrderPlan.getId());
            planGroupByOrderVO.setOrderNo(apsOrderPlan.getApsOrderPlan().getOrder().getOrderNo());
            planGroupByOrderVO.setMaterialId(apsOrderPlan.getApsOrderPlan().getMaterialId());
            planGroupByOrderVO.setMaterialCode(apsOrderPlan.getApsOrderPlan().getMaterialCode());
            planGroupByOrderVO.setMaterialName(apsOrderPlan.getApsOrderPlan().getMaterialName());
            planGroupByOrderVO.setMaterialSpec(apsOrderPlan.getApsOrderPlan().getMaterialSpec());
            planGroupByOrderVO.setMaterialType(MaterialType.transformType(apsOrderPlan.getApsOrderPlan().getMaterial().getType()));
            List<U9ProduceOrderAndMoPick> pickList = moPickListDao.findByDocNoAndMaterialId(planGroupByOrderVO.getOrderNo(), planGroupByOrderVO.getMaterialId());
            BigDecimal pullQty = new BigDecimal(0);
            if (pickList.size() > 0) {
                U9MoPickList u9MoPickList = pickList.get(0).getU9MoPickList();
                U9ProduceOrder u9ProduceOrder = pickList.get(0).getU9ProduceOrder();
                planGroupByOrderVO.setOriginMaterialId(u9MoPickList.getMaterialId());
                planGroupByOrderVO.setOriginMaterialCode(u9MoPickList.getMaterial().getCode());
                planGroupByOrderVO.setOriginMaterialSpec(u9MoPickList.getMaterial().getSpec());
                planGroupByOrderVO.setOriginMaterialName(u9MoPickList.getMaterial().getName());
                planGroupByOrderVO.setOriginMaterialType(MaterialType.transformType(u9MoPickList.getMaterial().getType()));
                planGroupByOrderVO.setBomQty(NumberUtils.getBigDecimalValueAndDefault(u9MoPickList.getQpa(),BigDecimal.ONE));

                String soId = u9ProduceOrder.getSoId();
                List<U9Receive> receives = receiveDao.findByDemandCodeAndMaterialIdAndStatus(soId, u9MoPickList.getMaterialId(), "0");
                BigDecimal tempReceiveQty = receives.stream().map(U9Receive::getArriveQty).reduce(BigDecimal.ZERO, BigDecimal::add);

                planGroupByOrderVO.setTempReceiveQty(tempReceiveQty);
                List<U9Rp> rps = rpDao.findByDemandCodeAndMaterialId(soId, u9ProduceOrder.getMaterialId());
                BigDecimal poQty = new BigDecimal(0);
                for (U9Rp rp : rps) {
                    poQty = NumberUtils.addBigDecimal(poQty, rp.getReqQtyPu());
                }
                planGroupByOrderVO.setPoQty(poQty);
            }
            for (U9ProduceOrderAndMoPick u9MoPickList : pickList) {
                pullQty = NumberUtils.addBigDecimal(pullQty, u9MoPickList.getU9MoPickList().getActualReqQty());
            }
            planGroupByOrderVO.setPullQty(pullQty);

            BigDecimal planQty = new BigDecimal(0);
            Map<LocalDate, BigDecimal> detail = new HashMap<>();
            for (ApsOrderPlanDetail plan : o) {
                planQty = NumberUtils.addBigDecimal(planQty, plan.getPlanQty());
                BigDecimal dayPlan = detail.get(plan.getPlanDate());
                dayPlan = NumberUtils.addBigDecimal(dayPlan, plan.getPlanQty());
                detail.put(plan.getPlanDate(), dayPlan);
            }
            planGroupByOrderVO.setDetails(detail);
            planGroupByOrderVO.setPlanQty(planQty);

            return planGroupByOrderVO;
        })));
        return groupByOrderMap;
    }


    /**
     * 根据原料汇总
     * @param groupByOrders
     * @return
     */
    private Map<String,PlanGroupByMaterialVO> groupByMaterial(List<PlanGroupByOrderVO> groupByOrders){

        Map<String, PlanGroupByMaterialVO> groupByMaterialMap = groupByOrders.stream().filter(o -> StringUtils.isNotEmpty(o.getOriginMaterialCode())).collect(Collectors.groupingBy(PlanGroupByOrderVO::getOriginMaterialCode, Collectors.collectingAndThen(Collectors.toList(), o -> {
            PlanGroupByMaterialVO vo = new PlanGroupByMaterialVO();
            PlanGroupByOrderVO planGroupByOrderVO = o.get(0);
            vo.setMaterialId(planGroupByOrderVO.getOriginMaterialId());
            vo.setMaterialCode(planGroupByOrderVO.getOriginMaterialCode());
            vo.setMaterialName(planGroupByOrderVO.getOriginMaterialName());
            vo.setMaterialSpec(planGroupByOrderVO.getOriginMaterialSpec());
            vo.setMaterialType(planGroupByOrderVO.getOriginMaterialType());
            List<U9Stock> stock = stockDao.findByMaterialId(vo.getMaterialId());
            BigDecimal stockQty = stock.stream().map(U9Stock::getStoreQty).reduce(BigDecimal.ZERO, BigDecimal::add);
            vo.setStockQty(stockQty);
            BigDecimal sumPlanQty = new BigDecimal(0);
            BigDecimal sumPullQty = new BigDecimal(0);
            BigDecimal sumPoQty = new BigDecimal(0);
            BigDecimal sumTempReceiveQty = new BigDecimal(0);
            BigDecimal bomQty = planGroupByOrderVO.getBomQty();
            Map<LocalDate, BigDecimal> groupByMaterialDetails = new HashMap<>();
            for (PlanGroupByOrderVO groupByOrderVO : o) {
                sumPlanQty = NumberUtils.addBigDecimal(sumPlanQty, groupByOrderVO.getPlanQty());
                sumPullQty = NumberUtils.addBigDecimal(sumPullQty, groupByOrderVO.getPullQty());
                sumPoQty = NumberUtils.addBigDecimal(sumPoQty, groupByOrderVO.getPoQty());
                sumTempReceiveQty = NumberUtils.addBigDecimal(sumTempReceiveQty, groupByOrderVO.getTempReceiveQty());
                for (Map.Entry<LocalDate, BigDecimal> entry : groupByOrderVO.getDetails().entrySet()) {
                    BigDecimal value = groupByMaterialDetails.get(entry.getKey());
                    value = NumberUtils.addBigDecimal(value, entry.getValue());
                    groupByMaterialDetails.put(entry.getKey(), value);
                }
            }

            vo.setRequireQty(sumPlanQty.multiply(bomQty).setScale(4,BigDecimal.ROUND_UP));
            vo.setPullQty(sumPullQty);
            vo.setPoQty(sumPoQty);
            vo.setTempReceiveQty(sumTempReceiveQty);

            BigDecimal tempStock = vo.getTempReceiveQty().add(vo.getPullQty()).add(stockQty);
            vo.setBeyondQty(tempStock.subtract(vo.getRequireQty()));
            //转为有序map
            TreeMap<LocalDate, BigDecimal> sortMap = new TreeMap<>(groupByMaterialDetails);
            //计算原料日需求数、欠料日期
            for (Map.Entry<LocalDate, BigDecimal> entry : sortMap.entrySet()) {
                BigDecimal value = NumberUtils.getBigDecimalValue(entry.getValue()).multiply(bomQty).setScale( 4, BigDecimal.ROUND_UP);
                tempStock = tempStock.subtract(value);
                if (tempStock.compareTo(BigDecimal.ZERO) <0){
                    vo.setOweDate(entry.getKey());
                }
                sortMap.put(entry.getKey(),value);
            }
            vo.setDetails(sortMap);

            return vo;
        })));
        return groupByMaterialMap;
    }
}
