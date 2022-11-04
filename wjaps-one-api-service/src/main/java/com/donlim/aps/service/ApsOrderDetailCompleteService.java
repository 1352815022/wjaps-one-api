package com.donlim.aps.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.dao.ApsOrderDetailCompleteDao;
import com.donlim.aps.entity.ApsOrderDetailComplete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 订单齐套(ApsOrderDetailComplete)业务逻辑实现类
 *
 * @author sei
 * @since 2022-07-13 14:09:47
 */
@Service
public class ApsOrderDetailCompleteService extends BaseEntityService<ApsOrderDetailComplete> {
    @Autowired
    private ApsOrderDetailCompleteDao dao;

    @Override
    protected BaseEntityDao<ApsOrderDetailComplete> getDao() {
        return dao;
    }

   public int deleteByParentId(String id){
        return dao.deleteByParentId(id);
    }

}
