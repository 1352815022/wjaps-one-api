package com.donlim.aps.controller;

import com.changhong.sei.core.context.ContextUtil;
import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.log.LogUtil;
import com.changhong.sei.core.service.BaseEntityService;
import com.changhong.sei.core.utils.ResultDataUtil;
import com.donlim.aps.api.McasProcessApi;
import com.donlim.aps.dto.McasProcessDto;
import com.donlim.aps.entity.McasProcess;
import com.donlim.aps.service.McasProcessService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * (McasProcess)控制类
 *
 * @author sei
 * @since 2022-06-15 14:04:13
 */
@RestController
@Api(value = "McasProcessApi", tags = "服务")
@RequestMapping(path = McasProcessApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class McasProcessController extends BaseEntityController<McasProcess, McasProcessDto> implements McasProcessApi {
    /**
     * 服务对象
     */
    @Autowired
    private McasProcessService service;

    @Override
    public BaseEntityService<McasProcess> getService() {
        return service;
    }
    @Override
    public ResultData updateProcessTask(Map<String, String> params) {
        LogUtil.bizLog("后台任务由【{}】执行完成！", ContextUtil.getSessionUser());
        service.updateProcessTask();
        return ResultDataUtil.success("执行成功");
    }
}
