package com.donlim.aps.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.dao.ApsOweMaterialPlanDao;
import com.donlim.aps.entity.ApsOweMaterialPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 欠料计划表(ApsOweMaterialPlan)业务逻辑实现类
 *
 * @author sei
 * @since 2022-06-14 11:53:37
 */
@Service
public class ApsOweMaterialPlanService extends BaseEntityService<ApsOweMaterialPlan> {
    @Autowired
    private ApsOweMaterialPlanDao dao;

    @Override
    protected BaseEntityDao<ApsOweMaterialPlan> getDao() {
        return dao;
    }

}
