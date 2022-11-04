package com.donlim.aps.controller;

import com.changhong.sei.core.controller.BaseTreeController;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.service.BaseTreeService;
import com.donlim.aps.api.ApsOrganizeApi;
import com.donlim.aps.dto.ApsOrganizeDto;
import com.donlim.aps.entity.ApsOrganize;
import com.donlim.aps.service.ApsOrganizeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 组织机构(ApsOrganize)控制类
 *
 * @author sei
 * @since 2022-04-25 11:27:41
 */
@RestController
@Api(value = "ApsOrganizeApi", tags = "组织机构服务")
@RequestMapping(path = ApsOrganizeApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class ApsOrganizeController extends BaseTreeController<ApsOrganize, ApsOrganizeDto> implements ApsOrganizeApi {
    /**
     * 组织机构服务对象
     */
    @Autowired
    private ApsOrganizeService service;

    @Override
    public BaseTreeService<ApsOrganize> getService() {
        return service;
    }

    /**
     * 获取组织机构树
     * @return
     */
    @Override
    public ResultData<ApsOrganizeDto> findTree(){
        return ResultData.success(convertToDto(service.findTree()));
    }

    /**
     * 获取组织结构树（不包含冻结）
     * @return
     */
    @Override
    public ResultData<List<ApsOrganizeDto>> findOrgTreeWithoutFrozen() {
        List<ApsOrganize> list = service.findOrgTreeWithoutFrozen();
        List<ApsOrganizeDto> dtos = list.stream().map(this::convertToDto).collect(Collectors.toList());
        return ResultData.success(dtos);
    }

    /**
     * 获取生产线（不包含冻结）
     * @return
     */
    @Override
    public ResultData<List<ApsOrganizeDto>> getLines() {
        List<ApsOrganize> lines = service.getLinesWithoutFrozen();
        List<ApsOrganizeDto> dtos = lines.stream().map(this::convertToDto).collect(Collectors.toList());
        return ResultData.success(dtos);
    }

    /**
     * 根据过滤条件获取组织
     * @param search
     * @return
     */
    @Override
    public ResultData<List<ApsOrganizeDto>> findByFilter(Search search) {
        List<ApsOrganize> list = service.findByFilters(search);
        List<ApsOrganizeDto> dtos = list.stream().map(this::convertToDto).collect(Collectors.toList());
        return ResultData.success(dtos);
    }
}
