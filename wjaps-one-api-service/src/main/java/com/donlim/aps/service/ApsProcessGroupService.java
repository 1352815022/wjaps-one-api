package com.donlim.aps.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.dao.ApsProcessGroupDao;
import com.donlim.aps.entity.ApsProcessGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 工艺表(ApsProcessGroup)业务逻辑实现类
 *
 * @author sei
 * @since 2022-04-20 16:11:38
 */
@Service
public class ApsProcessGroupService extends BaseEntityService<ApsProcessGroup> {
    @Autowired
    private ApsProcessGroupDao dao;

    @Override
    protected BaseEntityDao<ApsProcessGroup> getDao() {
        return dao;
    }

}
