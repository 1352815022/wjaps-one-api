package com.donlim.aps.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.changhong.sei.core.api.FindByPageApi;
import com.donlim.aps.dto.ApsHolidayDto;
import org.springframework.cloud.openfeign.FeignClient;

import javax.validation.Valid;

/**
 * 放假表(ApsHoliday)API
 *
 * @author sei
 * @since 2022-05-03 08:29:59
 * TODO @FeignClient(name = "请修改为项目服务名")
 */
@Valid
@FeignClient(name = "wjaps-one-api", path = ApsHolidayApi.PATH)
public interface ApsHolidayApi extends BaseEntityApi<ApsHolidayDto>  , FindByPageApi<ApsHolidayDto> {
    String PATH = "apsHoliday";
}
