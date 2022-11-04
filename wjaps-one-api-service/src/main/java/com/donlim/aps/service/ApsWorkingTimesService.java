package com.donlim.aps.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.changhong.sei.core.service.bo.OperateResultWithData;
import com.donlim.aps.dao.ApsWorkingTimesDao;
import com.donlim.aps.entity.ApsWorkingTimes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


/**
 * 上班日历配置表(ApsWorkingTimes)业务逻辑实现类
 *
 * @author sei
 * @since 2022-05-04 10:15:30
 */
@Service
public class ApsWorkingTimesService extends BaseEntityService<ApsWorkingTimes> {
    @Autowired
    private ApsWorkingTimesDao dao;

    @Override
    protected BaseEntityDao<ApsWorkingTimes> getDao() {
        return dao;
    }

    @Override
    protected OperateResultWithData<ApsWorkingTimes> preInsert(ApsWorkingTimes entity) {
        entity.setTotalHour(entity.getWorkHour()
                .add(entity.getOverTimeHour())
                .multiply(new BigDecimal(entity.getNumOfPeople()))
        );
        return super.preInsert(entity);
    }

    @Override
    protected OperateResultWithData<ApsWorkingTimes> preUpdate(ApsWorkingTimes entity) {
        entity.setTotalHour(entity.getWorkHour()
                .add(entity.getOverTimeHour())
                .multiply(new BigDecimal(entity.getNumOfPeople()))
        );
        return super.preUpdate(entity);
    }
}
