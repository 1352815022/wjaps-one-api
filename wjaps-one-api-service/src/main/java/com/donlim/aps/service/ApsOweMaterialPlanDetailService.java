package com.donlim.aps.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.dao.ApsOweMaterialPlanDetailDao;
import com.donlim.aps.dto.ColumnDto;
import com.donlim.aps.entity.ApsOweMaterialPlanDetail;
import com.donlim.aps.util.ColumnUtils;
import com.donlim.aps.util.DateUtils;
import com.donlim.aps.vo.SearchPeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 欠料计划明细表(ApsOweMaterialPlanDetail)业务逻辑实现类
 *
 * @author sei
 * @since 2022-06-14 11:53:38
 */
@Service
public class ApsOweMaterialPlanDetailService extends BaseEntityService<ApsOweMaterialPlanDetail> {
    @Autowired
    private ApsOweMaterialPlanDetailDao dao;

    @Override
    protected BaseEntityDao<ApsOweMaterialPlanDetail> getDao() {
        return dao;
    }

    /**
     * 查找欠料计划
     * @param search
     * @return
     */
    public PageResult findOweByPage(Search search){
        SearchPeriod searchPeriod = ColumnUtils.calcSearchPeriodDate(search, "planDate");
        LocalDate startDate = searchPeriod.getStartDate();
        LocalDate endDate = startDate.plusDays(searchPeriod.getDays());

        PageResult<ApsOweMaterialPlanDetail> planPageResult = this.findByPage(search);

        ArrayList<ApsOweMaterialPlanDetail> rows = planPageResult.getRows();
        Map<String, Map<String, Object>> collect = rows.stream().collect(Collectors.groupingBy(ApsOweMaterialPlanDetail::getParentId, Collectors.collectingAndThen(Collectors.toList(), r -> {
            Map<String, Object> map = new HashMap<>();
            ApsOweMaterialPlanDetail apsOweMaterialPlanDetail = r.get(0);
            map.put("materialId", apsOweMaterialPlanDetail.getApsOweMaterialPlan().getMaterialId());
            map.put("id", apsOweMaterialPlanDetail.getApsOweMaterialPlan().getId());
            map.put("materialCode", apsOweMaterialPlanDetail.getApsOweMaterialPlan().getMaterialCode());
            map.put("materialName", apsOweMaterialPlanDetail.getApsOweMaterialPlan().getMaterialName());
            map.put("materialSpec", apsOweMaterialPlanDetail.getApsOweMaterialPlan().getMaterialSpec());
            map.put("orderNo", apsOweMaterialPlanDetail.getApsOweMaterialPlan().getOrderNo());
            map.put("originMaterial", apsOweMaterialPlanDetail.getApsOweMaterialPlan().getOriginMaterial());
            map.put("oweQty", apsOweMaterialPlanDetail.getApsOweMaterialPlan().getOweQty());
            map.put("planQty", apsOweMaterialPlanDetail.getApsOweMaterialPlan().getPlanQty());
            map.put("requireQty", apsOweMaterialPlanDetail.getApsOweMaterialPlan().getRequireQty());
            map.put("summaryId", apsOweMaterialPlanDetail.getApsOweMaterialPlan().getSummaryId());
            map.put("unit", apsOweMaterialPlanDetail.getApsOweMaterialPlan().getUnit());
            map.put("workGroup", apsOweMaterialPlanDetail.getApsOweMaterialPlan().getWorkGroup());
            map.put("pullQty", apsOweMaterialPlanDetail.getApsOweMaterialPlan().getPullQty());
            for (ApsOweMaterialPlanDetail oweMaterialPlanDetail : r) {
                if (oweMaterialPlanDetail.getPlanDate().compareTo(startDate) >= 0 && oweMaterialPlanDetail.getPlanDate().compareTo(endDate) <= 0) {
                    map.put(DateUtils.LocalDateToString(oweMaterialPlanDetail.getPlanDate()), oweMaterialPlanDetail.getPlanQty());
                }
            }
            return map;
        })));

        List<Map<String, Object>> resultList = new ArrayList<>(collect.values());
        PageResult pageResult = new PageResult(planPageResult);
        pageResult.setRows(resultList);
        return pageResult;
    }

    /**
     * excel导出数据
     * @param search
     * @return
     */
    public List<List<Object>> excelData(Search search){
        SearchPeriod searchPeriod = ColumnUtils.calcSearchPeriodDate(search, "planDate");
        LocalDate startDate = searchPeriod.getStartDate();
        LocalDate endDate = startDate.plusDays(searchPeriod.getDays());
        List<ApsOweMaterialPlanDetail> rows = this.findByFilters(search);

        Map<String, Map<String, Object>> collect = rows.stream().collect(Collectors.groupingBy(ApsOweMaterialPlanDetail::getParentId, Collectors.collectingAndThen(Collectors.toList(), r -> {
            Map<String, Object> map = new HashMap<>();
            ApsOweMaterialPlanDetail apsOweMaterialPlanDetail = r.get(0);
            map.put("materialId", apsOweMaterialPlanDetail.getApsOweMaterialPlan().getMaterialId());
            map.put("id", apsOweMaterialPlanDetail.getApsOweMaterialPlan().getId());
            map.put("materialCode", apsOweMaterialPlanDetail.getApsOweMaterialPlan().getMaterialCode());
            map.put("materialName", apsOweMaterialPlanDetail.getApsOweMaterialPlan().getMaterialName());
            map.put("materialSpec", apsOweMaterialPlanDetail.getApsOweMaterialPlan().getMaterialSpec());
            map.put("orderNo", apsOweMaterialPlanDetail.getApsOweMaterialPlan().getOrderNo());
            map.put("originMaterial", apsOweMaterialPlanDetail.getApsOweMaterialPlan().getOriginMaterial());
            map.put("oweQty", apsOweMaterialPlanDetail.getApsOweMaterialPlan().getOweQty());
            map.put("planQty", apsOweMaterialPlanDetail.getApsOweMaterialPlan().getPlanQty());
            map.put("requireQty", apsOweMaterialPlanDetail.getApsOweMaterialPlan().getRequireQty());
            map.put("summaryId", apsOweMaterialPlanDetail.getApsOweMaterialPlan().getSummaryId());
            map.put("unit", apsOweMaterialPlanDetail.getApsOweMaterialPlan().getUnit());
            map.put("workGroup", apsOweMaterialPlanDetail.getApsOweMaterialPlan().getWorkGroup());
            map.put("pullQty", apsOweMaterialPlanDetail.getApsOweMaterialPlan().getPullQty());
            Map<String, BigDecimal> details = new TreeMap<>();

            for (ApsOweMaterialPlanDetail detail : r) {
                if (detail.getPlanDate().compareTo(startDate) >= 0 && detail.getPlanDate().compareTo(endDate) <= 0) {
                    details.put(DateUtils.LocalDateToString(detail.getPlanDate()), detail.getPlanQty());
                }
            }
            map.put("details", details);
            return map;
        })));

        List<List<Object>> result = new ArrayList<>();
        for (Map<String, Object> value : collect.values()) {
            List<Object> row= new ArrayList<>();
            row.add(value.get("orderNo"));
            row.add(value.get("materialCode"));
            row.add(value.get("materialName"));
            row.add(value.get("materialSpec"));
            row.add(value.get("materialType"));
            row.add(value.get("planQty"));
            row.add(value.get("pullQty"));
            row.add(value.get("requireQty"));
            row.add(value.get("oweQty"));
            TreeMap details = (TreeMap) value.get("details");
            for (Object o : details.keySet()) {
                row.add(details.get(o));
            }
            result.add(row);
        }
        return result;
    }

    /**
     * 获取excel字段头
     * @param search
     * @return
     */
    public List<List<String>> excelHeader(Search search){
        List<ColumnDto> planDateCols = ColumnUtils.getColumnsByDate(ColumnUtils.calcSearchPeriodDate(search, "planDate"));
        List<List<String>> header = new ArrayList<>();
        header.add(Arrays.asList("工单号"));
        header.add(Arrays.asList("料号"));
        header.add(Arrays.asList("料名"));
        header.add(Arrays.asList("规格"));
        header.add(Arrays.asList("属性"));
        header.add(Arrays.asList("排产数量"));
        header.add(Arrays.asList("已领数量"));
        header.add(Arrays.asList("需求数量"));
        header.add(Arrays.asList("超欠数量"));
        for (ColumnDto planDateCol : planDateCols) {
            header.add(Arrays.asList(planDateCol.getTitle()));
        }
        return header;
    }

}
