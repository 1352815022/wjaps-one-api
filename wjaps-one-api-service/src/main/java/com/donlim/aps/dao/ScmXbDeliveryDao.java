package com.donlim.aps.dao;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.donlim.aps.dto.ScmXbDeliveryQueryDto;
import com.donlim.aps.entity.ScmXbDelivery;
import com.donlim.aps.entity.cust.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * scm送货需求(ScmXbDelivery)数据库访问类
 *
 * @author sei
 * @since 2022-05-18 08:12:55
 */
@Repository
public interface ScmXbDeliveryDao extends BaseEntityDao<ScmXbDelivery> {

    /**
     * 获取委外与采购订单(新增)
     * @return
     */
    @Query("select new com.donlim.aps.entity.cust.ScmOrderAndMaterialCust(d,m,e) " +
            "from ScmXbDelivery d inner join U9Material m on d.materialCode = m.code and d.orgId = m.orgId " +
            "left join ApsOrderExt e on e.orderNo = d.orderNo " +
            "where d.type = '0' " +
            "and not exists (select 1 from ApsOrder o where d.id = o.scmId )")
    List<ScmOrderAndMaterialCust> queryPurchaseOrder();

    /**
     * 获取内部待排数据(用于新增)
     * SCM
     * @return
     */
    @Query("select new com.donlim.aps.entity.cust.U9OrderCust(p,d,m,e) from U9ProduceOrder p" +
            " inner join U9Material m on m.id = p.material.id  " +
            " left join ScmXbDelivery d  on d.orderNo = p.soId and d.materialCode = m.code  and d.type = '1' " +
            " left join ApsOrderExt e  on e.orderNo = p.docNo  " +
            " where not exists (select 1 from ApsOrder i where i.orderNo = p.docNo )")
    List<U9OrderCust> queryInnerOrderAndNotExists();


    @Query(value = "select " +
            "    i.* ," +
            "    o.qty as u9Qty ," +
            "    o.total_complete_qty as u9TotalCompleteQty , " +
            "    o.status as u9orderStatus ," +
            "    d.delivery_start_date as deliveryStartDate , " +
            "    d.delivery_end_date  as deliveryEndDate, " +
            "    d.product_model  as scmXbProductModel, " +
            "    e.finish_qty  as finishQty, " +
            "    d.owe_qty as scmOweQty "+
            "from " +
            "    aps_order i  " +
            "inner join " +
            "    u9_produce_order o  " +
            "        on i.order_no = o.doc_no  " +
            "left join " +
            "    scm_xb_delivery d " +
            "        on i.scm_id = d.id " +
            "        and d.type = '1' " +
            "left join " +
            "    aps_order_ext e " +
            "        on i.order_no = e.order_no " +
            "where " +
            "    i.u9_status <>'Completed' and i.type= 'INNER' and DATE_SUB(CURDATE(), INTERVAL 30 DAY)<=o.created_date " , nativeQuery = true)
    List<Map<String,Object>> queryInnerOrderAndExists_v2();




    /**
     * 获取委外与采购订单(更新)
     * @return
     */
    @Query(value = "select o.*, " +
            "d.owe_qty as scmOweQty,\n" +
            "d.delivery_qty as scmDeliveryQty,\n" +
            "d.delivery_start_date as deliveryStartDate ,\n" +
            "d.delivery_end_date  as deliveryEndDate,\n" +
            "d.product_model  as scmXbProductModel \n" +
            "from  scm_xb_delivery d inner join  aps_order o on o.scm_id  = d.id\n" +
            "where d.type = '0'  " , nativeQuery = true )
    List<Map<String,Object>> queryPurchaseOrderAndExists_v2();


    /**
     * 根据po行号查询SCM送货单
     * @param poLineId 采购单行号
     * @return
     */
    ScmXbDelivery findTopByPoLineId(Long poLineId);

    /**
     * 根据采购单行ID查找送SCM送货单
     * @param poLineIds
     * @return
     */
    List<ScmXbDelivery> findByPoLineIdIn(List<Long>poLineIds);

    /**
     *获取订单条数
     * @param ld
     * @return
     */
    @Query(value="select COUNT(*)num ,order_no,product_model from scm_xb_delivery where delivery_start_date>?1 GROUP BY order_no",nativeQuery=true)
    List findOrderNum(LocalDate ld);

    /**
     * 根据需求分类号搜出订单
     * @param orderNo
     * @return
     */
    List<ScmXbDelivery>findAllByOrderNo(String orderNo);


    List<ScmXbDelivery>findByDeliveryStartDateAfter(LocalDate date);
    /**
     * 根据送货日期获取送货计划
     * @param query
     * @return
     */
    @Query(value = "select a from ScmXbDelivery a where a.supplierCode=:#{#query.supplierCode}" +
            " and (a.materialCode like %:#{#query.materialCode}% or :#{#query.materialCode} is null)" +
            " and (a.materialName like %:#{#query.materialName}% or :#{#query.materialName} is null)" +
            " and (a.spec like %:#{#query.materialSpec}% or :#{#query.materialSpec} is null)" +
            " and (a.deliveryStartDate>=:#{#query.startDate} or :#{#query.startDate} is null)" +
            " and (a.deliveryStartDate<=:#{#query.endDate} or :#{#query.endDate} is null)"+
            " and (a.orderNo=:#{#query.orderNo} or :#{#query.orderNo} is null)")
    List<ScmXbDelivery>queryDelivery(@Param("query") ScmXbDeliveryQueryDto query);

    /**
     * 动态查询
     * @param spec can be {@literal null}.
     * @return
     */
    List<ScmXbDelivery>findAll(Specification<ScmXbDelivery> spec);





}
