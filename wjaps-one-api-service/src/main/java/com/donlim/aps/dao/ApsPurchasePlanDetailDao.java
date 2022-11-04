package com.donlim.aps.dao;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.donlim.aps.entity.ApsPurchasePlanDetail;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * 采购计划明细表(ApsPurchasePlanDetail)数据库访问类
 *
 * @author sei
 * @since 2022-05-23 08:20:14
 */
@Repository
public interface ApsPurchasePlanDetailDao extends BaseEntityDao<ApsPurchasePlanDetail> {

    /**
     * 根据委外计划id 和 计划日期 查询
     * @param planId 委外计划id
     * @param planDate 计划日期
     * @return
     */
    ApsPurchasePlanDetail findTopByPlanIdAndPlanDate(String planId, LocalDate planDate);

    int deleteByPlanIdIn(List<String> Ids);
}
