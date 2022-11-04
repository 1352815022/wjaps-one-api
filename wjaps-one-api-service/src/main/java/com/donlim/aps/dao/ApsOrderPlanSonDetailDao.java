package com.donlim.aps.dao;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.donlim.aps.entity.ApsOrderPlanSonDetail;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

/**
 * 生产计划明细表(子件)(ApsOrderPlanSonDetail)数据库访问类
 *
 * @author sei
 * @since 2022-05-28 10:20:29
 */
@Repository
public interface ApsOrderPlanSonDetailDao extends BaseEntityDao<ApsOrderPlanSonDetail> {


    /**
     * 根据子件生产计划id 和 排产日期 查找子件生产计划明细
     * @param planId 子件生产计划id
     * @param planDate 排产日期
     * @return
     */
    ApsOrderPlanSonDetail findTopByPlanIdAndPlanDate(String planId, LocalDate planDate);

}
