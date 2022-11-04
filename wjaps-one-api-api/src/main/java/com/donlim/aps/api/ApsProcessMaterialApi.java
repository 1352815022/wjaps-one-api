package com.donlim.aps.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.changhong.sei.core.api.FindByPageApi;
import com.donlim.aps.dto.ApsProcessMaterialDto;
import org.springframework.cloud.openfeign.FeignClient;

import javax.validation.Valid;

/**
 * 工序料品配置(ApsProcessMaterial)API
 *
 * @author sei
 * @since 2022-06-08 10:53:21
 * @FeignClient(name = "apsProcessMaterial")
 */
@Valid
@FeignClient(name = "wjaps-one-api", path = ApsProcessMaterialApi.PATH)
public interface ApsProcessMaterialApi extends BaseEntityApi<ApsProcessMaterialDto> , FindByPageApi<ApsProcessMaterialDto> {
    String PATH = "apsProcessMaterial";
}
