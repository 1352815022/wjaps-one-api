package com.donlim.aps.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.changhong.sei.core.dto.ResultData;
import com.donlim.aps.dto.McasYieldDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Map;

/**
 * (McasYield)API
 *
 * @author sei
 * @since 2022-06-14 10:26:07
 * TODO @FeignClient(name = "请修改为项目服务名")
 */
@Valid
@FeignClient(name = "wjaps-one-api", path = McasYieldApi.PATH)
public interface McasYieldApi extends BaseEntityApi<McasYieldDto> {
    String PATH = "mcasYield";

    /**
     * 定时产量信息
     * @param params
     * @return
     */
    @PostMapping(path = "updateYeildTask", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "定时产量信息", notes = "定时产量信息")
    ResultData updateYeildTask(@RequestBody Map<String, String> params);
}
