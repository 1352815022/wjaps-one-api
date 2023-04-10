package com.donlim.aps.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.dao.ApsOrderDao;
import com.donlim.aps.dao.ApsOrderExtDao;
import com.donlim.aps.entity.ApsOrder;
import com.donlim.aps.entity.ApsOrderExt;
import com.donlim.aps.entity.U9MoFinish;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


/**
 * 订单表(内部)扩展表(ApsOrderExt)业务逻辑实现类
 *
 * @author sei
 * @since 2023-04-06 08:29:18
 */
@Service
public class ApsOrderExtService   extends BaseEntityService<ApsOrderExt> {

    @Autowired
    private ApsOrderExtDao apsOrderExtDao;


    @Override
    protected BaseEntityDao<ApsOrderExt> getDao() {
        return apsOrderExtDao;
    }


    @Transactional
    public void saveU9TotalHandler(List<ApsOrderExt> apsOrderExts) {
        apsOrderExtDao.saveAll(apsOrderExts);
    }
}