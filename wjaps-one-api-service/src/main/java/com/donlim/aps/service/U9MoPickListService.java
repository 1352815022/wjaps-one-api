package com.donlim.aps.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.dao.U9MoPickListDao;
import com.donlim.aps.entity.U9MoPickList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * (U9MoPickList)业务逻辑实现类
 *
 * @author sei
 * @since 2022-06-14 14:26:36
 */
@Service
public class U9MoPickListService extends BaseEntityService<U9MoPickList> {
    @Autowired
    private U9MoPickListDao dao;

    @Override
    protected BaseEntityDao<U9MoPickList> getDao() {
        return dao;
    }

}
