package com.donlim.aps.controller;

import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.api.OneApsPlanDataApi;
import com.donlim.aps.dto.OneApsPlanDataDto;
import com.donlim.aps.entity.OneApsPlanData;
import com.donlim.aps.service.OneApsPlanDataService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (OneApsPlanData)控制类
 *
 * @author sei
 * @since 2022-07-27 17:42:04
 */
@RestController
@Api(value = "OneApsPlanDataApi", tags = "服务")
@RequestMapping(path = OneApsPlanDataApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class OneApsPlanDataController extends BaseEntityController<OneApsPlanData, OneApsPlanDataDto> implements OneApsPlanDataApi {
    /**
     * 服务对象
     */
    @Autowired
    private OneApsPlanDataService service;

    @Override
    public BaseEntityService<OneApsPlanData> getService() {
        return service;
    }

}
