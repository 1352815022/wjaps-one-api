package com.donlim.aps.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.changhong.sei.core.api.FindByPageApi;
import com.changhong.sei.core.dto.serach.Search;
import com.donlim.aps.dto.ApsOrderDetailCompleteDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

/**
 * 订单齐套(ApsOrderDetailComplete)API
 *
 * @author sei
 * @since 2022-07-13 14:01:28
 * TODO @FeignClient(name = "请修改为项目服务名")
 */
@Valid
@FeignClient(name = "wjaps-one-api", path = ApsOrderDetailCompleteApi.PATH)
public interface ApsOrderDetailCompleteApi extends BaseEntityApi<ApsOrderDetailCompleteDto>, FindByPageApi<ApsOrderDetailCompleteDto> {
    String PATH = "apsOrderDetailComplete";

    /**
     * 导出齐套明细信息
     * @param search
     * @param response
     */
    @PostMapping(path = "/export")
    @ApiOperation(value = "导出齐套明细信息",notes = "导出齐套明细信息")
    void export (@RequestBody Search search, HttpServletResponse response) throws IOException;
}
