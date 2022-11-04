package com.donlim.aps.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.donlim.aps.dto.OneApsPlanDataDto;
import org.springframework.cloud.openfeign.FeignClient;

import javax.validation.Valid;

/**
 * (OneApsPlanData)API
 *
 * @author sei
 * @since 2022-07-27 18:02:54
 * TODO @FeignClient(name = "请修改为项目服务名")
 */
@Valid
@FeignClient(name = "wjaps-one-api", path = OneApsPlanDataApi.PATH)
public interface OneApsPlanDataApi extends BaseEntityApi<OneApsPlanDataDto> {
    String PATH = "oneApsPlanData";
}
