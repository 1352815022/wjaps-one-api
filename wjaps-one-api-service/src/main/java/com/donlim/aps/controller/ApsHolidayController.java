package com.donlim.aps.controller;

import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.api.ApsHolidayApi;
import com.donlim.aps.dto.ApsHolidayDto;
import com.donlim.aps.dto.ApsOrganizeDto;
import com.donlim.aps.entity.ApsHoliday;
import com.donlim.aps.entity.ApsOrganize;
import com.donlim.aps.service.ApsHolidayService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * 放假表(ApsHoliday)控制类
 *
 * @author sei
 * @since 2022-05-03 08:29:55
 */
@RestController
@Api(value = "ApsHolidayApi", tags = "放假表服务")
@RequestMapping(path = ApsHolidayApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class ApsHolidayController extends BaseEntityController<ApsHoliday, ApsHolidayDto> implements ApsHolidayApi {
    /**
     * 放假表服务对象
     */
    @Autowired
    private ApsHolidayService service;

    @Override
    public BaseEntityService<ApsHoliday> getService() {
        return service;
    }


    @Override
    public ResultData<PageResult<ApsHolidayDto>> findByPage(Search search) {
        return convertToDtoPageResult(service.findByPage(search));

    }

    @Override
    public ApsHolidayDto convertToDto(ApsHoliday entity) {
        ApsHolidayDto dto = super.convertToDto(entity);
        if (!Objects.isNull(entity.getApsOrganize())){
            dto.setApsOrganizeDto(dtoModelMapper.map(entity.getApsOrganize(), ApsOrganizeDto.class));
        }

        return dto;
    }

    @Override
    public ApsHoliday convertToEntity(ApsHolidayDto dto) {
        ApsHoliday entity = super.convertToEntity(dto);
        if (!Objects.isNull(dto.getApsOrganizeDto())){
            entity.setApsOrganize(dtoModelMapper.map(dto.getApsOrganizeDto(), ApsOrganize.class));
        }
        return entity;
    }
}
