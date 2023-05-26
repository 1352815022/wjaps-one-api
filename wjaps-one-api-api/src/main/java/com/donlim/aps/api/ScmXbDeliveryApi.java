package com.donlim.aps.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.changhong.sei.core.api.FindByPageApi;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.Search;
import com.donlim.aps.dto.ScmXbDeliveryDto;
import com.donlim.aps.dto.U9MoFinishDto;
import com.donlim.aps.vo.ScmXbDeliveryVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * scm送货需求(ScmXbDelivery)API
 *
 * @author czq
 * @since 2022-05-18 08:12:55
 * TODO @FeignClient(name = "请修改为项目服务名")
 */
@Valid
@FeignClient(name = "wjaps-one-api", path = ScmXbDeliveryApi.PATH)
public interface ScmXbDeliveryApi extends BaseEntityApi<ScmXbDeliveryDto> , FindByPageApi<ScmXbDeliveryDto> {
    String PATH = "scmXbDelivery";

    /**
     * 定时更新订单变更
     * @param params
     * @return
     */
    @PostMapping(path = "updateOrderTask", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "定时更新订单变更并生成委外计划", notes = "定时更新订单变更并生成委外计划")
    ResultData updateOrderTask(@RequestBody Map<String, String> params);


    @PostMapping(path = "findChange", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "查询没排计划工单", notes = "")
    ResultData<List<ScmXbDeliveryDto>> findChange(@RequestBody ScmXbDeliveryVO search);
}
