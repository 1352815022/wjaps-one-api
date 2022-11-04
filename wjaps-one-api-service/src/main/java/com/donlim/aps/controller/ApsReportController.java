package com.donlim.aps.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.dto.serach.SearchFilter;
import com.changhong.sei.core.service.bo.OperateResultWithData;
import com.donlim.aps.api.ApsReportApi;
import com.donlim.aps.dto.ColumnDto;
import com.donlim.aps.dto.DateRange;
import com.donlim.aps.entity.ApsOweMaterialPlan;
import com.donlim.aps.entity.ApsOweMaterialPlanDetail;
import com.donlim.aps.entity.ApsOweMaterialSummary;
import com.donlim.aps.entity.ApsOweMaterialSummaryDetail;
import com.donlim.aps.service.*;
import com.donlim.aps.util.ColumnUtils;
import com.donlim.aps.util.DateUtils;
import com.donlim.aps.util.NumberUtils;
import com.donlim.aps.vo.ApsOweMaterialPlanVO;
import com.donlim.aps.vo.ApsOweMaterialVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @ClassName ApsReportController
 * @Description 报表统一控制器
 * @Author p09835
 * @Date 2022/6/11 8:01
 **/
@RestController
@Api(value = "ApsReportApi", tags = "报表统一接口")
@RequestMapping(path = ApsReportApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class ApsReportController implements ApsReportApi {

    @Autowired
    private ApsOrderPlanService apsOrderPlanService;

    @Autowired
    private ApsPurchasePlanService apsPurchasePlanService;

    @Autowired
    private ApsOrderPlanDetailService apsOrderPlanDetailService;
    @Autowired
    private ScmXbDeliveryService scmXbDeliveryService;
    @Autowired
    private ApsPurchasePlanDetailService apsPurchasePlanDetailService;
    @Autowired
    private ApsOweMaterialSummaryService apsOweMaterialSummaryService;
    @Autowired
    private ApsOweMaterialSummaryDetailService apsOweMaterialSummaryDetailService;
    @Autowired
    private ApsOweMaterialPlanService apsOweMaterialPlanService;
    @Autowired
    private ApsOweMaterialPlanDetailService apsOweMaterialPlanDetailService;


    /**
     * 导出欠料计划
     * @param search
     * @param response
     * @throws IOException
     */
    @Override
    public void exportOwePlan(Search search, HttpServletResponse response) throws IOException {
        List<List<Object>> datas = apsOweMaterialPlanDetailService.excelData(search);
        List<List<String>> headers = apsOweMaterialPlanDetailService.excelHeader(search);
        try {
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode("欠料计划","utf-8").replace("\\+","$20");
            response.setHeader("Content-disposition","attachment;filename="+fileName+".xlsx");
            EasyExcel.write(response.getOutputStream()).head(headers).autoCloseStream(false).sheet("sheet1").doWrite(datas);
        } catch (IOException e) {
            response.reset();
            response.setCharacterEncoding("utf-8");
            response.getWriter().println(JSON.toJSONString(ResultData.fail("导出excel失败，"+e.getMessage())));
            e.printStackTrace();
        }
    }

    /**
     * 导出欠料汇总
     * @param search
     * @param response
     * @throws IOException
     */
    @Override
    public void exportOweSummary(Search search, HttpServletResponse response) throws IOException {
        List<List<Object>> datas = apsOweMaterialSummaryDetailService.excelData(search);
        List<List<String>> headers = apsOweMaterialSummaryDetailService.excelHeader(search);
        try {
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode("欠料汇总","utf-8").replace("\\+","$20");
            response.setHeader("Content-disposition","attachment;filename="+fileName+".xlsx");
            EasyExcel.write(response.getOutputStream()).head(headers).autoCloseStream(false).sheet("sheet1").doWrite(datas);
        } catch (IOException e) {
            response.reset();
            response.setCharacterEncoding("utf-8");
            response.getWriter().println(JSON.toJSONString(ResultData.fail("导出excel失败，"+e.getMessage())));
            e.printStackTrace();
        }
    }

    /**
     * 分页查找欠料汇总
     * @param search
     * @return
     */
    @Override
    public ResultData<PageResult> findOweSummaryByPage(Search search) {
        return ResultData.success(apsOweMaterialSummaryDetailService.findOweByPage(search));
    }

    /**
     * 分页查找欠料计划
     * @param search
     * @return
     */
    @Override
    public ResultData<PageResult> findOwePlanByPage(Search search) {
        return ResultData.success(apsOweMaterialPlanDetailService.findOweByPage(search));
    }

    /**
     * 计算欠料
     * @param dateRange
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultData calcOweMaterial(DateRange dateRange){
        List<ApsOweMaterialVO> oweMaterialVOS = apsOweMaterialSummaryService.calcOweMaterial(dateRange);
        for (ApsOweMaterialVO oweMaterialVO : oweMaterialVOS) {
            OperateResultWithData<ApsOweMaterialSummary> summarySaveResult = apsOweMaterialSummaryService.save(oweMaterialVO.getSummary());
            ApsOweMaterialSummary apsOweMaterialSummary = summarySaveResult.getData();
            List<ApsOweMaterialPlanVO> plans = oweMaterialVO.getPlan();
            for (ApsOweMaterialPlanVO planVO : plans) {
                ApsOweMaterialPlan plan = planVO.getPlan();
                plan.setSummaryId(apsOweMaterialSummary.getId());
                OperateResultWithData<ApsOweMaterialPlan> planSaveResult = apsOweMaterialPlanService.save(plan);
                ApsOweMaterialPlan planResult = planSaveResult.getData();
                for (ApsOweMaterialPlanDetail planDetail : planVO.getPlanDetails()) {
                    planDetail.setParentId(planResult.getId());
                }
                apsOweMaterialPlanDetailService.save(planVO.getPlanDetails());
            }
            for (ApsOweMaterialSummaryDetail summaryDetail : oweMaterialVO.getSummaryDetails()) {
                summaryDetail.setParentId(apsOweMaterialSummary.getId());
            }
            apsOweMaterialSummaryDetailService.save(oweMaterialVO.getSummaryDetails());
        }
        return ResultData.success();
    }

    /**
     * 获取生产计划汇总报表
     * @param search
     * @return
     */
    @Override
    public ResultData<List<Map<String, Object>>> planGroupByMaterialReport(Search search) {
        List<SearchFilter> filters = search.getFilters();

        LocalDate startDate = LocalDate.now();
        LocalDate endDate =  LocalDate.now();
        for (SearchFilter filter : filters) {
            if ("planDate".equals(filter.getFieldName())){
                if (SearchFilter.Operator.GE.equals(filter.getOperator())){
                    startDate = DateUtils.date2LocalDate((Date) filter.getValue());
                }else if (SearchFilter.Operator.LE.equals(filter.getOperator())){
                    endDate = DateUtils.date2LocalDate((Date) filter.getValue());
                }
            }
        }
        long diff  = endDate.toEpochDay()-startDate.toEpochDay();
        List<Map<String, Object>> orderPlans = apsOrderPlanDetailService.getOrderPlans(search);
        List<Map<String, Object>> orderPlansGroup = group(orderPlans, startDate, diff, "自产");
      //  List<Map<String, Object>> purchasePlans = apsPurchasePlanDetailService.getPurchasePlans(search);
    //    List<Map<String, Object>> purchasePlansGroup = group(purchasePlans, startDate, diff, "委外");

        List<Map<String,Object>> rows = new ArrayList<>();
        rows.addAll(orderPlansGroup);
      //  rows.addAll(purchasePlansGroup);
        List<Map<String, Object>> result = rows.stream().sorted(Comparator.comparing(m -> m.get("materialCode").toString())).collect(Collectors.toList());
        return ResultData.success(result);
    }

    @Override
    public ResultData<List<Map<String, Object>>> requireByMaterial(Search search) {
        List<Map<String, Object>> orderPlans = scmXbDeliveryService.getRequire(search);
        List<Map<String,Object>> rows = new ArrayList<>();
        rows.addAll(orderPlans);
        return ResultData.success(rows);
    }

    /**
     * 获取生产日计划报表
     * @param search
     * @return
     */
    @Override
    public ResultData<List<Map<String, Object>>> dayPlanReport(Search search) {
        List<Map<String, Object>> orderPlans = apsOrderPlanDetailService.getOrderPlans(search);
        //List<Map<String, Object>> purchasePlans = apsPurchasePlanDetailService.getPurchasePlans(search);
        List<Map<String,Object>> rows = new ArrayList<>();
        rows.addAll(orderPlans);
        //rows.addAll(purchasePlans);
        return ResultData.success(rows);
    }

    /**
     * 分组汇总生产计划
     * @param list 生产计划
     * @param start 开始日期
     * @param days 展示日期数
     * @param type 自产/委外
     * @return
     */
    private List<Map<String,Object>> group(List<Map<String,Object>> list, LocalDate start,long days,String type){
        AtomicInteger id = new AtomicInteger(1);
        Map<Object, Map<String, Object>> collect = list.stream().collect(Collectors.groupingBy(m -> m.get("materialCode"), Collectors.collectingAndThen(Collectors.toList(), m -> {
            Map<String, Object> newMap = new HashMap<>();
            Map<String, Object> map = m.get(0);
            if (map == null) {
                return null;
            }
            newMap.put("id",id.getAndIncrement());
            BigDecimal orderQty = m.stream().map(o -> new BigDecimal(o.get("orderQty").toString())).reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal planQty = m.stream().map(o -> new BigDecimal(o.get("planQty").toString())).reduce(BigDecimal.ZERO, BigDecimal::add);
            newMap.put("orderQty", orderQty);
            newMap.put("planQty", planQty);
            newMap.put("type", type);
            newMap.put("materialCode", map.get("materialCode"));
            newMap.put("materialName", map.get("materialName"));
            newMap.put("materialSpec", map.get("materialSpec"));
            for (long i = 0; i <= days; i++) {
                LocalDate localDate = start.plusDays(i);
                String key = DateUtils.LocalDateToString(localDate);
                BigDecimal reduce = m.stream().map(o -> NumberUtils.getBigDecimalValue((BigDecimal) o.get(key))).reduce(BigDecimal.ZERO, BigDecimal::add);
                newMap.put(key, reduce);
            }

            return newMap;
        })));
        return new ArrayList<>(collect.values());
    }

    @Override
    public ResultData<List<ColumnDto>> getColumns(DateRange range) {
        Period between = Period.between(range.getEffectiveFrom(), range.getEffectiveTo());
        long diff = between.getDays() + 1;
        List<ColumnDto> columns = ColumnUtils.getColumnsByDate(diff, range.getEffectiveFrom(), false);
        return ResultData.success(columns);
    }

}
