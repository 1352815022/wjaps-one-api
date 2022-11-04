package com.donlim.aps.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.dao.ApsMaterialCapacityDao;
import com.donlim.aps.entity.ApsMaterialCapacity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * (ApsMaterialCapacity)业务逻辑实现类
 *
 * @author sei
 * @since 2022-05-09 11:17:11
 */
@Service
public class ApsMaterialCapacityService extends BaseEntityService<ApsMaterialCapacity> {
    @Autowired
    private ApsMaterialCapacityDao dao;

    @Override
    protected BaseEntityDao<ApsMaterialCapacity> getDao() {
        return dao;
    }

}
