package com.donlim.aps.dao;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.donlim.aps.entity.ApsWorkingTimes;
import org.springframework.stereotype.Repository;

/**
 * 上班日历配置表(ApsWorkingTimes)数据库访问类
 *
 * @author sei
 * @since 2022-05-04 10:15:30
 */
@Repository
public interface ApsWorkingTimesDao extends BaseEntityDao<ApsWorkingTimes> {

}
