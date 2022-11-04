package com.donlim.aps.api;

import org.springframework.cloud.openfeign.FeignClient;

import javax.validation.Valid;

/**
 * 料品表bom(U9Bom)API
 *
 * @author sei
 * @since 2022-05-12 14:58:08
 * TODO @FeignClient(name = "请修改为项目服务名")
 */
@Valid
@FeignClient(name = "wjaps-one-api", path = U9BomApi.PATH)
public interface U9BomApi {
    String PATH = "u9Bom";
}
