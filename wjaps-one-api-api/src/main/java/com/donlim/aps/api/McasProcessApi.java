package com.donlim.aps.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.changhong.sei.core.dto.ResultData;
import com.donlim.aps.dto.McasProcessDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Map;

/**
 * (McasProcess)API
 *MCAS工艺信息
 * @author sei
 * @since 2022-06-15 14:04:13
 * TODO @FeignClient(name = "请修改为项目服务名")
 */
@Valid
@FeignClient(name = "wjaps-one-api", path = McasProcessApi.PATH)
public interface McasProcessApi extends BaseEntityApi<McasProcessDto> {
    String PATH = "mcasProcess";

    /**
     * 定时更新工艺信息
     * @param params
     * @return
     */
    @PostMapping(path = "updateProcessTask", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "定时更新工艺信息", notes = "定时更新工艺信息")
    ResultData updateProcessTask(@RequestBody Map<String, String> params);
}
