package com.donlim.aps.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.changhong.sei.core.api.FindAllApi;
import com.changhong.sei.core.api.FindByPageApi;
import com.changhong.sei.core.dto.ResultData;
import com.donlim.aps.dto.ApsProcessDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

/**
 * 工序表(ApsProcess)API
 *
 * @author sei
 * @since 2022-04-19 14:13:40
 * TODO @FeignClient(name = "请修改为项目服务名")
 */
@Valid
@FeignClient(name = "wjaps-one-api", path = ApsProcessApi.PATH)
public interface ApsProcessApi extends BaseEntityApi<ApsProcessDto>, FindByPageApi<ApsProcessDto>, FindAllApi<ApsProcessDto> {
    String PATH = "apsProcess";

    @Override
    @PostMapping("/findAll")
    ResultData<List<ApsProcessDto>> findAll();
}
