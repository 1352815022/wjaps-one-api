package com.donlim.aps.dao;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.donlim.aps.dto.OrderStatusType;
import com.donlim.aps.entity.ApsOrderPlan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * 生产计划表(内部)(ApsOrderPlan)数据库访问类
 *
 * @author sei
 * @since 2022-05-11 15:12:30
 */
@Repository
public interface ApsOrderPlanDao extends BaseEntityDao<ApsOrderPlan> {

    /**
     * 根据内排单id查询
     * @param orderId
     * @return
     */
    List<ApsOrderPlan> findByOrderId(String orderId);

    /**
     * 根据内排单id查询
     * @param orderIds
     * @return
     */
    List<ApsOrderPlan> findByIdIn(List<String>orderIds);

    /**
     * 根据工单号获排产计划
     * @param orderList
     * @return
     */
    @Query(value="select a from ApsOrderPlan a inner join ApsOrder b on a.orderId=b.id  where b.orderNo in (:orderList)")
    List<ApsOrderPlan>findByOrderNo(@Param("orderList") List<String> orderList);

    /**
     * 获取当天时间内的计划数
     * @param startDate
     * @param endDate
     * @return
     */
    @Query(value="select count(*)  from aps_order_plan a inner join aps_order_plan_detail b on a.id =b.plan_id where b.plan_date >= ?1 and b.plan_date<=?2"
            ,nativeQuery = true)
    Integer countPlanByDate(LocalDate startDate,LocalDate endDate);

    List<ApsOrderPlan>findAllByStatus(OrderStatusType status);

    /**
     * 根据内排单id查询
     * @param orderIds
     * @return
     */
    List<ApsOrderPlan> findByOrderIdIn(List<String> orderIds);


}
