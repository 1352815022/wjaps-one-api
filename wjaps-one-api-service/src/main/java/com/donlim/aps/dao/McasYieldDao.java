package com.donlim.aps.dao;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.donlim.aps.entity.McasYield;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * (McasYield)数据库访问类
 *
 * @author sei
 * @since 2022-06-14 10:26:06
 */
@Repository
public interface McasYieldDao extends BaseEntityDao<McasYield> {

    List<McasYield>findMcasYieldsByDate(LocalDate planDate);

    /**
     * 清冼线产量（没有工序）
     * @param planDate
     * @param process
     * @return
     */
    List<McasYield>findMcasYieldsByDateAndProcess(LocalDate planDate, String process);

    int deleteByDateEquals(LocalDate date);
}
