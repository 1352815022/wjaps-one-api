package com.donlim.aps.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.dto.serach.SearchFilter;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.dao.ApsOrderPlanDao;
import com.donlim.aps.dao.ApsOrderPlanDetailDao;
import com.donlim.aps.dto.ApsDayPlanReportDto;
import com.donlim.aps.dto.DateRange;
import com.donlim.aps.entity.ApsOrderPlanDetail;
import com.donlim.aps.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;


/**
 * 生产计划明细表(ApsOrderPlanDetail)业务逻辑实现类
 *
 * @author sei
 * @since 2022-05-16 08:42:08
 */
@Service
public class ApsOrderPlanDetailService extends BaseEntityService<ApsOrderPlanDetail> {
    @Autowired
    private ApsOrderPlanDetailDao dao;
    @Autowired
    private ApsOrderPlanDao apsOrderPlanDao;

    @Override
    protected BaseEntityDao<ApsOrderPlanDetail> getDao() {
        return dao;
    }

    /**
     * 根据排产日期范围获取内排单ID并获取对应的排产单
     *
     * @param range
     * @return
     */
    public List<ApsOrderPlanDetail> findPlanBetweenDate(DateRange range) {
        List<String> orderIds = dao.queryDistinctBetweenPlanDate(range.getEffectiveFrom(), range.getEffectiveTo());
        List<ApsOrderPlanDetail> plans = dao.findByApsOrderPlanOrderIdIn(orderIds);
        return plans;
    }

    /**
     * 保存排产明细信息
     * 过滤不存在数据库的记录并值为0的
     *
     * @param parentId
     * @param details
     * @return
     */
    public BigDecimal saveDetail(String parentId, Map<String, BigDecimal> details) {
        List<ApsOrderPlanDetail> orderPlanDetails = new ArrayList<>();
        BigDecimal planQty = BigDecimal.ZERO;
        for (Map.Entry<String, BigDecimal> entry : details.entrySet()) {
            if (entry.getValue() != null) {
                LocalDate planDate = LocalDate.parse(entry.getKey());
                ApsOrderPlanDetail detail = dao.findTopByPlanIdAndPlanDate(parentId, planDate);
                if (detail == null) {
                    if (entry.getValue().compareTo(BigDecimal.ZERO) > 0) {
                        detail = new ApsOrderPlanDetail();
                        detail.setPlanId(parentId);
                        detail.setPlanQty(entry.getValue());
                        detail.setPlanDate(planDate);
                        orderPlanDetails.add(detail);
                    }
                } else {
                    detail.setPlanQty(entry.getValue());
                    orderPlanDetails.add(detail);
                }
                planQty = planQty.add(entry.getValue());
            }
        }
        this.save(orderPlanDetails);
        return planQty;
    }

    /**
     * 获取自产生产计划
     *
     * @param search
     * @return
     */
    public List<Map<String, Object>> getOrderPlans(Search search) {
        Map<String, Map<String, Object>> resultMap = new HashMap<>();
        List<SearchFilter> filters = search.getFilters();
        ApsDayPlanReportDto apsDayPlanReportDto = new ApsDayPlanReportDto();
        for (SearchFilter filter : filters) {
            if (filter.getFieldName().equals("orderNo")) {
                apsDayPlanReportDto.setOrderNo(filter.getValue().toString());
            }
            if (filter.getFieldName().equals("materialCode")) {
                apsDayPlanReportDto.setMaterialCode(filter.getValue().toString());
            }
            if (filter.getFieldName().equals("materialName")) {
                apsDayPlanReportDto.setMaterialName(filter.getValue().toString());
            }
            if (filter.getFieldName().equals("materialSpec")) {
                apsDayPlanReportDto.setMaterialSpec(filter.getValue().toString());
            }
            if (filter.getFieldName().equals("workGroupId")) {
                apsDayPlanReportDto.setWorkGroupId(filter.getValue().toString());
            }
            if (filter.getFieldName().equals("workLineId")) {
                apsDayPlanReportDto.setWorkLineId(filter.getValue().toString());
            }
            if (filter.getFieldName().equals("planDate")) {
                switch (filter.getOperator()) {
                    case EQ:
                        apsDayPlanReportDto.setStartDate(DateUtils.date2LocalDate((Date) filter.getValue()));
                        apsDayPlanReportDto.setEndDate(DateUtils.date2LocalDate((Date) filter.getValue()));
                        break;
                    case GE:
                        apsDayPlanReportDto.setStartDate(DateUtils.date2LocalDate((Date) filter.getValue()));
                        break;
                    case LE:
                        apsDayPlanReportDto.setEndDate(DateUtils.date2LocalDate((Date) filter.getValue()));
                        break;
                    default:
                        break;
                }
            }
        }
        if(apsDayPlanReportDto.getStartDate()==null){
            apsDayPlanReportDto.setEndDate(LocalDate.now());
            apsDayPlanReportDto.setStartDate(LocalDate.now());
        }
        List<ApsOrderPlanDetail> orderPlans = dao.queryPlan(apsDayPlanReportDto);
        for (ApsOrderPlanDetail orderPlan : orderPlans) {
            Map<String, Object> row = resultMap.get(orderPlan.getPlanId());
            if (row == null) {
                row = new HashMap<>();
                row.put("type", "自产");
                row.put("orderNo", orderPlan.getApsOrderPlan().getOrder().getOrderNo());
                row.put("id", orderPlan.getApsOrderPlan().getId());
                row.put("orderQty", orderPlan.getApsOrderPlan().getOrder().getOrderQty());
                row.put("planNum", orderPlan.getApsOrderPlan().getPlanNum());
                row.put("workGroupName", orderPlan.getApsOrderPlan().getWorkGroupName());
                row.put("lineName", orderPlan.getApsOrderPlan().getLineName());
                row.put("materialCode", orderPlan.getApsOrderPlan().getMaterialCode());
                row.put("materialName", orderPlan.getApsOrderPlan().getMaterialName());
                row.put("materialSpec", orderPlan.getApsOrderPlan().getMaterialSpec());
                row.put("productionDate", orderPlan.getApsOrderPlan().getProductionDate());
                row.put("scmDeliveryDate", orderPlan.getApsOrderPlan().getScmDeliveryDate());
                row.put("orderDate", orderPlan.getApsOrderPlan().getOrderDate());
                row.put("planQty", BigDecimal.ZERO);
            }
            row.put(DateUtils.LocalDateToString(orderPlan.getPlanDate()), orderPlan.getPlanQty());
            row.put("planQty",new BigDecimal(row.get("planQty").toString()).add(orderPlan.getPlanQty()));
            resultMap.put(orderPlan.getPlanId(), row);
        }
        return new ArrayList<>(resultMap.values());
    }


}
