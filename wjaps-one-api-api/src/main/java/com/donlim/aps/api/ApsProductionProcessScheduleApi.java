package com.donlim.aps.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.donlim.aps.dto.ApsProductionProcessScheduleDto;
import com.donlim.aps.dto.StampingReportDto;
import com.donlim.aps.dto.WashWorkshopScheduleDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * 生产工序报工表
 * (ApsProductionProcessSchedule)API
 *
 * @author sei
 * @since 2022-06-20 11:34:58
 * @FeignClient(name = "wjaps-one-api/apsProductionProcessSchedule")
 */
@Valid
@FeignClient(name = "wjaps-one-api", path = ApsProductionProcessScheduleApi.PATH)
public interface ApsProductionProcessScheduleApi extends BaseEntityApi<ApsProductionProcessScheduleDto> {
    String PATH = "apsProductionProcessSchedule";

    /**
     * 按料号汇总查询工序报工
     * @param search
     * @return
     */
    @PostMapping(path = "findByMaterial", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "按料号汇总查询工序报工", notes = "按料号汇总查询工序报工")
    ResultData<List<ApsProductionProcessScheduleDto>> findByMaterial(@RequestBody Search search);

    /**
     * 按工单汇总查询工序报工
     * @param search
     * @return
     */
    @PostMapping(path = "findByOrder", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "按工单汇总查询工序报工", notes = "按工单汇总查询工序报工")
    ResultData<PageResult<ApsProductionProcessScheduleDto>> findByOrder(@RequestBody Search search);

    /**
     * 按工单查询报工报表
     * @param search
     * @return
     */
    @PostMapping(path = "findByOrderReport",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "按工单查询报工报表",notes = "按工单查询报工报表")
    ResultData<List<StampingReportDto>>findByOrderReport(@RequestBody Search search);
    /**
     * 按工单汇总导出工序报工
     * @param search
     */
    @PostMapping(path = "exportByOrder" , consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "按工单汇总导出工序报工" , notes = "按工单汇总导出工序报工")
    void exportByOrder(@RequestBody Search search , HttpServletResponse response) throws IOException;

    /**
     * 清洗喷粉报表
     * @param search
     * @return
     */
    @PostMapping(path = "findWashByorder",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "清洗喷粉报表",notes = "清洗喷粉报表")
    ResultData<List<WashWorkshopScheduleDto>> findWashByOrder(@RequestBody Search search);



}
