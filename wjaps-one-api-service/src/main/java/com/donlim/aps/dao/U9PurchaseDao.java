package com.donlim.aps.dao;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.donlim.aps.entity.U9Purchase;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * 采购/委外(U9Purchase)数据库访问类
 *
 * @author sei
 * @since 2022-07-14 16:49:44
 */
@Repository
public interface U9PurchaseDao extends BaseEntityDao<U9Purchase> {

    List<U9Purchase> findAllByDemandCodeAndMaterialCode(String orderNo, String materialCode);

    List<U9Purchase> findAllByDemandCode(String demandCode);

    List<U9Purchase>findByDemandCodeIsNullAndDeliveryDateAfter(LocalDate date);

}
