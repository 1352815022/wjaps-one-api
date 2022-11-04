package com.donlim.aps.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.dao.U9ReceiveDao;
import com.donlim.aps.entity.U9Receive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * (U9Receive)业务逻辑实现类
 *
 * @author sei
 * @since 2022-06-14 14:26:36
 */
@Service
public class U9ReceiveService extends BaseEntityService<U9Receive> {
    @Autowired
    private U9ReceiveDao dao;

    @Override
    protected BaseEntityDao<U9Receive> getDao() {
        return dao;
    }

}
