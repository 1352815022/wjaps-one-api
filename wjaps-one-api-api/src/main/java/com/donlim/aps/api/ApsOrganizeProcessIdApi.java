package com.donlim.aps.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.changhong.sei.core.api.FindByPageApi;
import com.donlim.aps.dto.ApsOrganizeProcessIdDto;
import org.springframework.cloud.openfeign.FeignClient;

import javax.validation.Valid;

/**
 * 组织工艺配置(ApsOrganizeProcessId)API
 *
 * @author sei
 * @since 2022-04-26 09:22:24
 * @FeignClient(name = "apsOrganizeProcessId")
 */
@Valid
@FeignClient(name = "wjaps-one-api", path = ApsOrganizeProcessIdApi.PATH)
public interface ApsOrganizeProcessIdApi extends BaseEntityApi<ApsOrganizeProcessIdDto> , FindByPageApi<ApsOrganizeProcessIdDto> {
    String PATH = "apsOrganizeProcessId";
}
