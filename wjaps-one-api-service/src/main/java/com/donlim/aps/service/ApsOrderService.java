package com.donlim.aps.service;

import cn.hutool.core.map.MapUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson.JSON;
import com.changhong.sei.core.context.ContextUtil;
import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.dto.serach.SearchFilter;
import com.changhong.sei.core.log.LogUtil;
import com.changhong.sei.core.service.BaseEntityService;
import com.changhong.sei.core.service.bo.OperateResultWithData;
import com.changhong.sei.serial.sdk.SerialService;
import com.changhong.sei.util.DateUtils;
import com.donlim.aps.dao.*;
import com.donlim.aps.dto.ApsOrderDto;
import com.donlim.aps.dto.ApsOrderType;
import com.donlim.aps.dto.OrderStatusType;
import com.donlim.aps.dto.U9OrderStatus;
import com.donlim.aps.dto.pull.CommomOrderParamDto;
import com.donlim.aps.dto.pull.ModifyFinishQtyParamDto;
import com.donlim.aps.entity.*;
import com.donlim.aps.entity.cust.*;
import com.donlim.aps.util.NumberUtils;
import com.donlim.aps.util.ResultEnum;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 订单表(ApsOrder)业务逻辑实现类
 *
 * @author sei
 * @since 2022-05-10 15:29:57
 */
@Service
@Slf4j
public class ApsOrderService extends BaseEntityService<ApsOrder> {


    @Autowired
    private ApsOrderDao dao;

    @Autowired
    private ScmXbDeliveryDao scmXbDeliveryDao;

    @Autowired
    private ApsOrganizeDao apsOrganizeDao;

    @Autowired
    private U9MaterialService u9MaterialService;

    @Autowired
    private ApsOrderPlanDetailDao apsOrderPlanDetailDao;

    @Autowired
    private U9MoFinishService u9MoFinishService;

    @Autowired
    private ApsOrderPlanService apsOrderPlanService;

    @Override
    protected BaseEntityDao<ApsOrder> getDao() {
        return dao;
    }

    //解决事务失效
    private ApsOrderService getSelfService(){

        return SpringUtil.getBean(this.getClass());   //SpringUtil工具类见下面代码
    }

    private final Integer partitionSize = 500;
    private final Integer partitionCountLimit = 1000;
    /**
     * 2.0
     * 新增:
     * * 拉取u9_produce_order生产单数据至apsOrder type = INNER
     * * 拉取scm_xb_delivery type = 0委外单数据至apsOrder type = OUTER
     * * * 欠入库数：工单数量-完成数量
     * * * 生产数量：欠数
     * * * 未排数量：生产数量
     * 更新: scmXbDeliveryDao.queryInnerOrderAndExists()  内排
     * 更新: scmXbDeliveryDao.queryPurchaseOrderAndExists()  委外
     */
    public void pullData_v2() {

        //统计完工数
        long t1 = System.currentTimeMillis();
        u9MoFinishService.countU9FinishQtyHandler();
        long t2= System.currentTimeMillis();
        LogUtil.bizLog("countU9FinishQtyHandler耗时{}", t2 - t1);

        List<ApsOrder> orderList = new ArrayList<>();
        //U9料品
        List<U9Material> u9MaterialList = u9MaterialService.findByProductModelIsNotNull();
        //aps组织
        List<ApsOrganize> apsOrganizes = apsOrganizeDao.findAll();
        //拼接公共参数实体
        CommomOrderParamDto innerOrderParam = CommomOrderParamDto.builder()
                .apsOrganizes(apsOrganizes)
                .u9MaterialList(u9MaterialList)
                .build();
        ////内排更新
        long t3 = System.currentTimeMillis();
        List<ApsOrder> innerOrders = getSelfService().innerOrderModifyHandler(innerOrderParam);
        long t4 = System.currentTimeMillis();
        LogUtil.bizLog("innerOrderModifyHandler耗时{}", t4 - t3);
        //委外更新
        //List<ApsOrder> outerOrders = getSelfService().outerOrderModifyHandler();
        long t5= System.currentTimeMillis();
        LogUtil.bizLog("outerOrderModifyHandler耗时{}", t5 - t4);
        //内排新增
        List<ApsOrder> innerOrdersNew =  getSelfService().innerOrderAddHandler(innerOrderParam);
        long t6= System.currentTimeMillis();
        LogUtil.bizLog("innerOrderAddHandle耗时{}", t6 - t5);
        //委外新增
        //  List<ApsOrder> outerOrdersNew = getSelfService().outerOrderAddHandler(innerOrderParam);
        long t7= System.currentTimeMillis();
        LogUtil.bizLog("outerOrderAddHandler耗时{}", t7 - t6);

        //  orderList.addAll(outerOrders);
        orderList.addAll(innerOrders);
        orderList.addAll(innerOrdersNew);
        //   orderList.addAll(outerOrdersNew);
        //分片持久化
        long t8= System.currentTimeMillis();
        getSelfService().orderSaveAll(orderList);
        long t9= System.currentTimeMillis();
        LogUtil.bizLog("orderSaveAll持久化耗时{}", t9 - t8);
        //生产计划写入已完成数量
        //List<ModifyFinishQtyParamDto> modifyFinishQtyParamDtos = orderList.stream().map(t -> {
        //    return ModifyFinishQtyParamDto.builder()
        //            .orderId(t.getId())
        //            .qty(t.getFinishQty())
        //            .build();
        //}).collect(Collectors.toList());
        //apsOrderPlanService.BatchModifyHasQtyByParam(modifyFinishQtyParamDtos);



        LogUtil.bizLog("pullData_v2总耗时{}", t9 - t1);

    }

    /**
     * 分片处理，避免大事务
     * @param orderList
     */
    public void orderSaveAll(List<ApsOrder> orderList) {
        if (orderList.size() > partitionCountLimit) {
            List<List<ApsOrder>> partition = Lists.partition(orderList, partitionSize);
            for (List<ApsOrder> apsOrders : partition) {
                getSelfService().save(apsOrders);
            }
        }else{
            getSelfService().save(orderList);
        }
    }

    /**
     * 委外新增
     * @param innerOrderParam
     * @return
     */
    public List<ApsOrder> outerOrderAddHandler(CommomOrderParamDto innerOrderParam) {
        List<ApsOrder> orderList = new ArrayList<>();
        List<U9Material> u9MaterialList = innerOrderParam.getU9MaterialList();

        //委外新增
        List<ScmOrderAndMaterialCust> scmOutList = scmXbDeliveryDao.queryPurchaseOrder();
        for (ScmOrderAndMaterialCust scmOrder : scmOutList) {
            ApsOrder apsOrder = new ApsOrder();
            apsOrder.setOrderNo(scmOrder.getScmXbDelivery().getOrderNo());
            apsOrder.setPoNo(scmOrder.getScmXbDelivery().getPo());
            apsOrder.setMaterialId(NumberUtils.getLongValue(scmOrder.getU9Material().getId()));
            Optional<U9Material> material = u9MaterialList.stream().filter(m -> Objects.equals(m.getId(), apsOrder.getMaterialId().toString())).findFirst();
            if (material.isPresent()) {
                apsOrder.setProductModel(material.get().getProductModel());
            }
            if (scmOrder.getApsOrderExt() != null) {
                apsOrder.setFinishQty(scmOrder.getApsOrderExt().getFinishQty());
            }
            apsOrder.setMaterialCode(scmOrder.getU9Material().getCode());
            apsOrder.setMaterialName(scmOrder.getU9Material().getName());
            apsOrder.setMaterialSpec(scmOrder.getU9Material().getSpec());
            apsOrder.setCustomerName(scmOrder.getScmXbDelivery().getSupplierName());
            apsOrder.setCustomerCode(scmOrder.getScmXbDelivery().getSupplierCode());
            apsOrder.setDeliveryStartDate(scmOrder.getScmXbDelivery().getDeliveryStartDate());
            apsOrder.setDeliveryEndDate(scmOrder.getScmXbDelivery().getDeliveryEndDate());
            apsOrder.setOrderQty(scmOrder.getScmXbDelivery().getDeliveryQty());
            apsOrder.setOweQty(scmOrder.getScmXbDelivery().getOweQty());
            apsOrder.setPlanNum(0);
            apsOrder.setScmId(scmOrder.getScmXbDelivery().getId());
            apsOrder.setStatus(OrderStatusType.NoRelease);
            apsOrder.setType(ApsOrderType.OUTER);
            orderList.add(apsOrder);

        }
        return orderList;
    }

    /**
     * 内排订单新增处理
     * @return
     * @param innerOrderParam
     */
    public List<ApsOrder> innerOrderAddHandler(CommomOrderParamDto innerOrderParam) {

        List<ApsOrder> orderList = new ArrayList<>();
        List<U9Material> u9MaterialList = innerOrderParam.getU9MaterialList();
        List<ApsOrganize> apsOrganizes = innerOrderParam.getApsOrganizes();
        long t2 = System.currentTimeMillis();
        List<U9OrderCust> orderNotExists = scmXbDeliveryDao.queryInnerOrderAndNotExists();
        long t3 = System.currentTimeMillis();
        LogUtil.bizLog("queryInnerOrderAndNotExists耗时{}", t3 - t2);
        //内排新增
        for (U9OrderCust u9OrderCust : orderNotExists) {
            ApsOrder apsOrder = new ApsOrder();
            if (u9OrderCust.getU9ProduceOrder() != null) {
                apsOrder.setOrderNo(u9OrderCust.getU9ProduceOrder().getDocNo());
            }
            apsOrder.setMaterialId(NumberUtils.getLongValue(u9OrderCust.getU9Material().getId()));
            Optional<U9Material> material = u9MaterialList.stream().filter(m -> Objects.equals(m.getId(), apsOrder.getMaterialId().toString())).findFirst();
            if (material.isPresent()) {
                apsOrder.setProductModel(material.get().getProductModel());
            }
            apsOrder.setMaterialCode(u9OrderCust.getU9Material().getCode());
            apsOrder.setMaterialName(u9OrderCust.getU9Material().getName());
            apsOrder.setMaterialSpec(u9OrderCust.getU9Material().getSpec());
            apsOrder.setCapacity(u9OrderCust.getU9Material().getCapacity());
            if (u9OrderCust.getScmXbDelivery() != null) {
                apsOrder.setPoNo(u9OrderCust.getScmXbDelivery().getPo());
                apsOrder.setCustomerName(u9OrderCust.getScmXbDelivery().getCompanyName());
                apsOrder.setDeliveryStartDate(u9OrderCust.getScmXbDelivery().getDeliveryStartDate());
                apsOrder.setDeliveryEndDate(u9OrderCust.getScmXbDelivery().getDeliveryEndDate());
                apsOrder.setScmId(u9OrderCust.getScmXbDelivery().getId());
                apsOrder.setScmOweQty(u9OrderCust.getScmXbDelivery().getOweQty());
            }
            apsOrder.setOrderQty(NumberUtils.getBigDecimalValue(u9OrderCust.getU9ProduceOrder().getQty()));
            apsOrder.setOweQty(apsOrder.getOrderQty().subtract(NumberUtils.getBigDecimalValue(u9OrderCust.getU9ProduceOrder().getTotalCompleteQty())));
            apsOrder.setPlanFinishDate(apsOrder.getDeliveryStartDate());
            apsOrder.setOrderDate(DateUtils.date2LocalDate(u9OrderCust.getU9ProduceOrder().getCreatedDate()));
            apsOrder.setType(ApsOrderType.INNER);
            apsOrder.setProduceQty(u9OrderCust.getU9ProduceOrder().getTotalCompleteQty());
            //未排数初始化等于已排数
            apsOrder.setNoPlanQty(NumberUtils.getBigDecimalValue(u9OrderCust.getU9ProduceOrder().getQty()));
            apsOrder.setStatus(OrderStatusType.NoRelease);
            apsOrder.setU9Status(U9OrderStatus.transformStatus(u9OrderCust.getU9ProduceOrder().getStatus()));
            apsOrder.setTotalCompleteQty(u9OrderCust.getU9ProduceOrder().getTotalCompleteQty());
            Optional<ApsOrganize> dept = innerOrderParam.getApsOrganizes().stream().filter(a -> a.getCode().equals(u9OrderCust.getU9ProduceOrder().getDeptCode())).findFirst();
            if (dept.isPresent()) {
                ApsOrganize apsOrganize = dept.get();
                apsOrder.setWorkGroupId(apsOrganize.getId());
                apsOrder.setWorkGroupName(apsOrganize.getName());
            }
            Optional<ApsOrganize> line = innerOrderParam.getApsOrganizes().stream().filter(a -> a.getCode().equals(u9OrderCust.getU9ProduceOrder().getLineCode())).findFirst();
            if (line.isPresent()) {
                ApsOrganize apsOrganize = line.get();
                apsOrder.setWorkLineId(apsOrganize.getId());
                apsOrder.setWorkLineName(apsOrganize.getName());
            }
            if (u9OrderCust.getApsOrderExt() != null) {
                apsOrder.setFinishQty(u9OrderCust.getApsOrderExt().getFinishQty());
            }
            apsOrder.setPlanNum(0);
            orderList.add(apsOrder);

        }
       return orderList;
    }

    /**
     * 委外订单新增处理
     * @return
     */
    public List<ApsOrder> outerOrderModifyHandler() {
        List<ApsOrder> orderList = new ArrayList<>();
        List<OrderAndScmV2> scmOrderExists = getSelfService().queryPurchaseOrderAndExists();
        for (OrderAndScmV2 scmOrderExist : scmOrderExists) {
            ApsOrder apsOrder = new ApsOrder();
            BeanUtils.copyProperties(scmOrderExist,apsOrder);
            apsOrder.setOweQty(scmOrderExist.getScmXbDelivery().getOweQty());
            apsOrder.setOrderQty(scmOrderExist.getScmXbDelivery().getDeliveryQty());
            apsOrder.setDeliveryStartDate(scmOrderExist.getScmXbDelivery().getDeliveryStartDate());
            apsOrder.setDeliveryEndDate(scmOrderExist.getScmXbDelivery().getDeliveryEndDate());
            apsOrder.setProductModel(scmOrderExist.getScmXbDelivery().getProductModel());
            ApsOrderExt apsOrderExt = scmOrderExist.buildApsOrderExt(scmOrderExist);
            apsOrder.setFinishQty(apsOrderExt.getFinishQty());
            orderList.add(apsOrder);
        }
        return orderList;
    }

    /**
     * 获取委外更新单
     * @return
     */
    public List<OrderAndScmV2> queryPurchaseOrderAndExists() {
        List<Map<String,Object>> scmOrderExists = scmXbDeliveryDao.queryPurchaseOrderAndExists_v2();
        scmOrderExists = scmOrderExists.stream().map(MapUtil::toCamelCaseMap).collect(Collectors.toList());
        String irsStr = JSON.toJSONString(scmOrderExists);
        List<OrderAndScmV2> orderExistDtos = JSON.parseArray(irsStr, OrderAndScmV2.class);
        return orderExistDtos;
    }

    /**
     * 内排订单更新处理
     * @param innerOrderParam
     */
    public List<ApsOrder>  innerOrderModifyHandler(CommomOrderParamDto innerOrderParam) {
        List<ApsOrder> orderList = new ArrayList<>();
        List<U9Material> u9MaterialList = innerOrderParam.getU9MaterialList();
       // List<ApsOrganize> apsOrganizes = innerOrderParam.getApsOrganizes();
        List<OrderAndScmV2> orderExistDtos = getSelfService().queryInnerOrderAndExistsDto();
        for (OrderAndScmV2 orderExist : orderExistDtos) {
            //复制对象
            ApsOrder apsOrder = new ApsOrder();
            BeanUtils.copyProperties(orderExist,apsOrder);
            Optional<U9Material> material = u9MaterialList.stream().filter(m -> Objects.equals(Long.parseLong(Objects.requireNonNull(m.getId())), apsOrder.getMaterialId())).findFirst();
            if(!material.isPresent()){
                continue;
            }
            apsOrder.setProductModel(material.get().getProductModel());
            U9ProduceOrder u9ProduceOrder = orderExist.buildU9ProduceOrder(orderExist);
            if (u9ProduceOrder != null) {
                apsOrder.setOweQty(NumberUtils.getBigDecimalValue(u9ProduceOrder.getQty()).subtract(
                        NumberUtils.getBigDecimalValue(u9ProduceOrder.getTotalCompleteQty())));
                apsOrder.setU9Status(U9OrderStatus.transformStatus(orderExist.getU9ProduceOrder().getStatus()));
                apsOrder.setOrderQty(u9ProduceOrder.getQty());
                apsOrder.setProduceQty(u9ProduceOrder.getTotalCompleteQty());
                Optional<ApsOrganize> dept = innerOrderParam.getApsOrganizes().stream().filter(a -> a.getCode().equals(u9ProduceOrder.getDeptCode())).findFirst();
                if (dept.isPresent()) {
                    ApsOrganize apsOrganize = dept.get();
                    apsOrder.setWorkGroupId(apsOrganize.getId());
                    apsOrder.setWorkGroupName(apsOrganize.getName());
                }
                Optional<ApsOrganize> line = innerOrderParam.getApsOrganizes().stream().filter(a -> a.getCode().equals(u9ProduceOrder.getLineCode())).findFirst();
                if (line.isPresent()) {
                    ApsOrganize apsOrganize = line.get();
                    apsOrder.setWorkLineId(apsOrganize.getId());
                    apsOrder.setWorkLineName(apsOrganize.getName());
                }

            }
            //ScmXbDelivery scmXbDelivery = orderExist.getScmXbDelivery();
            ScmXbDelivery scmXbDelivery = orderExist.buildScmXbDelivery(orderExist);
            if (scmXbDelivery != null) {
                apsOrder.setDeliveryStartDate(scmXbDelivery.getDeliveryStartDate());
                apsOrder.setDeliveryEndDate(scmXbDelivery.getDeliveryEndDate());
                apsOrder.setProductModel(scmXbDelivery.getProductModel());
                apsOrder.setScmOweQty(scmXbDelivery.getOweQty());
            }
            //完工数量
            ApsOrderExt apsOrderExt = orderExist.buildApsOrderExt(orderExist);
            apsOrder.setFinishQty(apsOrderExt.getFinishQty());

            if (u9ProduceOrder != null || scmXbDelivery != null) {
                orderList.add(apsOrder);
            }


            //i++;
            //System.out.println(i);
        }
        return orderList;
    }

    /**
     * 获取需要更新内排订单
     * @return
     */
    public List<OrderAndScmV2> queryInnerOrderAndExistsDto() {
        List<Map<String,Object>>  orderExists = scmXbDeliveryDao.queryInnerOrderAndExists_v2();
        List<OrderAndScmV2> orderExistDtos = new ArrayList<OrderAndScmV2>(orderExists.size());
        //这里会有内存溢出、处理方法做分片
        List<List<Map<String, Object>>> partitionLists = Lists.partition(orderExists, partitionSize);
        if (partitionLists.size() > 0) {
            for (List<Map<String, Object>> partitionList : partitionLists) {
                partitionList = partitionList.stream().map(MapUtil::toCamelCaseMap).collect(Collectors.toList());
                String irsStr = JSON.toJSONString(partitionList);
                List<OrderAndScmV2> partitionDtos = JSON.parseArray(irsStr, OrderAndScmV2.class);
                orderExistDtos.addAll(partitionDtos);
            }
        }
        return orderExistDtos;
    }





    /**
     * 完成/暂停不变更数量
     * 更新前校验
     * 总排产数不能大于生产数
     * 已排数量 = 已排数量 + 排产数量
     * 未排数量 = 生产数量 - 已排数量
     *
     * @param entity
     * @return OperateResultWithData<ApsOrder>
     */
    @Override
    protected OperateResultWithData<ApsOrder> preUpdate(ApsOrder entity) {
        if (OrderStatusType.Completed.equals(entity.getStatus()) || OrderStatusType.Stop.equals(entity.getStatus())) {
            return super.preUpdate(entity);
        }
        entity.setTotalPlanQty(entity.getTotalPlanQty().add(entity.getPlanQty()));
      //entity.setNoPlanQty(entity.getProduceQty().subtract(entity.getTotalPlanQty()));
        if (entity.getTotalPlanQty().compareTo(entity.getProduceQty()) > 0) {
           // throw new IllegalArgumentException(ResultEnum.PLAN_QTY_ERROR.getMsg());
        } else if (entity.getTotalPlanQty().compareTo(entity.getProduceQty()) == 0) {
           // entity.setStatus(OrderStatusType.Released);
        } else if (entity.getTotalPlanQty().compareTo(BigDecimal.ZERO) > 0) {
           // entity.setStatus(OrderStatusType.Release_Part);
        }
        entity.setPlanQty(BigDecimal.ZERO);
        return super.preUpdate(entity);
    }

    /**
     * 恢复订单状态
     *
     * @param entity
     */
    public void recoverOrder(ApsOrder entity) {

        entity.setNoPlanQty(entity.getOweQty());
        entity.setTotalPlanQty(BigDecimal.ZERO);
        this.save(entity);
    }




    /**
     * 更新订单生产计划开始和结束时间
     *
     * @param apsOrderPlan
     */
    public void updateOrderStartDateAndEndDate(ApsOrderPlan apsOrderPlan) {
        Optional<ApsOrder> daoById = dao.findById(apsOrderPlan.getOrderId());
        if (daoById.isPresent()) {
            ApsOrder apsOrder = daoById.get();
            apsOrder.setPlanStartDate(apsOrderPlan.getStartDate());
            apsOrder.setPlanFinishDate(apsOrderPlan.getEndDate());
            apsOrder.setId(apsOrderPlan.getOrderId());
            dao.save(apsOrder);
        }

    }

    /**
     * 判断是否符合下达条件
     *
     * @param dto
     * @return
     */
    public boolean IsCanPlan(ApsOrderDto dto) {
        boolean flag = false;
        //先判断同订单当天是否已经有计划，有就不能下达
        List<ApsOrderPlanDetail> details = apsOrderPlanDetailDao.queryPlanByOrderNo(dto.getOrderNo(), dto.getPlanStartDate());
        if (details.size() == 0) {
            return true;
        }
        return flag;
    }
}
