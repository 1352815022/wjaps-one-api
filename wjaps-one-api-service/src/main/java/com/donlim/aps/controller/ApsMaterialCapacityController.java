package com.donlim.aps.controller;

import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.api.ApsMaterialCapacityApi;
import com.donlim.aps.dto.ApsMaterialCapacityDto;
import com.donlim.aps.entity.ApsMaterialCapacity;
import com.donlim.aps.service.ApsMaterialCapacityService;
import io.swagger.annotations.Api;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * 标准产能(ApsMaterialCapacity)控制类
 *
 * @author sei
 * @since 2022-05-09 11:17:11
 */
@RestController
@Api(value = "ApsMaterialCapacityApi", tags = "标准产能服务")
@RequestMapping(path = ApsMaterialCapacityApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class ApsMaterialCapacityController extends BaseEntityController<ApsMaterialCapacity, ApsMaterialCapacityDto> implements ApsMaterialCapacityApi {
    /**
     * 服务对象
     */
    @Autowired
    private ApsMaterialCapacityService service;

    @Override
    public BaseEntityService<ApsMaterialCapacity> getService() {
        return service;
    }

    @Override
    public ResultData<PageResult<ApsMaterialCapacityDto>> findByPage(Search search) {
        return convertToDtoPageResult(service.findByPage(search));
    }

    @Override
    public ApsMaterialCapacityDto convertToDto(ApsMaterialCapacity entity) {
        dtoModelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ApsMaterialCapacityDto dto = super.convertToDto(entity);
        if (!Objects.isNull(entity.getWorkGroup())){
            dto.setWorkGroupCode(entity.getWorkGroup().getCode());
            dto.setWorkGroupName(entity.getWorkGroup().getName());
        }
        if (!Objects.isNull(entity.getWorkLine())){
            dto.setWorkLineCode(entity.getWorkLine().getCode());
            dto.setWorkLineName(entity.getWorkLine().getName());
        }
        return dto;
    }
}
