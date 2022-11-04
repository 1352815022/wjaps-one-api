package com.donlim.aps.dao;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.donlim.aps.entity.ApsOrderDetailComplete;
import org.springframework.stereotype.Repository;

/**
 * 订单齐套(ApsOrderDetailComplete)数据库访问类
 *
 * @author sei
 * @since 2022-07-13 14:09:47
 */
@Repository
public interface ApsOrderDetailCompleteDao extends BaseEntityDao<ApsOrderDetailComplete> {

    int deleteByParentId(String id);
}
