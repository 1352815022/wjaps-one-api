package com.donlim.aps.controller;

import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.api.U9MaterialApi;
import com.donlim.aps.dto.U9MaterialDto;
import com.donlim.aps.dto.upload.EndQtyDTO;
import com.donlim.aps.dto.upload.PowerDTO;
import com.donlim.aps.entity.U9Material;
import com.donlim.aps.service.U9MaterialService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * 料品表(U9Material)控制类
 *
 * @author sei
 * @since 2022-05-09 13:45:01
 */
@RestController
@Api(value = "U9MaterialApi", tags = "料品表服务")
@RequestMapping(path = U9MaterialApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class U9MaterialController extends BaseEntityController<U9Material, U9MaterialDto> implements U9MaterialApi {
    /**
     * 料品表服务对象
     */
    @Autowired
    private U9MaterialService service;

    @Override
    public BaseEntityService<U9Material> getService() {
        return service;
    }

    @Override
    public ResultData<PageResult<U9MaterialDto>> findByPage(Search search) {
        return convertToDtoPageResult(service.findByPage(search));
    }

    @Override
    public ResultData<String> uploadEndQty(List<EndQtyDTO> list) throws IOException {
        service.uploadEndQty(list);
        return ResultData.success();
    }

    @Override
    public ResultData<String> uploadPower(List<PowerDTO> list) throws IOException {
        service.uploadPower(list);
        return ResultData.success();
    }

    @Override
    public ResultData<String> uploadMaterialType(List<U9MaterialDto> list) throws IOException {
        service.uploadMaterialType(list);
        return ResultData.success();
    }
}
