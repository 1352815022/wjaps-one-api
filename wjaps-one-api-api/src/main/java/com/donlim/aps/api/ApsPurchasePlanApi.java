package com.donlim.aps.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.donlim.aps.dto.ApsPurchasePlanAndDetailsDto;
import com.donlim.aps.dto.ApsPurchasePlanDto;
import com.donlim.aps.dto.ColsAndSearch;
import com.donlim.aps.dto.ColumnDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * 采购计划表(ApsPurchasePlan)API
 *
 * @author sei
 * @since 2022-05-20 09:16:30
 * @FeignClient(name = "apsPurchasePlan")
 */
@Valid
@FeignClient(name = "wjaps-one-api", path = ApsPurchasePlanApi.PATH)
public interface ApsPurchasePlanApi extends BaseEntityApi<ApsPurchasePlanDto> {
    String PATH = "apsPurchasePlan";

    /**
     * 分页查询委外计划
     * @param search
     * @return ResultData<PageResult>
     */
    @PostMapping(value = "find",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "分页查询",notes = "分页查询")
    ResultData<PageResult> findPlanByPage(@RequestBody ColsAndSearch search);

    /**
     * 保存委外计划
     * @param dto 委外计划 and 明细dto
     * @return success or fail
     */
    @PostMapping(value = "savePlan",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "保存委外计划",notes = "保存委外计划")
    ResultData<String> savePlan(@RequestBody ApsPurchasePlanAndDetailsDto dto);

    /**
     * 批量保存委外计划
     * @param dtos
     * @return
     */
    @PostMapping(value = "saveAllPlan",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "批量保存委外计划",notes = "批量保存委外计划")
    ResultData<String> savePlans(@RequestBody List<ApsPurchasePlanAndDetailsDto> dtos);

    /**
     * 生成委外排产数据
     * @param params
     * @return
     */
    @PostMapping(value = "initPurchasePlanData",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "生成委外排产数据",notes = "生成委外排产数据")
    ResultData<String> initPurchasePlan();

    /**
     * 委外计划导出
     * @param search
     * @param response
     */
    @PostMapping(path = "export",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "导出excel",notes = "导出excel")
    void export(@RequestBody ColsAndSearch search, HttpServletResponse response) throws IOException;

    /**
     * 获取动态列
     * @param cols 动态列滚动显示天数
     * @return List<ColumnDto>
     */
    @GetMapping(path = "/getCols")
    @ApiOperation(value = "获取动态列",notes = "获取动态列")
    ResultData<List<ColumnDto>> getColumn(@RequestParam(defaultValue = "45") Integer cols);
}
