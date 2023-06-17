package com.donlim.aps.controller;

import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.api.ApsOrderPlanApi;
import com.donlim.aps.dto.ApsOrderPlanAndDetailsDto;
import com.donlim.aps.dto.ApsOrderPlanDto;
import com.donlim.aps.dto.ColsAndSearch;
import com.donlim.aps.dto.ColumnDto;
import com.donlim.aps.entity.ApsOrderPlan;
import com.donlim.aps.service.ApsOrderPlanDetailService;
import com.donlim.aps.service.ApsOrderPlanService;
import com.donlim.aps.service.ApsOrderPlanSonDetailService;
import com.donlim.aps.util.ColumnUtils;
import com.donlim.aps.util.ResultEnum;
import com.donlim.aps.vo.PlanSearchVo;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * 生产计划表(内部)(ApsOrderPlan)控制类
 *
 * @author sei
 * @since 2022-05-11 15:12:30
 */
@RestController
@Api(value = "ApsOrderPlanApi", tags = "生产计划表(内部)服务")
@RequestMapping(path = ApsOrderPlanApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class ApsOrderPlanController extends BaseEntityController<ApsOrderPlan, ApsOrderPlanDto> implements ApsOrderPlanApi {
    /**
     * 生产计划表(内部)服务对象
     */
    @Autowired
    private ApsOrderPlanService service;

    @Autowired
    private ApsOrderPlanDetailService detailService;
    @Autowired
    private ApsOrderPlanSonDetailService sonDetailService;


    @Override
    public BaseEntityService<ApsOrderPlan> getService() {
        return service;
    }

    @Override
    public ResultData<List<Map<String, Object>>> findPlanByPage(PlanSearchVo planSearchVo) {
        return ResultData.success(service.findPlanByPage(planSearchVo));
    }

    @Override
    public ResultData<List<ColumnDto>> getColumn(Integer cols) {
        return ResultData.success(ColumnUtils.getColumnsByDate(cols, LocalDate.now().plusDays(-1), true));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultData<String> savePlan(ApsOrderPlanAndDetailsDto dto) {
        if (StringUtils.isEmpty(dto.getId())) {
            return ResultData.fail(ResultEnum.NOT_FIND_PLAN_ID.getMsg());
        }
        ApsOrderPlan orderPlan = entityModelMapper.map(dto, ApsOrderPlan.class);
        BigDecimal planned = detailService.saveDetail(orderPlan.getId(), dto.getDetails());
        orderPlan.setAwaitQty(orderPlan.getPlanQty().subtract(planned));
        service.save(orderPlan);
        return ResultData.success();
    }

    /**
     * 生产计划排产批量保存
     * 1.保存排产明细
     * 2.计算排产百分比 x < 1 则按百分比排产，x = 1 则排产剩余数量
     * 3.自动排产子件
     * 4.刷新待排数量，保存母件生产计划
     *
     * @param dtos
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultData<String> saveAllPlan(List<ApsOrderPlanAndDetailsDto> dtos) {
        for (ApsOrderPlanAndDetailsDto dto : dtos) {
            if (StringUtils.isEmpty(dto.getId())) {
                return ResultData.fail(ResultEnum.NOT_FIND_PLAN_ID.getMsg());
            }
            ApsOrderPlan orderPlan = service.findOne(dto.getId());
            if(dto.getPlanQty().compareTo(BigDecimal.ZERO)==0){
                continue;
            }
            BigDecimal planned = detailService.saveDetail(orderPlan.getId(), dto.getDetails());
            sonDetailService.saveDetail(dto);
            orderPlan.setAwaitQty(orderPlan.getPlanQty().subtract(planned));
            service.save(orderPlan);
        }
        return ResultData.success();
    }

    @Override
    public ResultData updateOrderPlanToFinish(Map<String, String> params) {
        service.updateOrderPlanToFinish();
        return ResultData.success();
    }
}
