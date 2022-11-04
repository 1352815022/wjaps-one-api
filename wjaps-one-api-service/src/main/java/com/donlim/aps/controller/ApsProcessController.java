package com.donlim.aps.controller;

import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.api.ApsProcessApi;
import com.donlim.aps.dto.ApsProcessDto;
import com.donlim.aps.entity.ApsProcess;
import com.donlim.aps.service.ApsProcessService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 工序表(ApsProcess)控制类
 *
 * @author sei
 * @since 2022-04-19 14:13:39
 */
@RestController
@Api(value = "ApsProcessApi", tags = "工序表服务")
@RequestMapping(path = ApsProcessApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class ApsProcessController extends BaseEntityController<ApsProcess, ApsProcessDto> implements ApsProcessApi {
    /**
     * 工序表服务对象
     */
    @Autowired
    private ApsProcessService service;

    @Override
    public BaseEntityService<ApsProcess> getService() {
        return service;
    }

    @Override
    public ResultData<PageResult<ApsProcessDto>> findByPage(Search search) {
        return convertToDtoPageResult(service.findByPage(search));
    }

    @Override
    public ResultData<List<ApsProcessDto>> findAll() {
        List<ApsProcess> all = service.findAll();
        List<ApsProcessDto> dtos = convertToDtos(all);
        return ResultData.success(dtos);
    }

    @Override
    public ResultData<List<ApsProcessDto>> findAllUnfrozen() {
        return ResultData.success(convertToDtos(service.findAllUnfrozen()));
    }
}
