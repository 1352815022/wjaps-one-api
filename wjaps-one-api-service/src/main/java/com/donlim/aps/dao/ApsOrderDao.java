package com.donlim.aps.dao;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.donlim.aps.dto.ApsOrderType;
import com.donlim.aps.entity.ApsOrder;
import com.donlim.aps.entity.cust.OrderAndPurchasePlan;
import com.donlim.aps.entity.cust.OrderAndScm;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * 订单表(ApsOrder)数据库访问类
 *
 * @author sei
 * @since 2022-05-10 15:29:57
 */
@Repository
public interface ApsOrderDao extends BaseEntityDao<ApsOrder> {

    /**
     * 根据日期后的
     * @param date 日期
     * @param type 类别
     * @return List<ApsOrder>
     */
    List<ApsOrder> findByDeliveryEndDateAfterAndType(LocalDate date, ApsOrderType type);

    /**
     * 获取委外订单（新增）
     * @return
     */
    @Query("select new com.donlim.aps.entity.cust.OrderAndScm(ao,sxd,m) from ApsOrder ao  " +
            " inner join ScmXbDelivery sxd on ao.scmId=sxd.id and ao.type = 'OUTER' and ao.materialCode = sxd.materialCode  " +
            " and sxd.type = '0' " +
            " inner  join U9Material m on ao.materialId =  m.id " +
            " where ao.id in :orderIds and  not exists (select 1 from ApsPurchasePlan aop where ao.id = aop.orderId) ")
    List<OrderAndScm> queryPurchaseOrderForAdd(List<String>orderIds);

    /**
     * 获取委外订单（更新）
     * @return
     */
    @Query("select new com.donlim.aps.entity.cust.OrderAndPurchasePlan(o,p) from ApsOrder o inner join ApsPurchasePlan p on o.id = p.orderId where o.type = 'OUTER' ")
    List<OrderAndPurchasePlan> queryPurchaseOrderForUpdate();


    /**
     * 获取所有主计划工单（新增）
     * @return
     */
    @Query("select new com.donlim.aps.entity.cust.OrderAndScm(ao,sxd,upo,m) from ApsOrder ao inner join U9ProduceOrder upo on ao.orderNo = upo.docNo and ao.type = 'INNER' " +
            " inner join ScmXbDelivery sxd on sxd.orderNo = upo.soId and ao.materialCode = sxd.materialCode and upo.orgId = sxd.orgId " +
            " and sxd.type = '1' " +
            " inner  join U9Material m on ao.materialId =  m.id " +
            " where  not exists (select 1 from ApsOrderPlan aop where ao.id = aop.orderId) ")
    List<OrderAndScm> findWithScmAndU9Produce();



    List<ApsOrder> findByType(ApsOrderType inner);


}
