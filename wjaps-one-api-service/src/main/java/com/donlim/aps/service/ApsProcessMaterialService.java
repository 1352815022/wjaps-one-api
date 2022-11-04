package com.donlim.aps.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.dao.ApsProcessMaterialDao;
import com.donlim.aps.entity.ApsProcessMaterial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 工序料品配置(ApsProcessMaterial)业务逻辑实现类
 *
 * @author sei
 * @since 2022-06-08 10:53:19
 */
@Service
public class ApsProcessMaterialService extends BaseEntityService<ApsProcessMaterial> {
    @Autowired
    private ApsProcessMaterialDao dao;

    @Override
    protected BaseEntityDao<ApsProcessMaterial> getDao() {
        return dao;
    }

}
