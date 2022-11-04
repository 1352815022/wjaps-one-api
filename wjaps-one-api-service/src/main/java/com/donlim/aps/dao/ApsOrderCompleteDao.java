package com.donlim.aps.dao;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.donlim.aps.entity.ApsOrderComplete;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单齐套(ApsOrderComplete)数据库访问类
 *
 * @author sei
 * @since 2022-07-13 13:59:12
 */
@Repository
public interface ApsOrderCompleteDao extends BaseEntityDao<ApsOrderComplete> {
    ApsOrderComplete findTopByOrderNo(String orderNo);
    List<ApsOrderComplete> findByOrderNoIn(List<String> orderNo);
}
