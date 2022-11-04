package com.donlim.aps.controller;

import com.changhong.sei.core.context.ContextUtil;
import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.log.LogUtil;
import com.changhong.sei.core.service.BaseEntityService;
import com.changhong.sei.core.utils.ResultDataUtil;
import com.donlim.aps.api.McasYieldApi;
import com.donlim.aps.dto.McasYieldDto;
import com.donlim.aps.entity.McasYield;
import com.donlim.aps.service.McasYieldService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * (McasYield)控制类
 *
 * @author sei
 * @since 2022-06-14 10:26:07
 */
@RestController
@Api(value = "McasYieldApi", tags = "服务")
@RequestMapping(path = McasYieldApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class McasYieldController extends BaseEntityController<McasYield, McasYieldDto> implements McasYieldApi {
    /**
     * 服务对象
     */
    @Autowired
    private McasYieldService service;

    @Override
    public BaseEntityService<McasYield> getService() {
        return service;
    }
    @Override
    public ResultData updateYeildTask(Map<String, String> params) {
        LogUtil.bizLog("后台任务由【{}】执行完成！", ContextUtil.getSessionUser());
        service.updateYeildTask();
        return ResultDataUtil.success("执行成功");
    }
}
