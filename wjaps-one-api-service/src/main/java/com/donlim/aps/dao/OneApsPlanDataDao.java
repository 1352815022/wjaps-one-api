package com.donlim.aps.dao;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.donlim.aps.entity.OneApsPlanData;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

/**
 * (OneApsPlanData)数据库访问类
 *
 * @author sei
 * @since 2022-07-27 17:42:04
 */
@Repository
public interface OneApsPlanDataDao extends BaseEntityDao<OneApsPlanData> {
    int deleteByDateEquals(LocalDate ld);

}
