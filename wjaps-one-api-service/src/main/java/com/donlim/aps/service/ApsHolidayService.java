package com.donlim.aps.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.dao.ApsHolidayDao;
import com.donlim.aps.entity.ApsHoliday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 放假表(ApsHoliday)业务逻辑实现类
 *
 * @author sei
 * @since 2022-05-03 08:29:55
 */
@Service
public class ApsHolidayService extends BaseEntityService<ApsHoliday> {
    @Autowired
    private ApsHolidayDao dao;

    @Override
    protected BaseEntityDao<ApsHoliday> getDao() {
        return dao;
    }

}
