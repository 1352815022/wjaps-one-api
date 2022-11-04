package com.donlim.aps.api;

import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.donlim.aps.dto.ColumnDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * 测试动态table列接口
 */
@FeignClient(name = "wjaps-one-api",path = TestColumnApi.PATH)
public interface TestColumnApi {
    String PATH = "dynamicColumn";

    /**
     * 获取动态列
     * @return
     */
    @GetMapping(path = "/getColumn")
    @ApiOperation(value = "获取动态列",notes = "获取动态列")
    ResultData<List<ColumnDto>> getColumn();

    /**
     * 获取动态列数据
     * @return
     */
    @PostMapping(path = "/getData")
    @ApiOperation(value = "获取动态列数据", notes = "获取动态列数据")
    ResultData<PageResult> getData();
}
