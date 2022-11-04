package com.donlim.aps.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.changhong.sei.core.api.FindByPageApi;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.Search;
import com.donlim.aps.dto.ApsOrderCompleteDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

/**
 * 订单齐套(ApsOrderComplete)API
 *
 * @author sei
 * @since 2022-07-13 14:01:51
 * TODO @FeignClient(name = "请修改为项目服务名")
 */
@Valid
@FeignClient(name = "wjaps-one-api", path = ApsOrderCompleteApi.PATH)
public interface ApsOrderCompleteApi extends BaseEntityApi<ApsOrderCompleteDto>, FindByPageApi<ApsOrderCompleteDto> {
    String PATH = "apsOrderComplete";

    /**
     * 导出齐套汇总信息
     * @param search
     * @param response
     * @throws IOException
     */
    @PostMapping(path = "/export")
    @ApiOperation(value = "导出齐套汇总信息",notes = "导出齐套汇总信息")
    void export (@RequestBody Search search, HttpServletResponse response) throws IOException;

    @PostMapping(path = "orderCompleteTask", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "定时计算齐套信息", notes = "定时计算齐套信息")
    ResultData orderCompleteTask(@RequestBody Map<String, String> params);

}
