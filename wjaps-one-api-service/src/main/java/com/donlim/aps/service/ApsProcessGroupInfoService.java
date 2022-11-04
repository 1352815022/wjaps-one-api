package com.donlim.aps.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.dao.ApsProcessGroupInfoDao;
import com.donlim.aps.entity.ApsProcessGroupInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 工艺工序关连表(ApsProcessGroupInfo)业务逻辑实现类
 *
 * @author sei
 * @since 2022-04-20 16:11:43
 */
@Service
public class ApsProcessGroupInfoService extends BaseEntityService<ApsProcessGroupInfo> {
    @Autowired
    private ApsProcessGroupInfoDao dao;

    @Override
    protected BaseEntityDao<ApsProcessGroupInfo> getDao() {
        return dao;
    }

}
