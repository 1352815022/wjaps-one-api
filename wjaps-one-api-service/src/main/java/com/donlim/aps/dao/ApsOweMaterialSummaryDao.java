package com.donlim.aps.dao;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.donlim.aps.entity.ApsOweMaterialSummary;
import org.springframework.stereotype.Repository;

/**
 * 欠料汇总表(ApsOweMaterialSummary)数据库访问类
 *
 * @author sei
 * @since 2022-06-14 11:52:45
 */
@Repository
public interface ApsOweMaterialSummaryDao extends BaseEntityDao<ApsOweMaterialSummary> {

    void deleteByTenantCode(String tenantCode);
}
