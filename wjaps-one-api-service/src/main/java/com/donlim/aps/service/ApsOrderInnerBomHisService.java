package com.donlim.aps.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.dao.ApsOrderInnerBomHisDao;
import com.donlim.aps.entity.ApsOrderInnerBomHis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 订单表内部分解合并历史表(ApsOrderInnerBomHis)业务逻辑实现类
 *
 * @author sei
 * @since 2022-05-13 14:47:00
 */
@Service
public class ApsOrderInnerBomHisService extends BaseEntityService<ApsOrderInnerBomHis> {
    @Autowired
    private ApsOrderInnerBomHisDao dao;

    @Override
    protected BaseEntityDao<ApsOrderInnerBomHis> getDao() {
        return dao;
    }

}
