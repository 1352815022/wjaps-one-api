package com.donlim.aps.controller;

import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.api.ApsDayReportApi;
import com.donlim.aps.dto.ApsDayReportDto;
import com.donlim.aps.entity.ApsDayReport;
import com.donlim.aps.service.ApsDayReportService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (ApsDayReport)控制类
 *
 * @author sei
 * @since 2023-05-19 11:50:12
 */
@RestController
@Api(value = "ApsDayReportApi", tags = "服务")
@RequestMapping(path = ApsDayReportApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class ApsDayReportController extends BaseEntityController<ApsDayReport, ApsDayReportDto> implements ApsDayReportApi {
    /**
     * 服务对象
     */
    @Autowired
    private ApsDayReportService service;

    @Override
    public BaseEntityService<ApsDayReport> getService() {
        return service;
    }

}
