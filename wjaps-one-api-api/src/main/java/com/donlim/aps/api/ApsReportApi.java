package com.donlim.aps.api;

import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.donlim.aps.dto.ColumnDto;
import com.donlim.aps.dto.DateRange;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 报表统一接口
 * @FeignClient(name = "apsReport")
 * @author yang jiateng
 */
@FeignClient(name = "wjaps-one-api",path = ApsReportApi.PATH )
public interface ApsReportApi {

    String PATH = "apsReport";

    /**
     * 导出欠料计划
     * @param search
     * @return
     */
    @PostMapping(path = "exportOwePlan",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "导出欠料计划",notes = "导出欠料计划")
    void exportOwePlan(@RequestBody Search search, HttpServletResponse response) throws IOException;


    /**
     * 导出欠料汇总
     * @param search
     * @return
     */
    @PostMapping(path = "exportOweSummary",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "导出欠料汇总",notes = "导出欠料汇总")
    void exportOweSummary(@RequestBody Search search, HttpServletResponse response) throws IOException;

    /**
     * 分页查找欠料汇总
     * @param search
     * @return
     */
    @PostMapping(path = "findOweSummaryByPage",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "查找欠料汇总",notes = "查找欠料汇总")
    ResultData<PageResult> findOweSummaryByPage(@RequestBody Search search);

    /**
     * 分页查找欠料计划
     * @param search
     * @return
     */
    @PostMapping(path = "findOwePlanByPage",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "查找欠料计划",notes = "查找欠料计划")
    ResultData<PageResult> findOwePlanByPage(@RequestBody Search search);

    /**
     * 计算欠料
     * @param dateRange
     * @return
     */
    @PostMapping(path = "calcOweMaterial",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "计算欠料",notes = "计算欠料")
    ResultData calcOweMaterial(@RequestBody DateRange dateRange);

    /**
     * 生产计划汇总报表
     * @param search
     * @return
     */
    @PostMapping(path = "planByMaterial",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "生产计划汇总",notes = "生产计划汇总")
    ResultData<List<Map<String,Object>>> planGroupByMaterialReport(@RequestBody Search search);


    /**
     * 需求汇总报表
     * @param search
     * @return
     */
    @PostMapping(path = "requireByMaterial",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "物料需求计划",notes = "物料需求计划")
    ResultData<List<Map<String,Object>>> requireByMaterial(@RequestBody Search search);

    /**
     * 生产日计划报表
     * @param search
     * @return
     */
    @PostMapping(path = "dayPlan",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "生产日计划",notes = "生产日计划")
    ResultData<List<Map<String,Object>>> dayPlanReport(@RequestBody Search search);

    /**
     * 获取动态列
     * @param range 日期范围
     * @return
     */
    @PostMapping(path = "getCols")
    @ApiOperation(value = "获取动态列",notes = "获取动态列")
    ResultData<List<ColumnDto>> getColumns(@RequestBody DateRange range);
}
