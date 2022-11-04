package com.donlim.aps.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.dto.serach.SearchFilter;
import com.changhong.sei.core.service.BaseEntityService;
import com.changhong.sei.core.service.bo.OperateResultWithData;
import com.donlim.aps.dao.*;
import com.donlim.aps.dto.ApsOrderType;
import com.donlim.aps.dto.CalcBomDto;
import com.donlim.aps.entity.*;
import com.donlim.aps.entity.cust.OrderAndPurchasePlan;
import com.donlim.aps.entity.cust.OrderAndScm;
import com.donlim.aps.util.DateUtils;
import com.donlim.aps.util.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;


/**
 * 采购计划表(ApsPurchasePlan)业务逻辑实现类
 *
 * @author sei
 * @since 2022-05-20 09:16:30
 */
@Service
public class ApsPurchasePlanService extends BaseEntityService<ApsPurchasePlan> {
    @Autowired
    private ApsPurchasePlanDao dao;
    @Autowired
    private ScmXbDeliveryDao scmXbDeliveryDao;
    @Autowired
    private ApsOrderDao apsOrderDao;
    @Autowired
    private ApsPurchasePlanDetailDao apsPurchasePlanDetailDao;

    @Autowired
    private U9MaterialDao u9MaterialDao;
    @Override
    protected BaseEntityDao<ApsPurchasePlan> getDao() {
        return dao;
    }


    /**
     * 导出excel数据
     *
     * @param search
     * @param cols
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public List<List<Object>> findByFiltersForExport(Search search, Integer cols) {
        search.addFilter(new SearchFilter("endDate", LocalDate.now().minusDays(1), SearchFilter.Operator.GE));
        search.addFilter(new SearchFilter("endDate", LocalDate.now().plusDays(31), SearchFilter.Operator.LE));
        List<ApsPurchasePlan> byFilters = this.findByFilters(search);
        LocalDate now = LocalDate.now();
        LocalDate end = now.plusDays(cols);
        List<List<Object>> result = new ArrayList<>();
        for (ApsPurchasePlan plan : byFilters) {
            List<Object> row = new ArrayList<>();
            row.add(plan.getSoNo());
            row.add(plan.getOrderNo());
            row.add(plan.getPoLineNo());
            row.add(plan.getMaterialCode());
            row.add(plan.getMaterialName());
            row.add(plan.getMaterialSpec());
            row.add(plan.getProductModel());
            row.add(plan.getUnit());
            row.add(plan.getSupplierCode());
            row.add(plan.getSupplierName());
            row.add(plan.getBuyer());
            row.add(DateUtils.LocalDateToString(plan.getDeliveryStartDate()));
            row.add(DateUtils.LocalDateToString(plan.getDeliveryEndDate()));
            row.add(DateUtils.LocalDateToString(plan.getStartDate()));
            row.add(DateUtils.LocalDateToString(plan.getEndDate()));
            row.add(plan.getPlanQty());
            row.add(plan.getSumArrivalQty());
            row.add(plan.getOweQty());
            row.add(plan.getRemark());
            List<ApsPurchasePlanDetail> purchasePlanDetails = plan.getPurchasePlanDetails();
            for (ApsPurchasePlanDetail detail : purchasePlanDetails) {
                LocalDate planDate = detail.getPlanDate();
                if (planDate.isAfter(now) && planDate.isBefore(end)) {
                    row.add(detail.getPlanQty());
                }
            }
            result.add(row);
        }
        return result;
    }

    /**
     * 生成委外计划
     */
    @Transactional(rollbackFor = Exception.class)
    public void copyScmOrder() {
        List<ApsPurchasePlan> purchasePlans = new ArrayList<>();
        List<OrderAndPurchasePlan> updateList = apsOrderDao.queryPurchaseOrderForUpdate();
        for (OrderAndPurchasePlan o : updateList) {
            ApsPurchasePlan apsPurchasePlan = o.getApsPurchasePlan();
            ApsOrder apsOrder = o.getApsOrder();
            apsPurchasePlan.setOweQty(apsOrder.getOweQty());
            apsPurchasePlan.setDeliveryStartDate(apsOrder.getDeliveryStartDate());
            apsPurchasePlan.setDeliveryEndDate(apsOrder.getDeliveryEndDate());
            apsPurchasePlan.setSumArrivalQty(NumberUtils.subtractBigDecimal(apsPurchasePlan.getPlanQty(), apsPurchasePlan.getOweQty()));
            purchasePlans.add(apsPurchasePlan);
        }

        //先把送货结束日期大于今天的删除，再重新拉取数据
        List<String> orderIdList = apsOrderDao.findByDeliveryEndDateAfterAndType(LocalDate.now().minusDays(1), ApsOrderType.OUTER).stream().map(ApsOrder::getId).collect(Collectors.toList());
        List<String> planIdList = dao.findByOrderIdIn(orderIdList).stream().map(ApsPurchasePlan::getId).collect(Collectors.toList());
        apsPurchasePlanDetailDao.deleteByPlanIdIn(planIdList);
        dao.deleteByOrderIdIn(orderIdList);
        List<OrderAndScm> addList = apsOrderDao.queryPurchaseOrderForAdd(orderIdList);
        Map<String, List<ApsPurchasePlanDetail>> details = new HashMap<>();
        for (OrderAndScm orderAndScm : addList) {
            ApsPurchasePlan entity = new ApsPurchasePlan();
            U9Material u9Material = orderAndScm.getU9Material();
            int fixedLt = NumberUtils.getBigDecimalValue(u9Material.getFixedLt()).intValue();
            ScmXbDelivery scmXbDelivery = orderAndScm.getScmXbDelivery();
            ApsOrder apsOrder = orderAndScm.getApsOrder();
            entity.setOrderId(apsOrder.getId());
            entity.setOrderNo(apsOrder.getPoNo());
            entity.setSoNo(apsOrder.getOrderNo());
            entity.setMaterialId(apsOrder.getMaterialId());
            entity.setMaterialCode(apsOrder.getMaterialCode());
            entity.setMaterialName(apsOrder.getMaterialName());
            entity.setMaterialSpec(apsOrder.getMaterialSpec());
            entity.setDeliveryStartDate(scmXbDelivery.getDeliveryStartDate());
            entity.setDeliveryEndDate(scmXbDelivery.getDeliveryEndDate());
            entity.setStartDate(scmXbDelivery.getDeliveryStartDate().minusDays(fixedLt));
            entity.setEndDate(scmXbDelivery.getDeliveryEndDate().minusDays(fixedLt));
            entity.setSupplierCode(apsOrder.getCustomerCode());
            entity.setSupplierName(apsOrder.getCustomerName());
            entity.setPlanQty(apsOrder.getOrderQty());
            entity.setOweQty(apsOrder.getOweQty());
            entity.setBuyer(scmXbDelivery.getBuyer());
            entity.setProductModel(u9Material.getProductModel());
            entity.setSumArrivalQty((scmXbDelivery.getDeliveryQty().subtract(scmXbDelivery.getOweQty())));
            List<ScmXbDeliveryPlan> deliveryPlans = scmXbDelivery.getDeliveryPlans();
            List<ApsPurchasePlanDetail> detail = new ArrayList<>();
            BigDecimal tempQty = new BigDecimal(0);
            for (ScmXbDeliveryPlan deliveryPlan : deliveryPlans) {
                ApsPurchasePlanDetail apsPurchasePlanDetail = new ApsPurchasePlanDetail();
                BigDecimal qty = deliveryPlan.getQty().setScale(0, RoundingMode.HALF_UP);
                apsPurchasePlanDetail.setPlanQty(qty);
                apsPurchasePlanDetail.setPlanDate(deliveryPlan.getDeliveryDate().minusDays(fixedLt));
                tempQty = tempQty.add(qty);
                detail.add(apsPurchasePlanDetail);
            }
            details.put(entity.getOrderId(), detail);
            purchasePlans.add(entity);
        }
        this.save(purchasePlans);
        for (ApsPurchasePlan purchasePlan : purchasePlans) {
            List<ApsPurchasePlanDetail> purchasePlanDetails = details.get(purchasePlan.getOrderId());
            if (Objects.isNull(purchasePlanDetails)) {
                continue;
            }
            for (ApsPurchasePlanDetail purchasePlanDetail : purchasePlanDetails) {
                purchasePlanDetail.setPlanId(purchasePlan.getId());
            }
            apsPurchasePlanDetailDao.save(purchasePlanDetails);
        }
    }

    /**
     * 获取委外排产计划列表
     *
     * @param search 过滤条件
     * @param cols   显示排产日期数 从当天开始往后推
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public PageResult findPlanByPage(Search search, Integer cols) {
        //取出未结束的记录
        search.addFilter(new SearchFilter("endDate", LocalDate.now().minusDays(15), SearchFilter.Operator.GE));
        search.addFilter(new SearchFilter("endDate", LocalDate.now().plusDays(31), SearchFilter.Operator.LE));
        PageResult<ApsPurchasePlan> plans = this.findByPage(search);
        LocalDate now = LocalDate.now();
        ArrayList<ApsPurchasePlan> rows = plans.getRows();
        List<Map> colsRows = new ArrayList<>();
        for (ApsPurchasePlan row : rows) {
            Map<String, Object> map = toMapDto(row);
            List<ApsPurchasePlanDetail> purchasePlanDetails = row.getPurchasePlanDetails();
            for (ApsPurchasePlanDetail purchasePlanDetail : purchasePlanDetails) {
                LocalDate planDate = purchasePlanDetail.getPlanDate();
                map.put(planDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), purchasePlanDetail.getPlanQty());
            }
            colsRows.add(map);
        }
        PageResult pageResult = new PageResult(plans);
        pageResult.setRows(colsRows);
        return pageResult;

    }

    /**
     * 计算委外采购计划
     *
     * @param calcBomDtoList
     */
    @Transactional(rollbackFor = Exception.class)
    public void calcPurchasePlan(List<CalcBomDto> calcBomDtoList, LocalDate localDate) {
        //先删除数据
        List<String> planIds = dao.findByStartDateAfter(localDate).stream().map(a -> a.getId()).collect(Collectors.toList());
        //找出涉及到的采购单号
        List<String> orderNos = calcBomDtoList.stream().map(a -> a.getDocNo()).collect(Collectors.toList());
        List<String> orderNoPlanIds = dao.findByOrderNoIn(orderNos).stream().map(a -> a.getId()).collect(Collectors.toList());
        planIds.addAll(orderNoPlanIds);
        apsPurchasePlanDetailDao.deleteByPlanIdIn(planIds);
        dao.delete(planIds);
        List<ApsPurchasePlanDetail>apsPurchasePlanDetails=new ArrayList<>();
        Map<String, List<CalcBomDto>> groupByDocNo = calcBomDtoList.stream().collect(groupingBy(a->a.getDocNo()+a.getMaterialCode()));
        groupByDocNo.forEach((docNo, details) -> {
            ApsPurchasePlan apsPurchasePlan=new ApsPurchasePlan();
            LocalDate startDate = details.stream().min(Comparator.comparing(CalcBomDto::getPlanDate)).get().getPlanDate();
            LocalDate endDate = details.stream().max(Comparator.comparing(CalcBomDto::getPlanDate)).get().getPlanDate();
            apsPurchasePlan.setStartDate(startDate);
            apsPurchasePlan.setEndDate(endDate);
            CalcBomDto first = details.get(0);
            apsPurchasePlan.setSoNo(first.getU9Purchase().getDemandCode());
            apsPurchasePlan.setSumArrivalQty(first.getU9Purchase().getReceiveQty());
            apsPurchasePlan.setOweQty(first.getU9Purchase().getReqQty().subtract(first.getU9Purchase().getReceiveQty()));
            apsPurchasePlan.setPlanQty(first.getU9Purchase().getReqQty());
            apsPurchasePlan.setOrderNo(first.getU9Purchase().getDocNo());
            apsPurchasePlan.setBuyer(first.getU9Purchase().getCreatorName());
            apsPurchasePlan.setMaterialName(first.getU9Purchase().getMaterialName());
            apsPurchasePlan.setMaterialCode(first.getU9Purchase().getMaterialCode());
            apsPurchasePlan.setMaterialId(first.getU9Purchase().getMaterialId());
            apsPurchasePlan.setSupplierName(first.getU9Purchase().getSupplierName());
            U9Material u9Material = u9MaterialDao.findById(apsPurchasePlan.getMaterialId() + "").get();
            apsPurchasePlan.setProductModel(u9Material.getProductModel());
            apsPurchasePlan.setMaterialSpec(u9Material.getSpec());
            apsPurchasePlan.setDeliveryStartDate(first.getDeliveryStartDate());
            apsPurchasePlan.setDeliveryEndDate(first.getDeliveryEndDate());

            OperateResultWithData<ApsPurchasePlan> save = save(apsPurchasePlan);

            if(save.successful()){
                for (CalcBomDto detail : details) {
                    ApsPurchasePlanDetail apsPurchasePlanDetail=new ApsPurchasePlanDetail();
                    apsPurchasePlanDetail.setPlanQty(detail.getQty());
                    apsPurchasePlanDetail.setPlanDate(detail.getPlanDate());
                    apsPurchasePlanDetail.setPlanId(save.getData().getId());
                    apsPurchasePlanDetails.add(apsPurchasePlanDetail);
                }
            }
        });
        apsPurchasePlanDetailDao.save(apsPurchasePlanDetails);
    }

    private Map<String, Object> toMapDto(ApsPurchasePlan row) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", row.getId());
        map.put("orderId", row.getOrderId());
        map.put("soNo", row.getSoNo());
        map.put("orderNo", row.getOrderNo());
        map.put("orderDate", row.getOrderDate());
        map.put("poLineNo", row.getPoLineNo());
        map.put("materialId", row.getMaterialId());
        map.put("materialCode", row.getMaterialCode());
        map.put("materialName", row.getMaterialName());
        map.put("materialSpec", row.getMaterialSpec());
        map.put("materialType", row.getMaterialType());
        map.put("supplierCode", row.getSupplierCode());
        map.put("supplierName", row.getSupplierName());
        map.put("unit", row.getUnit());
        map.put("buyer", row.getBuyer());
        map.put("productModel", row.getProductModel());
        map.put("deliveryStartDate", DateUtils.LocalDateToString(row.getDeliveryStartDate()));
        map.put("deliveryEndDate", DateUtils.LocalDateToString(row.getDeliveryEndDate()));
        map.put("startDate", DateUtils.LocalDateToString(row.getStartDate()));
        map.put("endDate", DateUtils.LocalDateToString(row.getEndDate()));
        map.put("planQty", row.getPlanQty());
        map.put("awaitQty", row.getAwaitQty());
        map.put("oweQty", row.getOweQty());
        map.put("remark", row.getRemark());
        map.put("unit", row.getUnit());
        map.put("sumArrivalQty", row.getSumArrivalQty());
        map.put("tenantCode", row.getTenantCode());
        map.put("status", row.getStatus());

        return map;

    }


}
