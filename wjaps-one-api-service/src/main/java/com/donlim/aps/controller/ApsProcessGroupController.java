package com.donlim.aps.controller;

import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.api.ApsProcessGroupApi;
import com.donlim.aps.dto.ApsProcessGroupDto;
import com.donlim.aps.entity.ApsProcessGroup;
import com.donlim.aps.service.ApsProcessGroupService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 工艺表(ApsProcessGroup)控制类
 *
 * @author sei
 * @since 2022-04-20 16:11:38
 */
@RestController
@Api(value = "ApsProcessGroupApi", tags = "工艺表服务")
@RequestMapping(path = ApsProcessGroupApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class ApsProcessGroupController extends BaseEntityController<ApsProcessGroup, ApsProcessGroupDto> implements ApsProcessGroupApi {
    /**
     * 工艺表服务对象
     */
    @Autowired
    private ApsProcessGroupService service;

    @Override
    public BaseEntityService<ApsProcessGroup> getService() {
        return service;
    }

    @Override
    public ResultData<PageResult<ApsProcessGroupDto>> findByPage(Search search) {
        return convertToDtoPageResult(service.findByPage(search));
    }
}
