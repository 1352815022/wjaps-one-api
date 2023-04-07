package com.donlim.aps.controller;

import com.changhong.sei.core.context.ContextUtil;
import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.log.LogUtil;
import com.changhong.sei.core.service.BaseEntityService;
import com.changhong.sei.core.service.bo.OperateResultWithData;
import com.changhong.sei.core.utils.ResultDataUtil;
import com.donlim.aps.api.ApsOrderApi;
import com.donlim.aps.dto.*;
import com.donlim.aps.entity.ApsOrder;
import com.donlim.aps.service.ApsOrderPlanService;
import com.donlim.aps.service.ApsOrderPlanSonService;
import com.donlim.aps.service.ApsOrderService;
import com.donlim.aps.service.U9ProduceOrderService;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 订单表(ApsOrderInner)控制类
 *
 * @author sei
 * @since 2022-05-10 15:29:57
 */
@RestController
@Api(value = "ApsOrderApi", tags = "订单表服务")
@RequestMapping(path = ApsOrderApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class ApsOrderController extends BaseEntityController<ApsOrder, ApsOrderDto> implements ApsOrderApi {
    /**
     * 订单表(内部)服务对象
     */
    @Autowired
    private ApsOrderService service;

    /**
     * 生产计划service
     */
    @Autowired
    private ApsOrderPlanService planService;

    @Autowired
    private U9ProduceOrderService u9ProduceOrderService;
    @Autowired
    private ApsOrderPlanSonService planSonService;

    @Override
    public BaseEntityService<ApsOrder> getService() {
        return service;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultData<String> initApsOrder() {
        LogUtil.bizLog("后台任务由【{}】执行完成！", ContextUtil.getSessionUser());
        //service.pullData();
        service.pullData_v2();
        return ResultDataUtil.success("执行成功");
    }

    /**
     * 修改内排单据状态，同时修改排产单状态
     * @param dtos
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultData<String> changeOrderStatus(List<ApsOrderDto> dtos) {
        List<String> collect = dtos.stream().filter(e -> StringUtils.isNotBlank(e.getId())).map(ApsOrderDto::getId).collect(Collectors.toList());
        List<ApsOrder> dbList = service.findByIds(collect);
        for (ApsOrderDto dto : dtos) {
            ApsOrder apsOrder = dtoModelMapper.map(dto, ApsOrder.class);
            Optional<ApsOrder> apsOrderOptional = dbList.stream().filter(e -> e.getId().equals(dto.getId())).findFirst();
            if (apsOrderOptional.isPresent()){
                ApsOrder dbApsOrder = apsOrderOptional.get();
                // <暂停> 且 入参 <恢复> 状态才可以恢复
                if (OrderStatusType.Stop.equals(dbApsOrder.getStatus()) && OrderStatusType.NoRelease.equals(apsOrder.getStatus())){
                    apsOrder.setNoPlanQty(apsOrder.getOrderQty());
                    apsOrder.setTotalPlanQty(BigDecimal.ZERO);
                    service.save(apsOrder);
                }else if (OrderStatusType.Stop.equals(dto.getStatus()) || OrderStatusType.Completed.equals(dto.getStatus())){
                    //暂停 或 完成
                    service.save(apsOrder);
                    planService.changeStatus(apsOrder);
                }
            }
        }
        return ResultData.success();
    }

    /**
     * 分页查找方法
     * @param search
     * @return
     */
    @Override
    public ResultData<PageResult<ApsOrderDto>> findByPage(Search search) {
        return convertToDtoPageResult(service.findByPage(search));
    }


    /**
     * 排产下达
     * 逻辑：
     * 1.可分多次下达，车间、生产线、计划数量不能为空
     * 2.手工维护生产计划批次号
     * 3.更新内部待排中对应记录的未排数量、已排数量
     * 4.插入新记录到生产计划
     * 5.拆分组件生成子件生产计划
     * @param dto
     * @return ResultData<String>
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultData<String> plan(ApsOrderDto dto) throws Exception {
        dto.setPlanNum(dto.getPlanNum()+1);
        ApsOrder entity = dtoModelMapper.map(dto, ApsOrder.class);
        if (OrderStatusType.Released.equals(entity.getStatus())){
            return ResultData.fail("工单已下达，不能重复下达！");
        }
        if (OrderStatusType.Completed.equals(entity.getStatus()) || OrderStatusType.Stop.equals(entity.getStatus())){
            return ResultData.fail("工单已完成或暂停，不能继续下达！");
        }
        if(!service.IsCanPlan(dto)){
            return ResultData.fail(dto.getOrderNo()+"在"+dto.getPlanStartDate()+"已经有计划，请重新选择日期！");
        }
        OperateResultWithData<ApsOrder> orderInnerSave = service.save(entity);
        if (orderInnerSave.getSuccess()){
          planService.orderPlan(dto);
          return ResultData.success();
        }
        return ResultData.fail(orderInnerSave.getMessage());
    }

    /**
     * 批量下达
     * @param dtos
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultData<String> batchPlan(@Valid List<ApsOrderDto> dtos) throws Exception {
        for (ApsOrderDto dto : dtos) {
            if (OrderStatusType.Completed.equals(dto.getStatus()) || OrderStatusType.Stop.equals(dto.getStatus())){
                continue;
            }
            ResultData<String> result = this.plan(dto);
            if (!result.getSuccess()){
                LogUtil.warn(result.getMessage());
                throw new Exception(result.getMessage());
            }
        }
        return ResultData.success();
    }

    /**
     * 组件拆分并合并
     * @param range 日期范围
     * @return
     */
    @Override
    public ResultData<String> splitAndMerge(@Valid DateRange range) {
        //service.splitAndMerge(range);
        return ResultData.success();
    }

    @Override
    public ResultData<List<StampingOrderDto>> getStampingOrder(DateRange range){
        return ResultData.success(u9ProduceOrderService.stampingOrder(range));
    }

    @Override
    public ResultData<List<AllOrderExcelDto>> exportAllOrder(Search search) {
        List<ApsOrder> allOrders = service.findByFilters(search);
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<ApsOrder, AllOrderExcelDto> typeMap = modelMapper.typeMap(ApsOrder.class, AllOrderExcelDto.class);
        List<AllOrderExcelDto> collect = allOrders.stream().map(typeMap::map).collect(Collectors.toList());
        return ResultData.success(collect);
    }

    @Override
    public ResultData<List<InnerOrderExcelDto>> exportInnerOrder(Search search) {
        List<ApsOrder> allOrders = service.findByFilters(search);
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<ApsOrder, InnerOrderExcelDto> typeMap = modelMapper.typeMap(ApsOrder.class, InnerOrderExcelDto.class);
        List<InnerOrderExcelDto> collect = allOrders.stream().map(typeMap::map).collect(Collectors.toList());
        return ResultData.success(collect);
    }


}
