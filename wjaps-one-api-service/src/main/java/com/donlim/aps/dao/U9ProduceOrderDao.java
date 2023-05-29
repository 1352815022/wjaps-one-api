package com.donlim.aps.dao;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.donlim.aps.entity.U9ProduceOrder;
import com.donlim.aps.entity.cust.OrderAndU9;
import com.donlim.aps.entity.cust.U9OrderCust;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * 生产单表(U9)(U9ProduceOrder)数据库访问类
 *
 * @author sei
 * @since 2022-05-18 09:35:35
 */
@Repository
public interface U9ProduceOrderDao extends BaseEntityDao<U9ProduceOrder> {


    /**
     * 获取冲压订单
     * @param from , to
     * @return
     */
    @Query("select new com.donlim.aps.entity.cust.U9OrderCust(o,s,m) from U9ProduceOrder o inner join U9Material m on o.material.id=m.id" +
            " left join ScmXbDelivery s on o.soId = s.orderNo and m.code = s.materialCode " +
            " where m.deptCode in ('ETS10','ETS30') and m.type = '10' " +
            " and s.type = '1' " +
            " and s.deliveryStartDate between :from and :to ")
    List<U9OrderCust> queryStampingOrder(@Param("from") LocalDate from , @Param("to") LocalDate to);

    /**
     * 根据销售订单号与物料ids查找工单
     * @param soId 销售订单号
     * @param materialIds 物料ids
     * @return
     */
    List<U9ProduceOrder> findBySoIdAndMaterialIdIn(String soId,List<Long> materialIds);

    /**
     * 获取内排与u9订单集合（用于更新）
     * @return
     */
    @Query("select new com.donlim.aps.entity.cust.OrderAndU9(i,o) from ApsOrder i inner join U9ProduceOrder o on i.orderNo = o.docNo")
    List<OrderAndU9> findAllInnerOrder();

    /**
     * 根据销售订单号与物料ids查找工单与内排集合
     * @param soId
     * @param materialIds
     * @return
     */
    @Query("select new com.donlim.aps.entity.cust.OrderAndU9(i,o,m) from ApsOrder i inner join U9ProduceOrder o on i.orderNo = o.docNo " +
            " inner join U9Material m on i.materialId = m.id " +
            " where o.soId = :soId and o.materialId in (:materialIds) and not exists (select 1 from ApsOrderPlan p where i.id = p.orderId )")
    List<OrderAndU9> queryInnerOrderBySoIdAndMaterialIdIn(@Param("soId") String soId, @Param("materialIds") List<Long> materialIds);

    /**
     * 获取待排子件(新增)
     * @param soIds
     * @return
     */
    @Query("select o from U9ProduceOrder o where o.soId in (:soIds) and not exists (select 1 from ApsOrder i where o.docNo = i.orderNo)")
    List<U9ProduceOrder> queryU9OrderInSoIdAndNotExistsInner(@Param("soIds")Set<String> soIds);

    /**
     * 获取待排库存订单(新增)
     * @return
     */
    @Query("select o from U9ProduceOrder o where not exists (select 1 from ApsOrder i where o.docNo = i.orderNo)")
    List<U9ProduceOrder> queryNotExistsInner();

    U9ProduceOrder findAllByDocNo(String OrderNo);

    /**
     * 需求分类号
     * @param orderNo
     * @return
     */
    List<U9ProduceOrder>findAllByDocNoStartsWith(String orderNo);
    /**
     * 根据需求分类号
     * @param orderNo
     * @return
     */
    List<U9ProduceOrder>findAllBySoIdAndMaterialCode(String orderNo,String materialCode);
    @Query("select docNo from U9ProduceOrder where status=3 and docNo in :orderList")
    List<String> findCompleteOrder(List<String>orderList);
}
