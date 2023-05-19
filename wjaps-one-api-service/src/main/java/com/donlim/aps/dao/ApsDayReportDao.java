package com.donlim.aps.dao;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.donlim.aps.entity.ApsDayReport;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

/**
 * (ApsDayReport)数据库访问类
 *
 * @author sei
 * @since 2023-05-19 11:50:12
 */
@Repository
public interface ApsDayReportDao extends BaseEntityDao<ApsDayReport> {

    Optional<ApsDayReport> findByDate(LocalDate date);

}
