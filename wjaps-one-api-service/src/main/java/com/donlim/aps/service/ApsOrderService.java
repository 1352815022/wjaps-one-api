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

    @Override
    protected BaseEntityDao<ApsOrder> getDao() {
        return dao;
    }

    //解决事务失效
    private ApsOrderService getSelfService(){
        return SpringUtil.getBean(this.getClass());   //SpringUtil工具类见下面代码
    }

    private final Integer partitionSize = 50000000;
    private final Integer partitionCountLimit = 10000000;
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
        List<ApsOrder> outerOrders = getSelfService().outerOrderModifyHandler();
        long t5= System.currentTimeMillis();
        LogUtil.bizLog("outerOrderModifyHandler耗时{}", t5 - t4);
        //内排新增
        List<ApsOrder> innerOrdersNew =  getSelfService().innerOrderAddHandler(innerOrderParam);
        long t6= System.currentTimeMillis();
        LogUtil.bizLog("innerOrderAddHandle耗时{}", t6 - t5);
        //委外新增
        List<ApsOrder> outerOrdersNew = getSelfService().outerOrderAddHandler(innerOrderParam);
        long t7= System.currentTimeMillis();
        LogUtil.bizLog("outerOrderAddHandler耗时{}", t7 - t6);

        orderList.addAll(outerOrders);
        orderList.addAll(innerOrders);
        orderList.addAll(innerOrdersNew);
        orderList.addAll(outerOrdersNew);
        //分片持久化
        long t8= System.currentTimeMillis();
        getSelfService().orderSaveAll(orderList);
        long t9= System.currentTimeMillis();
        LogUtil.bizLog("orderSaveAll持久化耗时{}", t9 - t8);
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
            apsOrder.setDeliveryStartDate(scmOrder.getScmXbDelivery().getDeliveryEndDate());
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
            }
            apsOrder.setOrderQty(NumberUtils.getBigDecimalValue(u9OrderCust.getU9ProduceOrder().getQty()));
            apsOrder.setOweQty(apsOrder.getOrderQty().subtract(NumberUtils.getBigDecimalValue(u9OrderCust.getU9ProduceOrder().getTotalCompleteQty())));
            apsOrder.setPlanFinishDate(apsOrder.getDeliveryStartDate());
            apsOrder.setOrderDate(DateUtils.date2LocalDate(u9OrderCust.getU9ProduceOrder().getCreatedDate()));
            apsOrder.setType(ApsOrderType.INNER);
            if (apsOrder.getOweQty().compareTo(BigDecimal.ZERO) <= 0) {
                continue;
            }
            apsOrder.setProduceQty(apsOrder.getOweQty());
            apsOrder.setNoPlanQty(apsOrder.getProduceQty());
            apsOrder.setStatus(OrderStatusType.NoRelease);
            apsOrder.setU9Status(U9OrderStatus.transformStatus(u9OrderCust.getU9ProduceOrder().getStatus()));
            apsOrder.setTotalCompleteQty(u9OrderCust.getU9ProduceOrder().getTotalCompleteQty());
            String deptCode = u9OrderCust.getU9Material().getDeptCode();
            if (StringUtils.isNotEmpty(deptCode)) {
                Optional<ApsOrganize> first = apsOrganizes.stream().filter(e -> deptCode.equals(e.getCode())).findFirst();
                if (first.isPresent()) {
                    ApsOrganize apsOrganize = first.get();
                    apsOrder.setWorkGroupId(apsOrganize.getId());
                    apsOrder.setWorkGroupName(apsOrganize.getName());
                }
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
        List<ApsOrganize> apsOrganizes = innerOrderParam.getApsOrganizes();

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
            String deptCode = material.get().getDeptCode();
            if (StringUtils.isNotEmpty(deptCode)) {
                Optional<ApsOrganize> first = apsOrganizes.stream().filter(e -> deptCode.equals(e.getCode())).findFirst();
                if (first.isPresent()) {
                    ApsOrganize apsOrganize = first.get();
                    apsOrder.setWorkGroupId(apsOrganize.getId());
                    apsOrder.setWorkGroupName(apsOrganize.getName());
                }
            }
            //U9ProduceOrder u9ProduceOrder = orderExist.getU9ProduceOrder();
            U9ProduceOrder u9ProduceOrder = orderExist.buildU9ProduceOrder(orderExist);
            if (u9ProduceOrder != null) {
                apsOrder.setOweQty(NumberUtils.getBigDecimalValue(u9ProduceOrder.getQty()).subtract(
                        NumberUtils.getBigDecimalValue(u9ProduceOrder.getTotalCompleteQty())));
                apsOrder.setU9Status(U9OrderStatus.transformStatus(orderExist.getU9ProduceOrder().getStatus()));
                apsOrder.setProduceQty(u9ProduceOrder.getQty());
            }
            //ScmXbDelivery scmXbDelivery = orderExist.getScmXbDelivery();
            ScmXbDelivery scmXbDelivery = orderExist.buildScmXbDelivery(orderExist);
            if (scmXbDelivery != null) {
                apsOrder.setDeliveryStartDate(scmXbDelivery.getDeliveryStartDate());
                apsOrder.setDeliveryEndDate(scmXbDelivery.getDeliveryEndDate());
                apsOrder.setProductModel(scmXbDelivery.getProductModel());
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
     * 新增:
     * * 拉取u9_produce_order生产单数据至apsOrder type = INNER
     * * 拉取scm_xb_delivery type = 0委外单数据至apsOrder type = OUTER
     * * * 欠入库数：工单数量-完成数量
     * * * 生产数量：欠数
     * * * 未排数量：生产数量
     * 更新: scmXbDeliveryDao.queryInnerOrderAndExists()  内排
     * 更新: scmXbDeliveryDao.queryPurchaseOrderAndExists()  委外
     */
    @Transactional(rollbackFor = Exception.class)
    public void pullData() {
        List<ApsOrder> orderList = new ArrayList<>();
        List<U9Material> u9MaterialList = u9MaterialService.findByProductModelIsNotNull();
        List<U9OrderCust> orderNotExists = scmXbDeliveryDao.queryInnerOrderAndNotExists();
        List<ApsOrganize> apsOrganizes = apsOrganizeDao.findAll();
        //内排更新
        List<OrderAndScm> orderExists = scmXbDeliveryDao.queryInnerOrderAndExists();
        for (OrderAndScm orderExist : orderExists) {
            ApsOrder apsOrder = orderExist.getApsOrder();
            Optional<U9Material> material = u9MaterialList.stream().filter(m -> Objects.equals(Long.parseLong(Objects.requireNonNull(m.getId())), apsOrder.getMaterialId())).findFirst();
            if(!material.isPresent()){
                continue;
            }
            apsOrder.setProductModel(material.get().getProductModel());
            String deptCode = material.get().getDeptCode();
            if (StringUtils.isNotEmpty(deptCode)) {
                Optional<ApsOrganize> first = apsOrganizes.stream().filter(e -> deptCode.equals(e.getCode())).findFirst();
                if (first.isPresent()) {
                    ApsOrganize apsOrganize = first.get();
                    apsOrder.setWorkGroupId(apsOrganize.getId());
                    apsOrder.setWorkGroupName(apsOrganize.getName());
                }
            }
            U9ProduceOrder u9ProduceOrder = orderExist.getU9ProduceOrder();
            if (u9ProduceOrder != null) {
                apsOrder.setOweQty(NumberUtils.getBigDecimalValue(u9ProduceOrder.getQty()).subtract(
                        NumberUtils.getBigDecimalValue(u9ProduceOrder.getTotalCompleteQty())));
                apsOrder.setU9Status(U9OrderStatus.transformStatus(orderExist.getU9ProduceOrder().getStatus()));
            }
            ScmXbDelivery scmXbDelivery = orderExist.getScmXbDelivery();
            if (scmXbDelivery != null) {
                apsOrder.setDeliveryStartDate(scmXbDelivery.getDeliveryStartDate());
                apsOrder.setDeliveryEndDate(scmXbDelivery.getDeliveryEndDate());
                apsOrder.setProductModel(scmXbDelivery.getProductModel());
            }
            if (u9ProduceOrder != null || scmXbDelivery != null) {
                orderList.add(apsOrder);
            }
        }
        //委外更新
        List<OrderAndScm> scmOrderExists = scmXbDeliveryDao.queryPurchaseOrderAndExists();
        for (OrderAndScm scmOrderExist : scmOrderExists) {
            ApsOrder apsOrder = scmOrderExist.getApsOrder();
            apsOrder.setOweQty(scmOrderExist.getScmXbDelivery().getOweQty());
            apsOrder.setOrderQty(scmOrderExist.getScmXbDelivery().getDeliveryQty());
            apsOrder.setDeliveryStartDate(scmOrderExist.getScmXbDelivery().getDeliveryStartDate());
            apsOrder.setDeliveryEndDate(scmOrderExist.getScmXbDelivery().getDeliveryEndDate());
            apsOrder.setProductModel(scmOrderExist.getScmXbDelivery().getProductModel());
            orderList.add(apsOrder);
        }
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
            }
            apsOrder.setOrderQty(NumberUtils.getBigDecimalValue(u9OrderCust.getU9ProduceOrder().getQty()));
            apsOrder.setOweQty(apsOrder.getOrderQty().subtract(NumberUtils.getBigDecimalValue(u9OrderCust.getU9ProduceOrder().getTotalCompleteQty())));
            apsOrder.setPlanFinishDate(apsOrder.getDeliveryStartDate());
            apsOrder.setOrderDate(DateUtils.date2LocalDate(u9OrderCust.getU9ProduceOrder().getCreatedDate()));
            apsOrder.setType(ApsOrderType.INNER);
            if (apsOrder.getOweQty().compareTo(BigDecimal.ZERO) <= 0) {
                continue;
            }
            apsOrder.setProduceQty(apsOrder.getOweQty());
            apsOrder.setNoPlanQty(apsOrder.getProduceQty());
            apsOrder.setStatus(OrderStatusType.NoRelease);
            apsOrder.setU9Status(U9OrderStatus.transformStatus(u9OrderCust.getU9ProduceOrder().getStatus()));
            apsOrder.setTotalCompleteQty(u9OrderCust.getU9ProduceOrder().getTotalCompleteQty());
            String deptCode = u9OrderCust.getU9Material().getDeptCode();
            if (StringUtils.isNotEmpty(deptCode)) {
                Optional<ApsOrganize> first = apsOrganizes.stream().filter(e -> deptCode.equals(e.getCode())).findFirst();
                if (first.isPresent()) {
                    ApsOrganize apsOrganize = first.get();
                    apsOrder.setWorkGroupId(apsOrganize.getId());
                    apsOrder.setWorkGroupName(apsOrganize.getName());
                }
            }

            apsOrder.setPlanNum(0);
            orderList.add(apsOrder);

        }

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
            apsOrder.setMaterialCode(scmOrder.getU9Material().getCode());
            apsOrder.setMaterialName(scmOrder.getU9Material().getName());
            apsOrder.setMaterialSpec(scmOrder.getU9Material().getSpec());
            apsOrder.setCustomerName(scmOrder.getScmXbDelivery().getSupplierName());
            apsOrder.setCustomerCode(scmOrder.getScmXbDelivery().getSupplierCode());
            apsOrder.setDeliveryStartDate(scmOrder.getScmXbDelivery().getDeliveryStartDate());
            apsOrder.setDeliveryStartDate(scmOrder.getScmXbDelivery().getDeliveryEndDate());
            apsOrder.setOrderQty(scmOrder.getScmXbDelivery().getDeliveryQty());
            apsOrder.setOweQty(scmOrder.getScmXbDelivery().getOweQty());
            apsOrder.setPlanNum(0);
            apsOrder.setScmId(scmOrder.getScmXbDelivery().getId());
            apsOrder.setStatus(OrderStatusType.NoRelease);
            apsOrder.setType(ApsOrderType.OUTER);
            orderList.add(apsOrder);

        }
        this.save(orderList);


    }

   /* private ApsOrder createInnerOrder(U9ProduceOrder u9ProduceOrder, ApsOrderType type) {
        ApsOrder apsOrder = new ApsOrder();
        apsOrder.setOrderNo(u9ProduceOrder.getDocNo());
        apsOrder.setMaterialId(u9ProduceOrder.getMaterialId());
        apsOrder.setMaterialCode(u9ProduceOrder.getMaterial().getCode());
        apsOrder.setMaterialName(u9ProduceOrder.getMaterial().getName());
        apsOrder.setMaterialSpec(u9ProduceOrder.getMaterial().getSpec());
        apsOrder.setOrderQty(NumberUtils.getBigDecimalValue(u9ProduceOrder.getQty()));
        apsOrder.setOweQty(NumberUtils.subtractBigDecimal(apsOrder.getOrderQty(), u9ProduceOrder.getTotalCompleteQty()));
        if (apsOrder.getOweQty().compareTo(BigDecimal.ZERO) <= 0) {
            return null;
        }
        apsOrder.setType(type);
        apsOrder.setOrderDate(DateUtils.date2LocalDate(u9ProduceOrder.getCreatedDate()));
        apsOrder.setProduceQty(apsOrder.getOweQty());
        apsOrder.setNoPlanQty(apsOrder.getProduceQty());
        apsOrder.setStatus(OrderStatusType.NoRelease);
        apsOrder.setPlanNum(0);
        return apsOrder;
    }*/

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
        entity.setNoPlanQty(entity.getProduceQty().subtract(entity.getTotalPlanQty()));
        if (entity.getTotalPlanQty().compareTo(entity.getProduceQty()) > 0) {
            throw new IllegalArgumentException(ResultEnum.PLAN_QTY_ERROR.getMsg());
        } else if (entity.getTotalPlanQty().compareTo(entity.getProduceQty()) == 0) {
            entity.setStatus(OrderStatusType.Released);
        } else if (entity.getTotalPlanQty().compareTo(BigDecimal.ZERO) > 0) {
            entity.setStatus(OrderStatusType.Release_Part);
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
     * 按照选定时间范围进行订单的组件拆分，并合并新的虚拟单
     * 拆分料名带有<mark>组件</mark>二字，并合并范围内相同料品，汇总数量，创建新的虚拟单
     * 拆分过程中，组件下组件需继续拆分，并根据bom的用量计算出需求数量
     * 使原单<mark>merge_flag</mark>为true
     * 并记录合并历史到aps_order_inner_bom_his中
     *
     * @param range 传入开始与结束日期范围
     */
    /*@Transactional(rollbackFor = Exception.class)
    public void splitAndMerge(DateRange range) {
        Map<String, List<OrderSplit>> splitMap = new HashMap<>();
        List<ApsOrder> orders = dao.findByOrderDateBetween(range.getEffectiveFrom(), range.getEffectiveTo());
        for (ApsOrder order : orders) {
            boolean splitFlag = splitOrder(splitMap, order);
        }
        List<ApsOrder> orderList = mergeOrder(splitMap);
        this.save(orderList);
        this.save(orders);
        saveSplitHis(orderList, splitMap);
    }*/


    /**
     * @param splitMap 拆分集合，<物料编码，合并列表>
     * @param order    拆分目标
     * @return boolean true:已拆 ; false:不可拆
     */
 /*   private boolean splitOrder(Map<String, List<OrderSplit>> splitMap, ApsOrder order) {
        if (StringUtils.contains(order.getMaterialName(), "组件")) {
            LinkedList<Long> searchQueue = new LinkedList<>();
            LinkedList<BigDecimal> usageQueue = new LinkedList<>();
            searchQueue.add(order.getMaterialId());
            while (!searchQueue.isEmpty()) {
                List<U9Bom> bomList = bomDao.findByMasterId(searchQueue.remove());
                BigDecimal usage = usageQueue.size() > 0 ? usageQueue.remove() : new BigDecimal(1);
                for (U9Bom bom : bomList) {
                    if (StringUtils.contains(bom.getMaterial().getName(), "组件")) {
                        searchQueue.add(Long.valueOf(bom.getMaterial().getId()));
                        usageQueue.add(usage.multiply(bom.getQty()));
                    } else {
                        OrderSplit orderSplit = new OrderSplit();
                        orderSplit.setOrderId(order.getId());
                        orderSplit.setMaterialId(Long.valueOf(bom.getMaterial().getId()));
                        orderSplit.setMaterialCode(bom.getMaterial().getCode());
                        orderSplit.setMaterialSpec(bom.getMaterial().getSpec());
                        orderSplit.setOrderQty(order.getOrderQty());
                        orderSplit.setBomUsage(usage);
                        if (splitMap.containsKey(bom.getMaterial().getCode())) {
                            splitMap.get(bom.getMaterial().getCode()).add(orderSplit);
                        } else {
                            List<OrderSplit> splitList = new ArrayList<>();
                            splitList.add(orderSplit);
                            splitMap.put(bom.getMaterial().getCode(), splitList);
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }*/

    /**
     * 合并拆分后的组件
     *
     * @param splitMap 拆分后的组件
     * @return List<ApsOrderInner> 返回虚拟单
     */
   /* private List<ApsOrder> mergeOrder(Map<String, List<OrderSplit>> splitMap) {
        List<ApsOrder> orderList = new ArrayList<>();
        for (Map.Entry<String, List<OrderSplit>> entry : splitMap.entrySet()) {
            ApsOrder order = new ApsOrder();
            OrderSplit orderSplit = entry.getValue().get(0);
            String number = serialService.getNumber(order.getClass());
            order.setOrderNo(number);
            order.setOrderDate(LocalDate.now());
            BigDecimal qty = new BigDecimal(0);
            for (OrderSplit split : entry.getValue()) {
                qty = qty.add(split.getBomUsage().multiply(split.getOrderQty()));
            }
            order.setOrderQty(qty);
            order.setMaterialId(orderSplit.getMaterialId());
            order.setMaterialCode(orderSplit.getMaterialCode());
            order.setMaterialName(orderSplit.getMaterialName());
            order.setMaterialSpec(orderSplit.getMaterialSpec());
            order.setProduceQty(order.getOrderQty());
            order.setNoPlanQty(order.getOrderQty());
            order.setTotalPlanQty(BigDecimal.ZERO);
            order.setPlanQty(BigDecimal.ZERO);
            orderList.add(order);
        }
        return orderList;
    }*/

    /**
     * 保存组件拆分、合并历史记录
     *
     * @param orderList 合并后订单
     * @param splitMap  合并前拆分数据
     */
    /*private void saveSplitHis(List<ApsOrder> orderList, Map<String, List<OrderSplit>> splitMap) {
        List<ApsOrderInnerBomHis> hisList = new ArrayList<>();
        for (ApsOrder order : orderList) {
            for (OrderSplit split : splitMap.get(order.getMaterialCode())) {
                ApsOrderInnerBomHis his = new ApsOrderInnerBomHis();
                his.setNewId(order.getId());
                his.setMaterialId(split.getMaterialId());
                his.setOriginId(split.getOrderId());
                his.setOrderQty(split.getOrderQty());
                his.setUsage(split.getBomUsage());
                hisList.add(his);
            }
        }
        apsOrderInnerBomHisService.save(hisList);
    }*/

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
