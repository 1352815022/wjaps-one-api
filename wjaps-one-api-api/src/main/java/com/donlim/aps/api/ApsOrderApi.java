package com.donlim.aps.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.changhong.sei.core.api.FindByPageApi;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.Search;
import com.donlim.aps.dto.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 * 订单表(ApsOrder)API
 *
 * @author sei
 * @since 2022-05-10 15:29:59
 * @FeignClient(name = "apsOrder")
 */
@Valid
@FeignClient(name = "wjaps-one-api", path = ApsOrderApi.PATH)
public interface ApsOrderApi extends BaseEntityApi<ApsOrderDto> , FindByPageApi<ApsOrderDto> {
    String PATH = "apsOrder";

    /**
     * 修改单据状态 ，状态： 取消 cancel ；暂停 stop；
     * @param dtos
     * @return
     */
    @PostMapping(path = "changeOrderStatus" , consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "改变单据状态" ,notes = "改变单据状态")
    ResultData<String> changeOrderStatus(@RequestBody List<ApsOrderDto> dtos);

    /**
     * 内部待排下达
     * @param dto
     * @return
     */
    @PostMapping(path = "plan", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "计划下达", notes = "计划下达")
    ResultData<String> plan(@RequestBody @Valid ApsOrderDto dto) throws Exception;

    /**
     * 内部待排批量下达
     * @param dtos
     * @return
     */
    @PostMapping(path = "batchPlan", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "批量下达", notes = "批量下达")
    ResultData<String> batchPlan(@RequestBody @Valid List<ApsOrderDto> dtos) throws Exception;

    /**
     * 组件拆分并合并
     * @param range 日期范围
     * @return
     */
    @PostMapping(path = "splitAndMerge",consumes = MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "组件拆分",notes = "组件拆分")
    ResultData<String> splitAndMerge(@RequestBody @Valid DateRange range);

    /**
     * 获取冲压车间订单
     * @param range 日期范围
     * @return
     */
    @PostMapping(path = "getStampingOrder",consumes = MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "冲压车间订单",notes = "冲压车间订单")
    ResultData<List<StampingOrderDto>> getStampingOrder(@RequestBody DateRange range);

    /**
     * 订单一览表导出excel
     * @param search 查询条件
     */
    @PostMapping(path = "exportAllOrder",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "导出订单一览表" ,notes = "导出订单一览表")
    ResultData<List<AllOrderExcelDto>> exportAllOrder(@RequestBody Search search);

    /**
     * 订单一览表导出excel
     * @param search 查询条件
     */
    @PostMapping(path = "exportInnerOrder",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "导出订单一览表" ,notes = "导出订单一览表")
    ResultData<List<InnerOrderExcelDto>> exportInnerOrder(@RequestBody Search search);

    /**
     * 生成APS工单 （定时任务）
     * @param
     * @return
     */
    @PostMapping(path = "initApsOrderData",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "生成APS工单" ,notes = "生成APS工单")
    ResultData<String> initApsOrder();


    /**
     * 控制台
     * @return
     */
    @PostMapping(path = "findOrderStatistic")
    @ApiOperation(value = "控制台",notes = "控制台")
    ResultData<List<StatisticGridDto>> findOrderStatistic();

}
