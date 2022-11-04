package com.donlim.aps.controller;

import com.donlim.aps.api.U9BomApi;
import com.donlim.aps.service.U9BomService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 料品表bom(U9Bom)控制类
 *
 * @author sei
 * @since 2022-05-12 14:58:03
 */
@RestController
@Api(value = "U9BomApi", tags = "料品表bom服务")
@RequestMapping(path = U9BomApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class U9BomController  implements U9BomApi {
    /**
     * 料品表bom服务对象
     */
    @Autowired
    private U9BomService service;



}
