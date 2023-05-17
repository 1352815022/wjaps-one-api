package com.donlim.aps.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.changhong.sei.core.api.FindByPageApi;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.donlim.aps.dto.U9MoFinishDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 * U9数据扩展字段(U9MoFinish)API
 *
 * @author sei
 * @since 2023-05-17 09:14:17
 * TODO @FeignClient(name = "请修改为项目服务名")
 */
@Valid
@FeignClient(name = "wjaps-one-api", path = U9MoFinishApi.PATH)
public interface U9MoFinishApi extends BaseEntityApi<U9MoFinishDto>, FindByPageApi<U9MoFinishDto> {
    String PATH = "u9MoFinish";


    /**
     * 分页查询业务实体
     *
     * @param search 查询参数
     * @return 分页查询结果
     */
    @PostMapping(path = "findNoPlan", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "分页查询业务实体", notes = "分页查询业务实体")
    ResultData<List<U9MoFinishDto>> findNoPlan(@RequestBody Search search);



}
