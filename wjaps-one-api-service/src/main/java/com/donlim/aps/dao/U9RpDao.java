package com.donlim.aps.dao;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.donlim.aps.entity.U9Rp;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 请购单(U9Rp)数据库访问类
 *
 * @author sei
 * @since 2022-06-14 14:26:35
 */
@Repository
public interface U9RpDao extends BaseEntityDao<U9Rp> {

    /**
     * 根据需求分类号与料id查找请购单
     * @param demandCode 需求分类号 U9ProduceOrder.soId
     * @param materialId 料id
     * @return
     */
    List<U9Rp> findByDemandCodeAndMaterialId(String demandCode,Long materialId);
}
