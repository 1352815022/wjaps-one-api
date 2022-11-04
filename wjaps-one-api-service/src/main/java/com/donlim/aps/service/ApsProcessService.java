package com.donlim.aps.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.dao.ApsProcessDao;
import com.donlim.aps.entity.ApsProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 工序表(ApsProcess)业务逻辑实现类
 *
 * @author sei
 * @since 2022-04-19 14:13:39
 */
@Service
public class ApsProcessService extends BaseEntityService<ApsProcess> {
    @Autowired
    private ApsProcessDao dao;

    @Override
    protected BaseEntityDao<ApsProcess> getDao() {
        return dao;
    }

}
