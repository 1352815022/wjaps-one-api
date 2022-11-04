package com.donlim.aps.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.dao.U9RpDao;
import com.donlim.aps.entity.U9Rp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 请购单(U9Rp)业务逻辑实现类
 *
 * @author sei
 * @since 2022-06-14 14:26:35
 */
@Service
public class U9RpService extends BaseEntityService<U9Rp> {
    @Autowired
    private U9RpDao dao;

    @Override
    protected BaseEntityDao<U9Rp> getDao() {
        return dao;
    }

}
