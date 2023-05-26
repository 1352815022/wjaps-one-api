package com.donlim.aps.controller;

import com.changhong.sei.core.context.ContextUtil;
import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.log.LogUtil;
import com.changhong.sei.core.service.BaseEntityService;
import com.changhong.sei.core.utils.ResultDataUtil;
import com.donlim.aps.api.ScmXbDeliveryApi;
import com.donlim.aps.convertdto.ConvertToDto;
import com.donlim.aps.dto.ScmXbDeliveryDto;
import com.donlim.aps.entity.ScmXbDelivery;
import com.donlim.aps.entity.cust.OrderChangeCountVO;
import com.donlim.aps.service.ApsOrderService;
import com.donlim.aps.service.ScmXbDeliveryService;
import com.donlim.aps.vo.ScmXbDeliveryVO;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * scm送货需求(ScmXbDelivery)控制类
 *
 * @author sei
 * @since 2022-05-18 08:12:55
 */
@RestController
@Api(value = "ScmXbDeliveryApi", tags = "scm送货需求服务")
@RequestMapping(path = ScmXbDeliveryApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class ScmXbDeliveryController extends BaseEntityController<ScmXbDelivery, ScmXbDeliveryDto> implements ScmXbDeliveryApi {
    /**
     * scm送货需求服务对象
     */
    @Autowired
    private ScmXbDeliveryService service;
    @Autowired
    private ApsOrderService apsOrderService;

    @Override
    public BaseEntityService<ScmXbDelivery> getService() {
        return service;
    }

    @Override
    public ResultData updateOrderTask(Map<String, String> params) {
        service.updateOrderTask();
        return ResultDataUtil.success("执行成功");
    }

    @Override
    public ResultData<List<ScmXbDeliveryDto>> findChange(ScmXbDeliveryVO scmXbDeliveryVO) {
        List<ScmXbDeliveryDto> scmXbDeliveryDtos = service.findChange(scmXbDeliveryVO).stream().map(ConvertToDto::scmXbDeliveryToDto).collect(Collectors.toList());
        return ResultData.success(scmXbDeliveryDtos);
    }

    @Override
    public ResultData<PageResult<ScmXbDeliveryDto>> findByPage(Search search) {
        return null;
    }
}
