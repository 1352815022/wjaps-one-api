package com.donlim.aps.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.dao.ApsDayReportDao;
import com.donlim.aps.entity.ApsDayReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * (ApsDayReport)业务逻辑实现类
 *
 * @author sei
 * @since 2023-05-19 11:50:12
 */
@Service
public class ApsDayReportService extends BaseEntityService<ApsDayReport> {
    @Autowired
    private ApsDayReportDao dao;

    @Override
    protected BaseEntityDao<ApsDayReport> getDao() {
        return dao;
    }

}
