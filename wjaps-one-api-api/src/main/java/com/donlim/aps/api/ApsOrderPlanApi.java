package com.donlim.aps.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.donlim.aps.dto.ApsOrderPlanAndDetailsDto;
import com.donlim.aps.dto.ApsOrderPlanDto;
import com.donlim.aps.dto.ColsAndSearch;
import com.donlim.aps.dto.ColumnDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 生产计划表(内部)(ApsOrderPlan)API
 *
 * @author sei
 * @since 2022-05-11 15:12:32
 * @FeignClient(name = "apsOrderPlan")
 */
@Valid
@FeignClient(name = "wjaps-one-api", path = ApsOrderPlanApi.PATH)
public interface ApsOrderPlanApi extends BaseEntityApi<ApsOrderPlanDto> {
    String PATH = "apsOrderPlan";

    /**
     * 分页查询生产计划
     * @param search 查询条件
     * @return PageResult
     */
    @PostMapping(path = "find",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "分页查询", notes = "分页查询")
    ResultData<PageResult> findPlanByPage(@RequestBody ColsAndSearch search);

    /**
     * 获取动态列
     * @param cols 动态列滚动显示天数
     * @return List<ColumnDto>
     */
    @GetMapping(path = "/getCols")
    @ApiOperation(value = "获取动态列",notes = "获取动态列")
    ResultData<List<ColumnDto>> getColumn(@RequestParam(defaultValue = "15") Integer cols);

    /**
     * 排产保存
     * @param dto 入参
     * @return ResultData<String> success Or fail
     */
    @PostMapping(path = "/savePlan")
    @ApiOperation(value = "排产保存",notes = "排产保存")
    ResultData<String> savePlan(@RequestBody ApsOrderPlanAndDetailsDto dto);

    /**
     * 批量保存排产
     * @param dtos
     * @return
     */
    @PostMapping(path = "/saveAllPlan")
    @ApiOperation(value = "批量保存排产" , notes = "批量保存排产")
    ResultData<String> saveAllPlan(@RequestBody List<ApsOrderPlanAndDetailsDto> dtos);

    /**
     * 定时产量信息
     * @param params
     * @return
     */
    @PostMapping(path = "updateOrderPlanToFinishTask", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "更新计划表", notes = "每天12点更新把已经完成的订单关闭")
    ResultData updateOrderPlanToFinish(@RequestBody Map<String, String> params);
}
