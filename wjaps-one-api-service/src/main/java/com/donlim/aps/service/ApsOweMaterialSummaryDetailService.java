package com.donlim.aps.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.dao.ApsOweMaterialSummaryDetailDao;
import com.donlim.aps.dto.ColumnDto;
import com.donlim.aps.entity.ApsOweMaterialSummaryDetail;
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
 * 欠料汇总明细表(ApsOweMaterialSummaryDetail)业务逻辑实现类
 *
 * @author sei
 * @since 2022-06-14 11:53:14
 */
@Service
public class ApsOweMaterialSummaryDetailService extends BaseEntityService<ApsOweMaterialSummaryDetail> {
    @Autowired
    private ApsOweMaterialSummaryDetailDao dao;

    @Override
    protected BaseEntityDao<ApsOweMaterialSummaryDetail> getDao() {
        return dao;
    }

    /**
     * 查询欠料跟踪信息
     * @param search
     * @return
     */
    public PageResult findOweByPage(Search search){

        SearchPeriod searchPeriod = ColumnUtils.calcSearchPeriodDate(search, "planDate");
        LocalDate startDate = searchPeriod.getStartDate();
        LocalDate endDate = startDate.plusDays(searchPeriod.getDays());

        PageResult<ApsOweMaterialSummaryDetail> summaryPageResult = this.findByPage(search);

        ArrayList<ApsOweMaterialSummaryDetail> rows = summaryPageResult.getRows();

        Map<String, Map<String, Object>> collect = rows.stream().collect(Collectors.groupingBy(ApsOweMaterialSummaryDetail::getParentId, Collectors.collectingAndThen(Collectors.toList(), r -> {
            Map<String, Object> map = new HashMap<>();
            ApsOweMaterialSummaryDetail apsOweMaterialSummaryDetail = r.get(0);
            map.put("id", apsOweMaterialSummaryDetail.getApsOweMaterialSummary().getId());
            map.put("materialId", apsOweMaterialSummaryDetail.getApsOweMaterialSummary().getMaterialId());
            map.put("materialCode", apsOweMaterialSummaryDetail.getApsOweMaterialSummary().getMaterialCode());
            map.put("materialName", apsOweMaterialSummaryDetail.getApsOweMaterialSummary().getMaterialName());
            map.put("materialSpec", apsOweMaterialSummaryDetail.getApsOweMaterialSummary().getMaterialSpec());
            map.put("materialType", apsOweMaterialSummaryDetail.getApsOweMaterialSummary().getMaterialType().getName());
            map.put("oweDate", DateUtils.LocalDateToString(apsOweMaterialSummaryDetail.getApsOweMaterialSummary().getOweDate()));
            map.put("pullQty", apsOweMaterialSummaryDetail.getApsOweMaterialSummary().getPullQty());
            map.put("stockQty", apsOweMaterialSummaryDetail.getApsOweMaterialSummary().getStockQty());
            map.put("requireQty", apsOweMaterialSummaryDetail.getApsOweMaterialSummary().getRequireQty());
            map.put("tempReceiveQty", apsOweMaterialSummaryDetail.getApsOweMaterialSummary().getTempReceiveQty());
            map.put("beyondQty", apsOweMaterialSummaryDetail.getApsOweMaterialSummary().getBeyondQty());
            map.put("poQty", apsOweMaterialSummaryDetail.getApsOweMaterialSummary().getPoQty());


            for (ApsOweMaterialSummaryDetail oweMaterialSummaryDetail : r) {
                if (oweMaterialSummaryDetail.getPlanDate().compareTo(startDate) >= 0 && oweMaterialSummaryDetail.getPlanDate().compareTo(endDate) <= 0) {
                    map.put(DateUtils.LocalDateToString(oweMaterialSummaryDetail.getPlanDate()), oweMaterialSummaryDetail.getPlanQty());
                }

            }
            return map;
        })));
        List<Map<String,Object>> resultRows = new ArrayList<>(collect.values());
        PageResult pageResult = new PageResult(summaryPageResult);
        pageResult.setRows(resultRows);
        return pageResult;
    }


    /**
     * 获取excel导出实体
     * @param search
     * @return
     */
    public List<List<Object>> excelData(Search search){
        SearchPeriod searchPeriod = ColumnUtils.calcSearchPeriodDate(search, "planDate");
        LocalDate startDate = searchPeriod.getStartDate();
        LocalDate endDate = startDate.plusDays(searchPeriod.getDays());
        List<ApsOweMaterialSummaryDetail> rows = this.findByFilters(search);
        Map<String, Map<String, Object>> collect = rows.stream().collect(Collectors.groupingBy(ApsOweMaterialSummaryDetail::getParentId, Collectors.collectingAndThen(Collectors.toList(), r -> {
            Map<String, Object> map = new HashMap<>();
            ApsOweMaterialSummaryDetail apsOweMaterialSummaryDetail = r.get(0);
            map.put("materialCode", apsOweMaterialSummaryDetail.getApsOweMaterialSummary().getMaterialCode());
            map.put("materialName", apsOweMaterialSummaryDetail.getApsOweMaterialSummary().getMaterialName());
            map.put("materialSpec", apsOweMaterialSummaryDetail.getApsOweMaterialSummary().getMaterialSpec());
            map.put("materialType", apsOweMaterialSummaryDetail.getApsOweMaterialSummary().getMaterialType().getName());
            map.put("oweDate", DateUtils.LocalDateToString(apsOweMaterialSummaryDetail.getApsOweMaterialSummary().getOweDate()));
            map.put("pullQty", apsOweMaterialSummaryDetail.getApsOweMaterialSummary().getPullQty());
            map.put("stockQty", apsOweMaterialSummaryDetail.getApsOweMaterialSummary().getStockQty());
            map.put("requireQty", apsOweMaterialSummaryDetail.getApsOweMaterialSummary().getRequireQty());
            map.put("tempReceiveQty", apsOweMaterialSummaryDetail.getApsOweMaterialSummary().getTempReceiveQty());
            map.put("beyondQty", apsOweMaterialSummaryDetail.getApsOweMaterialSummary().getBeyondQty());
            map.put("poQty", apsOweMaterialSummaryDetail.getApsOweMaterialSummary().getPoQty());
            Map<String, BigDecimal> details= new TreeMap<>();

            for (ApsOweMaterialSummaryDetail oweMaterialSummaryDetail : r) {
                if (oweMaterialSummaryDetail.getPlanDate().compareTo(startDate) >= 0 && oweMaterialSummaryDetail.getPlanDate().compareTo(endDate) <= 0) {
                    details.put(DateUtils.LocalDateToString(oweMaterialSummaryDetail.getPlanDate()), oweMaterialSummaryDetail.getPlanQty());
                }
            }
            map.put("details",details);
            return map;
        })));
        List<List<Object>> result = new ArrayList<>();
        for (Map<String, Object> value : collect.values()) {
            List<Object> row= new ArrayList<>();
            row.add(value.get("materialCode"));
            row.add(value.get("materialName"));
            row.add(value.get("materialSpec"));
            row.add(value.get("materialType"));
            row.add(value.get("pullQty"));
            row.add(value.get("stockQty"));
            row.add(value.get("requireQty"));
            row.add(value.get("tempReceiveQty"));
            row.add(value.get("beyondQty"));
            row.add(value.get("poQty"));
            row.add(value.get("oweDate"));
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
        header.add(Arrays.asList("料号"));
        header.add(Arrays.asList("料名"));
        header.add(Arrays.asList("规格"));
        header.add(Arrays.asList("属性"));
        header.add(Arrays.asList("已领数量"));
        header.add(Arrays.asList("库存数量"));
        header.add(Arrays.asList("需求数量"));
        header.add(Arrays.asList("暂收数量"));
        header.add(Arrays.asList("请购数量"));
        header.add(Arrays.asList("超欠数量"));
        header.add(Arrays.asList("欠料日期"));
        for (ColumnDto planDateCol : planDateCols) {
            header.add(Arrays.asList(planDateCol.getTitle()));
        }
        return header;
    }



}
