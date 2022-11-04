package com.donlim.aps.dao;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.donlim.aps.entity.ScmXbDeliveryPlan;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * scm送货明细(ScmXbDeliveryPlan)数据库访问类
 *
 * @author sei
 * @since 2022-06-20 11:15:03
 */
@Repository
public interface ScmXbDeliveryPlanDao extends BaseEntityDao<ScmXbDeliveryPlan> {

    int deleteByCreatedDateBefore(Date date);

    List<ScmXbDeliveryPlan> findByParentId(String parentId);
    int deleteByParentIdIn(List<String>parentIds);
}
