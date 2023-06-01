package com.donlim.aps.api;

import com.changhong.sei.core.dto.ResultData;
import com.donlim.aps.dto.open.ApsPlanDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.time.LocalDate;

/**
 * @Description:对外开放的查询接口
 * @Author: chenzhiquan
 * @Date: 2022/9/17.
 */
@Valid
@FeignClient(name = "wjaps-one-api", path = ApsOpenApi.PATH)
public interface ApsOpenApi {
    String PATH = "apsOpenApi";




    /**
     * 获取当天计划单
     * @param date
     * @return
     */
    @GetMapping(path = "getPlanByDate")
    @ApiOperation(value = "获取生产计划" ,notes = "获取生产计划")
    ResultData<ApsPlanDto> getPlanByDate(@RequestParam("date") String date);

}
