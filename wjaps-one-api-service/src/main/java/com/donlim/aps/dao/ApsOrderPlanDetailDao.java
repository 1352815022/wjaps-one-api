package com.donlim.aps.dao;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.donlim.aps.dto.ApsDayPlanReportDto;
import com.donlim.aps.entity.ApsOrderPlanDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * 生产计划明细表(ApsOrderPlanDetail)数据库访问类
 *
 * @author sei
 * @since 2022-05-16 08:42:08
 */
@Repository
public interface ApsOrderPlanDetailDao extends BaseEntityDao<ApsOrderPlanDetail> {

    /**
     * 根据排产id与排产日期查询排产明细
     *
     * @param planId   parentId 生产计划id
     * @param planDate 排产日期
     * @return
     */
    ApsOrderPlanDetail findTopByPlanIdAndPlanDate(String planId, LocalDate planDate);

    @Query("select b from ApsOrderPlan a inner join ApsOrderPlanDetail b on a.id = b.planId " +
            " where a.status='Normal' and b.planDate=:date")
    List<ApsOrderPlanDetail> findAllByPlanDate(LocalDate date);

    @Query("select b from ApsOrderPlan a inner join ApsOrderPlanDetail b on a.id = b.planId " +
            " where a.status='Normal' and b.planDate>=:start and b.planDate<=:end")
    List<ApsOrderPlanDetail> findAllByPlanDate(LocalDate start,LocalDate end);
    /**
     * 根据日期范围获取内排单id
     *
     * @param start
     * @param end
     * @return
     */
    @Query("select distinct p.orderId from ApsOrderPlan p inner join ApsOrderPlanDetail d on  p.id = d.planId where d.planDate between :start and :end ")
    List<String> queryDistinctBetweenPlanDate(@Param("start") LocalDate start, @Param("end") LocalDate end);


    List<ApsOrderPlanDetail> findByApsOrderPlanOrderIdIn(List<String> orderIds);

    /**
     * 根据车间、班组查询最后一条记录
     *
     * @param workGroupId
     * @param lineId
     * @return
     */
    @Query("select b from ApsOrderPlan a inner join ApsOrderPlanDetail b on a.id = b.planId " +
            " where a.status='Normal' and a.workGroupId = :workGroupId and a.lineId = :lineId" +
            " order by b.planDate desc ")
    List<ApsOrderPlanDetail> queryLastRecordByWorkGroupAndLine(@Param("workGroupId") String workGroupId, @Param("lineId") String lineId);

    /**
     * 根据车间料号查询最后一条记录
     *
     * @param workGroupId
     * @return
     */
    @Query(value = "select b from ApsOrderPlan a inner join ApsOrderPlanDetail b on a.id = b.planId " +
            " where a.status='Normal' and a.workGroupId = :workGroupId and a.materialCode= :materialCode" +
            " order by b.planDate desc")
    List<ApsOrderPlanDetail> queryLastRecordByWorkGroupAndMaterialCode(@Param("workGroupId") String workGroupId, @Param("materialCode") String materialCode);

    /**
     * 按日期获取计划明细
     *
     * @param workGroupId  车间
     * @param materialCode 料号
     * @param planDate     计划日期
     * @return
     */
    @Query(value = "select b from ApsOrderPlan a inner join ApsOrderPlanDetail b on a.id = b.planId " +
            " where a.status='Normal' and a.workGroupId = :workGroupId and a.materialCode= :materialCode" +
            " and b.planDate= :planDate order by b.planDate")
    List<ApsOrderPlanDetail> queryRecordByPlanDate(@Param("workGroupId") String workGroupId, @Param("materialCode") String materialCode, @Param("planDate") LocalDate planDate);

    /**
     * 获取车间料号当天已经排产的记录
     *
     * @param workGroupId
     * @return
     */
    @Query(value = "select b from ApsOrderPlan a inner join ApsOrderPlanDetail b on a.id = b.planId " +
            " where a.status='Normal' and a.workGroupId = :workGroupId and a.materialCode= :materialCode" +
            " and b.planDate>= :planDate and a.id<> :planId  order by b.planDate")
    List<ApsOrderPlanDetail> queryRecordPlan(@Param("workGroupId") String workGroupId, @Param("materialCode") String materialCode, @Param("planDate") LocalDate planDate, @Param("planId") String planId);

    /**
     * 获取某计划的排产记录
     *
     * @param planDate
     * @param planId
     * @return
     */
    @Query(value = "select b from ApsOrderPlan a inner join ApsOrderPlanDetail b on a.id = b.planId " +
            " where a.status='Normal'" +
            " and b.planDate>= :planDate and a.id= :planId  order by b.planDate")
    List<ApsOrderPlanDetail> queryRecordPlan(@Param("planDate") LocalDate planDate, @Param("planId") String planId);

    List<ApsOrderPlanDetail> queryAllByPlanDateAfterAndPlanId(@Param("planDate") LocalDate planDate, @Param("planId") String planId);


    /**
     * 根据计划日期获取订单号排产记录
     *
     * @param planDate 计划日期
     * @param orderNo  订单号
     * @return
     */
    @Query(value = "select b from ApsOrderPlan a inner join ApsOrderPlanDetail b on a.id = b.planId " +
            " where a.status='Normal'" +
            " and b.planDate= :planDate and a.order.orderNo= :orderNo")
    List<ApsOrderPlanDetail> queryPlanByOrderNo(@Param("orderNo") String orderNo, @Param("planDate") LocalDate planDate);


    /**
     * 获取订单号排产记录
     *
     * @param orderNo 订单号
     * @return
     */
    @Query(value = "select b from ApsOrderPlan a inner join ApsOrderPlanDetail b on a.id = b.planId " +
            " where a.status='Normal'" +
            " and a.order.orderNo= :orderNo")
    List<ApsOrderPlanDetail> queryPlanByOrderNo(@Param("orderNo") String orderNo);

    /**
     * 查询生产计划
     * @param query
     * @return
     */
    @Query(value = "select b from ApsOrderPlan a inner join ApsOrderPlanDetail b on a.id=b.planId" +
            " where a.order.type='INNER' and a.status='Normal' " +
            " and (a.materialCode like %:#{#query.materialCode}% or :#{#query.materialCode} is null)" +
            " and (a.materialName like %:#{#query.materialName}% or :#{#query.materialName} is null)" +
            " and (a.materialSpec like %:#{#query.materialSpec}% or :#{#query.materialSpec} is null)" +
            " and (b.planDate>=:#{#query.startDate} or :#{#query.startDate} is null)" +
            " and (b.planDate<=:#{#query.endDate} or :#{#query.endDate} is null)" +
            " and (a.workGroupId=:#{#query.workGroupId} or :#{#query.workGroupId} is null)" +
            " and (a.lineId=:#{#query.workLineId} or :#{#query.workLineId} is null)" +
            " and (a.order.orderNo=:#{#query.orderNo} or :#{#query.orderNo} is null)")
    List<ApsOrderPlanDetail> queryPlan(@Param("query") ApsDayPlanReportDto query);
}
