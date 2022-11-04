package com.donlim.aps.dao;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.donlim.aps.dto.ScmXbDeliveryQueryDto;
import com.donlim.aps.entity.ScmXbDelivery;
import com.donlim.aps.entity.cust.OrderAndScm;
import com.donlim.aps.entity.cust.ScmOrderAndMaterialCust;
import com.donlim.aps.entity.cust.U9OrderCust;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

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
    @Query("select new com.donlim.aps.entity.cust.ScmOrderAndMaterialCust(d,m) " +
            "from ScmXbDelivery d inner join U9Material m on d.materialCode = m.code and d.orgId = m.orgId " +
            "where d.type = '0' " +
            "and not exists (select 1 from ApsOrder o where d.id = o.scmId )")
    List<ScmOrderAndMaterialCust> queryPurchaseOrder();

    /**
     * 获取内部待排数据(用于新增)
     * SCM
     * @return
     */
    @Query("select new com.donlim.aps.entity.cust.U9OrderCust(p,d,m) from U9ProduceOrder p" +
            " inner join U9Material m on m.id = p.material.id and p.orgId = m.orgId  " +
            " left join ScmXbDelivery d  on d.orderNo = p.soId and d.materialCode = m.code and p.orgId = d.orgId and d.type = '1' " +
            " where not exists (select 1 from ApsOrder i where i.orderNo = p.docNo )")
    List<U9OrderCust> queryInnerOrderAndNotExists();

    /**
     * 获取内部待排数据(用于更新)
     * SCM
     * @return
     */
    @Query("select new com.donlim.aps.entity.cust.OrderAndScm(i,d,o) from ApsOrder i " +
            " left join U9ProduceOrder o on i.orderNo = o.docNo " +
            " left join ScmXbDelivery d on i.scmId = d.id and d.type = '1' " +
            " where i.type= 'INNER' ")
    List<OrderAndScm> queryInnerOrderAndExists();

    /**
     * 获取委外与采购订单(更新)
     * @return
     */
    @Query("select new com.donlim.aps.entity.cust.OrderAndScm(o,d) " +
            "from ScmXbDelivery d inner join ApsOrder o on o.scmId = d.id " +
            "where d.type = '0' " )
    List<OrderAndScm> queryPurchaseOrderAndExists();

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

    /**
     * 根据送货日期获取送货计划
     * @param date
     * @return
     */
    List<ScmXbDelivery>findByDeliveryStartDateAndSupplierCode(LocalDate date,String supplierCode);

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


}
