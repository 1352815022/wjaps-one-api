package com.donlim.aps.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.donlim.aps.dto.ApsOrderPlanSonDto;
import com.donlim.aps.dto.ColsAndSearch;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

/**
 * 生产计划表(子件)(ApsOrderPlanSon)API
 *
 * @author sei
 * @since 2022-05-28 10:19:22
 * @FeignClient(name = "apsOrderPlanSon")
 */
@Valid
@FeignClient(name = "wjaps-one-api", path = ApsOrderPlanSonApi.PATH)
public interface ApsOrderPlanSonApi extends BaseEntityApi<ApsOrderPlanSonDto>  {
    String PATH = "apsOrderPlanSon";

    /**
     * 生产计划子件分页查询
     * @param search 查询条件
     * @return PageResult
     */
    @PostMapping(path = "find" ,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "分页查询",notes = "分页查询")
    ResultData<PageResult> findPlanByPage(@RequestBody ColsAndSearch search);

    /**
     * 子件上产计划导出excel
     * @param search
     * @param response
     */
    @PostMapping(path = "export",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "导出excel",notes = "导出excel")
    void export(@RequestBody ColsAndSearch search,HttpServletResponse response) throws IOException;
}
