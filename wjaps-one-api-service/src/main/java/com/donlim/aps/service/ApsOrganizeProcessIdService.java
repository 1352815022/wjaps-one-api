package com.donlim.aps.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.dao.ApsOrganizeProcessIdDao;
import com.donlim.aps.entity.ApsOrganizeProcessId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 组织工艺配置(ApsOrganizeProcessId)业务逻辑实现类
 *
 * @author sei
 * @since 2022-04-26 09:22:16
 */
@Service
public class ApsOrganizeProcessIdService extends BaseEntityService<ApsOrganizeProcessId> {
    @Autowired
    private ApsOrganizeProcessIdDao dao;

    @Override
    protected BaseEntityDao<ApsOrganizeProcessId> getDao() {
        return dao;
    }

}
