package com.donlim.aps.controller;

import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.api.ApsOrganizeProcessIdApi;
import com.donlim.aps.dto.ApsOrganizeDto;
import com.donlim.aps.dto.ApsOrganizeProcessIdDto;
import com.donlim.aps.dto.ApsProcessDto;
import com.donlim.aps.entity.ApsOrganize;
import com.donlim.aps.entity.ApsOrganizeProcessId;
import com.donlim.aps.entity.ApsProcess;
import com.donlim.aps.service.ApsOrganizeProcessIdService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * 组织工艺配置(ApsOrganizeProcessId)控制类
 *
 * @author sei
 * @since 2022-04-26 09:22:16
 */
@RestController
@Api(value = "ApsOrganizeProcessIdApi", tags = "组织工艺配置服务")
@RequestMapping(path = ApsOrganizeProcessIdApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class ApsOrganizeProcessIdController extends BaseEntityController<ApsOrganizeProcessId, ApsOrganizeProcessIdDto> implements ApsOrganizeProcessIdApi {
    /**
     * 组织工艺配置服务对象
     */
    @Autowired
    private ApsOrganizeProcessIdService service;

    @Override
    public BaseEntityService<ApsOrganizeProcessId> getService() {
        return service;
    }

    @Override
    public ResultData<PageResult<ApsOrganizeProcessIdDto>> findByPage(Search search) {
        return convertToDtoPageResult(service.findByPage(search));
    }

    @Override
    public ApsOrganizeProcessIdDto convertToDto(ApsOrganizeProcessId entity) {
        ApsOrganizeProcessIdDto dto = super.convertToDto(entity);
        if (!Objects.isNull(dto.getApsProcessDto())){
            dto.setApsProcessDto(dtoModelMapper.map(entity.getApsProcess(), ApsProcessDto.class));
        }
        if (!Objects.isNull(dto.getApsOrganizeDto())){
            dto.setApsOrganizeDto(dtoModelMapper.map(entity.getApsOrganize(), ApsOrganizeDto.class));
        }
        return dto;
    }

    @Override
    public ApsOrganizeProcessId convertToEntity(ApsOrganizeProcessIdDto dto) {
        ApsOrganizeProcessId entity = super.convertToEntity(dto);
        if (!Objects.isNull(dto.getApsProcessDto())){
            entity.setApsProcess(dtoModelMapper.map(dto.getApsProcessDto(), ApsProcess.class));
        }
        if (!Objects.isNull(dto.getApsOrganizeDto())){
            entity.setApsOrganize(dtoModelMapper.map(dto.getApsOrganizeDto(), ApsOrganize.class));
        }
        return entity;
    }
}
