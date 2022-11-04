package com.donlim.aps.controller;

import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.api.ApsProcessMaterialApi;
import com.donlim.aps.dto.ApsProcessMaterialDto;
import com.donlim.aps.entity.ApsProcessMaterial;
import com.donlim.aps.service.ApsProcessMaterialService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 工序料品配置(ApsProcessMaterial)控制类
 *
 * @author sei
 * @since 2022-06-08 10:53:19
 */
@RestController
@Api(value = "ApsProcessMaterialApi", tags = "工序料品配置服务")
@RequestMapping(path = ApsProcessMaterialApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class ApsProcessMaterialController extends BaseEntityController<ApsProcessMaterial, ApsProcessMaterialDto> implements ApsProcessMaterialApi {
    /**
     * 工序料品配置服务对象
     */
    @Autowired
    private ApsProcessMaterialService service;

    @Override
    public BaseEntityService<ApsProcessMaterial> getService() {
        return service;
    }

    @Override
    public ResultData<PageResult<ApsProcessMaterialDto>> findByPage(Search search) {
        return convertToDtoPageResult(service.findByPage(search));
    }
}
