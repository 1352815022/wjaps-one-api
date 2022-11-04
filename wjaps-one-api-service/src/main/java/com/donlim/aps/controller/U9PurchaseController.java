package com.donlim.aps.controller;

import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.api.U9PurchaseApi;
import com.donlim.aps.dto.U9PurchaseDto;
import com.donlim.aps.entity.U9Purchase;
import com.donlim.aps.service.U9PurchaseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 采购/委外(U9Purchase)控制类
 *
 * @author sei
 * @since 2022-07-14 16:49:44
 */
@RestController
@Api(value = "U9PurchaseApi", tags = "采购/委外服务")
@RequestMapping(path = U9PurchaseApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class U9PurchaseController extends BaseEntityController<U9Purchase, U9PurchaseDto> implements U9PurchaseApi {
    /**
     * 采购/委外服务对象
     */
    @Autowired
    private U9PurchaseService service;

    @Override
    public BaseEntityService<U9Purchase> getService() {
        return service;
    }

}
