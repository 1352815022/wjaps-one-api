package com.donlim.aps.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.donlim.aps.dto.ApsOrderExtDto;
import org.springframework.cloud.openfeign.FeignClient;

import javax.validation.Valid;

/**
 * 订单表(内部)扩展表(ApsOrderExt)API
 *
 * @author sei
 * @since 2023-04-06 08:30:24
 * TODO @FeignClient(name = "请修改为项目服务名")
 */
@Valid
@FeignClient(name = "wjaps-one-api", path = ApsOrderExtApi.PATH)
public interface ApsOrderExtApi extends BaseEntityApi<ApsOrderExtDto> {
    String PATH = "apsOrderExt";
}