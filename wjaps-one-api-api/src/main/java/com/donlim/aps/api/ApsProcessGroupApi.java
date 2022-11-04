package com.donlim.aps.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.changhong.sei.core.api.FindByPageApi;
import com.donlim.aps.dto.ApsProcessGroupDto;
import org.springframework.cloud.openfeign.FeignClient;

import javax.validation.Valid;

/**
 * 工艺表(ApsProcessGroup)API
 *
 * @author sei
 * @since 2022-04-20 16:11:42
 * @FeignClient(name = "wjaps-one-api")
 */
@Valid
@FeignClient(name = "wjaps-one-api", path = ApsProcessGroupApi.PATH)
public interface ApsProcessGroupApi extends BaseEntityApi<ApsProcessGroupDto>, FindByPageApi<ApsProcessGroupDto> {
    String PATH = "apsProcessGroup";
}
