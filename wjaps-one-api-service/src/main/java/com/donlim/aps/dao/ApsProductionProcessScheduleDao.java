package com.donlim.aps.dao;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.donlim.aps.entity.ApsProductionProcessSchedule;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * 生产工序报工表
 * (ApsProductionProcessSchedule)数据库访问类
 *
 * @author sei
 * @since 2022-06-20 11:34:57
 */
@Repository
public interface ApsProductionProcessScheduleDao extends BaseEntityDao<ApsProductionProcessSchedule> {

    /**
     * 根据生日日期查询报工
     * @return 报工情况
     */
    List<ApsProductionProcessSchedule> findAllByProductionDateEquals(LocalDate date);

    List<ApsProductionProcessSchedule> findAllByProductOrder(String productOrder);


    int deleteByProductionDateEquals(LocalDate date);
}
