package com.donlim.aps.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.changhong.sei.core.api.FindByPageApi;
import com.donlim.aps.dto.ApsDayReportDto;
import com.donlim.aps.dto.ApsHolidayDto;
import org.springframework.cloud.openfeign.FeignClient;

import javax.validation.Valid;

/**
 * (ApsDayReport)API
 *
 * @author sei
 * @since 2023-05-19 16:17:08
 * TODO @FeignClient(name = "请修改为项目服务名")
 */
@Valid
@FeignClient(name = "wjaps-one-api", path = ApsDayReportApi.PATH)
public interface ApsDayReportApi extends BaseEntityApi<ApsDayReportDto> , FindByPageApi<ApsDayReportDto> {
    String PATH = "apsDayReport";
}
