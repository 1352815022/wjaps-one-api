package com.donlim.aps.dao;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.donlim.aps.entity.ApsOrderPlanSon;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 生产计划表(子件)(ApsOrderPlanSon)数据库访问类
 *
 * @author sei
 * @since 2022-05-28 10:19:20
 */
@Repository
public interface ApsOrderPlanSonDao extends BaseEntityDao<ApsOrderPlanSon> {

    /**
     * 根据内排单id查询
     * @param orderId
     * @param materialId
     * @return
     */
    List<ApsOrderPlanSon> findByOrderIdAndMaterialId(String orderId,Long materialId);

    List<ApsOrderPlanSon> findByPlanId(String planId);
}
