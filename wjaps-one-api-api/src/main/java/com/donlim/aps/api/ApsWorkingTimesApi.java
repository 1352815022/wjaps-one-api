package com.donlim.aps.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.changhong.sei.core.api.FindByPageApi;
import com.changhong.sei.core.dto.ResultData;
import com.donlim.aps.dto.ApsWorkingTimesDto;
import com.donlim.aps.dto.MultiAddWorkingTimesDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * 上班日历配置表(ApsWorkingTimes)API
 *
 * @author sei
 * @since 2022-05-04 10:15:33
 * @FeignClient(name = "apsWorkingTimes")
 */
@Valid
@FeignClient(name = "wjaps-one-api", path = ApsWorkingTimesApi.PATH)
public interface ApsWorkingTimesApi extends BaseEntityApi<ApsWorkingTimesDto> , FindByPageApi<ApsWorkingTimesDto> {
    String PATH = "apsWorkingTimes";

    /**
     * 批量新增工作日历
     * @return
     */
    @PostMapping(path = "multiAdd")
    @ApiOperation(value = "批量新增工作日历",notes = "批量新增工作日历")
    ResultData<String> multiAdd(@RequestBody MultiAddWorkingTimesDto dto);
}
