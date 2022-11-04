package com.donlim.aps.dao;

import com.changhong.sei.core.dao.BaseTreeDao;
import com.donlim.aps.entity.ApsOrganize;
import org.springframework.stereotype.Repository;

/**
 * 组织机构(ApsOrganize)数据库访问类
 *
 * @author sei
 * @since 2022-04-25 11:27:41
 */
@Repository
public interface ApsOrganizeDao extends BaseTreeDao<ApsOrganize> {
    /**
     * 根据编码查询组织
     * @param code
     * @return
     */
    ApsOrganize findTopByCode(String code);

}
