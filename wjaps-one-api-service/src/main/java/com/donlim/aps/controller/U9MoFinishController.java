package com.donlim.aps.controller;

import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.api.U9MoFinishApi;
import com.donlim.aps.dto.U9MoFinishDto;
import com.donlim.aps.entity.U9MoFinish;
import com.donlim.aps.service.U9MoFinishService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * U9数据扩展字段(U9MoFinish)控制类
 *
 * @author sei
 * @since 2023-05-17 09:12:34
 */
@RestController
@Api(value = "U9MoFinishApi", tags = "U9完工表")
@RequestMapping(path = U9MoFinishApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class U9MoFinishController extends BaseEntityController<U9MoFinish, U9MoFinishDto> implements U9MoFinishApi {
    /**
     * U9数据扩展字段服务对象
     */
    @Autowired
    private U9MoFinishService service;

    @Override
    public BaseEntityService<U9MoFinish> getService() {
        return service;
    }

    @Override
    public ResultData<PageResult<U9MoFinishDto>> findByPage(Search search) {
        return ResultData.success(service.getList(search));
    }

    @Override
    public ResultData<List<U9MoFinishDto>> findNoPlan(Search search) {
        return ResultData.success(service.findNoPlan(search));
    }
}
