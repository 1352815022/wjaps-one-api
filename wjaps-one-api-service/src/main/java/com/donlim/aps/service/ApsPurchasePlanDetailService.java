package com.donlim.aps.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.dto.serach.SearchFilter;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.dao.ApsPurchasePlanDetailDao;
import com.donlim.aps.entity.ApsPurchasePlanDetail;
import com.donlim.aps.util.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 采购计划明细表(ApsPurchasePlanDetail)业务逻辑实现类
 *
 * @author sei
 * @since 2022-05-23 08:20:14
 */
@Service
public class ApsPurchasePlanDetailService extends BaseEntityService<ApsPurchasePlanDetail> {
    @Autowired
    private ApsPurchasePlanDetailDao dao;

    @Override
    protected BaseEntityDao<ApsPurchasePlanDetail> getDao() {
        return dao;
    }

    public BigDecimal savePlan(String id, Map<String,BigDecimal> details){
        List<ApsPurchasePlanDetail> purchasePlanDetails = new ArrayList<>();
        BigDecimal planQty = BigDecimal.ZERO;
        for (Map.Entry<String, BigDecimal> entry : details.entrySet()) {
            if (entry.getValue() != null ){
                LocalDate planDate = LocalDate.parse(entry.getKey());
                ApsPurchasePlanDetail detail = dao.findTopByPlanIdAndPlanDate(id, planDate);
                if (detail == null){
                    detail = new ApsPurchasePlanDetail();
                    detail.setPlanId(id);
                    detail.setPlanQty(entry.getValue());
                    detail.setPlanDate(planDate);
                }else{
                    detail.setPlanQty(entry.getValue());
                }
                planQty = planQty.add(entry.getValue());
                purchasePlanDetails.add(detail);
            }
        }
        this.save(purchasePlanDetails);
        return planQty;
    }

    /**
     * 获取委外生产计划
     * @param search
     * @return
     */
    public List<Map<String,Object>> getPurchasePlans(Search search){
        Map<String,Map<String,Object>> resultMap = new HashMap<>();
        List<SearchFilter> filters = search.getFilters();
        String OrderNo="";
        List<SearchFilter>removeFilter=new ArrayList<>();
        for(SearchFilter filter :filters){
            if(filter.getFieldName().equals("orderNo")){
                OrderNo=filter.getValue().toString();
                removeFilter.add(filter);
            }
        }

        filters.removeAll(removeFilter);
        search.setFilters(filters);
        List<ApsPurchasePlanDetail> purchasePlans = this.findByFilters(search);
        for (ApsPurchasePlanDetail purchasePlan : purchasePlans) {
            Map<String,Object> row = resultMap.get(purchasePlan.getPlanId());
            if (row == null){
                if(StringUtils.isNotEmpty(OrderNo) && StringUtils.isNotEmpty(purchasePlan.getApsPurchasePlan().getOrder().getOrderNo()) &&!purchasePlan.getApsPurchasePlan().getOrder().getOrderNo().contains(OrderNo)){
                    continue;
                }
                row = new HashMap<>();
                row.put("type","委外");
                row.put("orderNo",purchasePlan.getApsPurchasePlan().getOrder().getOrderNo());

                row.put("id",purchasePlan.getApsPurchasePlan().getId());
                row.put("orderQty",purchasePlan.getApsPurchasePlan().getOrder().getOrderQty());
                row.put("supplierCode",purchasePlan.getApsPurchasePlan().getSupplierCode());
                row.put("supplierName",purchasePlan.getApsPurchasePlan().getSupplierName());
                row.put("materialCode",purchasePlan.getApsPurchasePlan().getMaterialCode());
                row.put("materialName",purchasePlan.getApsPurchasePlan().getMaterialName());
                row.put("materialSpec",purchasePlan.getApsPurchasePlan().getMaterialSpec());
                row.put("scmDeliveryDate",purchasePlan.getApsPurchasePlan().getDeliveryStartDate());
                row.put("orderDate",purchasePlan.getApsPurchasePlan().getOrderDate());
                row.put("planQty",purchasePlan.getApsPurchasePlan().getPlanQty());
            }
            row.put(DateUtils.LocalDateToString(purchasePlan.getPlanDate()),purchasePlan.getPlanQty());
            resultMap.put(purchasePlan.getPlanId(),row);
        }
        return new ArrayList<>(resultMap.values());
    }
}
