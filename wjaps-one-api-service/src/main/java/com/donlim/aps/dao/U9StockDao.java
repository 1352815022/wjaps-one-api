package com.donlim.aps.dao;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.donlim.aps.entity.U9Stock;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (U9Stock)数据库访问类
 *
 * @author sei
 * @since 2022-05-09 16:20:09
 */
@Repository
public interface U9StockDao extends BaseEntityDao<U9Stock> {

    /**
     * 根据物料id返回库存
     * @param materialsIds
     * @return
     */
    List<U9Stock> findByMaterialIdIn(List<Long> materialsIds);

    /**
     * 根据料品id查找库存
     * @param materialId
     * @return
     */
    List<U9Stock> findByMaterialId(Long materialId);
}
