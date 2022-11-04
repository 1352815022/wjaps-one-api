package com.donlim.aps.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.donlim.aps.dto.U9PurchaseDto;
import org.springframework.cloud.openfeign.FeignClient;

import javax.validation.Valid;

/**
 * 采购/委外(U9Purchase)API
 *
 * @author sei
 * @since 2022-07-14 17:26:28
 * TODO @FeignClient(name = "请修改为项目服务名")
 */
@Valid
@FeignClient(name = "wjaps-one-api", path = U9PurchaseApi.PATH)
public interface U9PurchaseApi extends BaseEntityApi<U9PurchaseDto> {
    String PATH = "u9Purchase";
}
