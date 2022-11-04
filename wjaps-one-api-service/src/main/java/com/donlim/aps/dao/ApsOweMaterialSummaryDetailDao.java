package com.donlim.aps.dao;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.donlim.aps.entity.ApsOweMaterialSummaryDetail;
import org.springframework.stereotype.Repository;

/**
 * 欠料汇总明细表(ApsOweMaterialSummaryDetail)数据库访问类
 *
 * @author sei
 * @since 2022-06-14 11:53:14
 */
@Repository
public interface ApsOweMaterialSummaryDetailDao extends BaseEntityDao<ApsOweMaterialSummaryDetail> {

    void deleteByTenantCode(String tenantCode);
}
