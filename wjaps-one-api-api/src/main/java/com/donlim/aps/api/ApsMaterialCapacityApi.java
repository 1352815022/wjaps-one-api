package com.donlim.aps.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.changhong.sei.core.api.FindByPageApi;
import com.donlim.aps.dto.ApsMaterialCapacityDto;
import org.springframework.cloud.openfeign.FeignClient;

import javax.validation.Valid;

/**
 * 标准产能(ApsMaterialCapacity)API
 *
 * @author sei
 * @since 2022-05-09 11:17:15
 *@FeignClient(name = "apsMaterialCapacity")
 */
@Valid
@FeignClient(name = "wjaps-one-api", path = ApsMaterialCapacityApi.PATH)
public interface ApsMaterialCapacityApi extends BaseEntityApi<ApsMaterialCapacityDto> , FindByPageApi<ApsMaterialCapacityDto> {
    String PATH = "apsMaterialCapacity";
}
