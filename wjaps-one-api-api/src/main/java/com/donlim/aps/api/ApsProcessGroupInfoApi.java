package com.donlim.aps.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.changhong.sei.core.api.FindByPageApi;
import com.donlim.aps.dto.ApsProcessGroupInfoDto;
import org.springframework.cloud.openfeign.FeignClient;

import javax.validation.Valid;

/**
 * 工艺工序关连表(ApsProcessGroupInfo)API
 *
 * @author sei
 * @since 2022-04-20 16:11:45
 * @FeignClient(name = "wjaps-one-api")
 */
@Valid
@FeignClient(name = "wjaps-one-api", path = ApsProcessGroupInfoApi.PATH)
public interface ApsProcessGroupInfoApi extends BaseEntityApi<ApsProcessGroupInfoDto>, FindByPageApi<ApsProcessGroupInfoDto> {
    String PATH = "apsProcessGroupInfo";
}
