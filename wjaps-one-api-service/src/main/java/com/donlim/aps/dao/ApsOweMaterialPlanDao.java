package com.donlim.aps.dao;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.donlim.aps.entity.ApsOweMaterialPlan;
import org.springframework.stereotype.Repository;

/**
 * 欠料计划表(ApsOweMaterialPlan)数据库访问类
 *
 * @author sei
 * @since 2022-06-14 11:53:37
 */
@Repository
public interface ApsOweMaterialPlanDao extends BaseEntityDao<ApsOweMaterialPlan> {


    void deleteByTenantCode(String tenantCode);
}
