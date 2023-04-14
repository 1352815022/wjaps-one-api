package com.donlim.aps.dao;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.donlim.aps.entity.ApsPurchasePlan;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * 采购计划表(ApsPurchasePlan)数据库访问类
 *
 * @author sei
 * @since 2022-05-20 09:16:30
 */
@Repository
public interface ApsPurchasePlanDao extends BaseEntityDao<ApsPurchasePlan> {

    int deleteByOrderIdIn(List<String>Ids);
    List<ApsPurchasePlan>findByOrderIdIn(List<String>Ids);
    int deleteByStartDateAfter(LocalDate date);
    List<ApsPurchasePlan>findByStartDateAfterAndSoNoIsNotNull(LocalDate date);

    /**
     * 根据订单号删除计划
     * @param orderNos
     * @return
     */
    int deleteByOrderNoIn(List<String>orderNos);

    List<ApsPurchasePlan>findByOrderNoIn(List<String>orderNos);


    List<ApsPurchasePlan>findByRemarkIn(List<String>purchaseIds);

}
