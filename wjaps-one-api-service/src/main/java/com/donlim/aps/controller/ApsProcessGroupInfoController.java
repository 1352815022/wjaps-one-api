package com.donlim.aps.controller;

import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.api.ApsProcessGroupInfoApi;
import com.donlim.aps.dto.ApsProcessDto;
import com.donlim.aps.dto.ApsProcessGroupDto;
import com.donlim.aps.dto.ApsProcessGroupInfoDto;
import com.donlim.aps.entity.ApsProcess;
import com.donlim.aps.entity.ApsProcessGroup;
import com.donlim.aps.entity.ApsProcessGroupInfo;
import com.donlim.aps.service.ApsProcessGroupInfoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * 工艺工序关连表(ApsProcessGroupInfo)控制类
 *
 * @author sei
 * @since 2022-04-20 16:11:43
 */
@RestController
@Api(value = "ApsProcessGroupInfoApi", tags = "工艺工序关连表服务")
@RequestMapping(path = ApsProcessGroupInfoApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class ApsProcessGroupInfoController extends BaseEntityController<ApsProcessGroupInfo, ApsProcessGroupInfoDto> implements ApsProcessGroupInfoApi {
    /**
     * 工艺工序关连表服务对象
     */
    @Autowired
    private ApsProcessGroupInfoService service;


    @Override
    public BaseEntityService<ApsProcessGroupInfo> getService() {
        return service;
    }

    @Override
    public ResultData<PageResult<ApsProcessGroupInfoDto>> findByPage(Search search) {

        return convertToDtoPageResult(service.findByPage(search));
    }

    @Override
    public ApsProcessGroupInfoDto convertToDto(ApsProcessGroupInfo entity) {
        ApsProcessGroupInfoDto dto = super.convertToDto(entity);
        if (!Objects.isNull(entity.getApsProcess())){
            dto.setApsProcessDto(dtoModelMapper.map(entity.getApsProcess(), ApsProcessDto.class));
        }
        if (!Objects.isNull(entity.getApsProcessGroup())){
            dto.setApsProcessGroupDto(dtoModelMapper.map(entity.getApsProcessGroup(), ApsProcessGroupDto.class));
        }
        return dto;
    }

    @Override
    public ApsProcessGroupInfo convertToEntity(ApsProcessGroupInfoDto dto) {
        ApsProcessGroupInfo entity = super.convertToEntity(dto);
        if (!Objects.isNull(dto.getApsProcessDto())){
            entity.setApsProcess(entityModelMapper.map(dto.getApsProcessDto(),ApsProcess.class));
        }
        if (!Objects.isNull(dto.getApsProcessGroupDto())) {
            entity.setApsProcessGroup(entityModelMapper.map(dto.getApsProcessGroupDto(), ApsProcessGroup.class));
        }
        return entity;
    }
}
