package com.donlim.aps.dao;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.donlim.aps.entity.U9MoPickList;
import com.donlim.aps.entity.cust.U9ProduceOrderAndMoPick;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (U9MoPickList)数据库访问类
 *
 * @author sei
 * @since 2022-06-14 14:26:35
 */
@Repository
public interface U9MoPickListDao extends BaseEntityDao<U9MoPickList> {

    /**
     * 根据工单号查找备料单
     * @param docNo  工单号
     * @param materialId 工单料品Id
     * @return
     */
    @Query("select new com.donlim.aps.entity.cust.U9ProduceOrderAndMoPick(o,mpl) from U9ProduceOrder o inner join U9MoPickList mpl on o.id = mpl.moId where o.docNo = :docNo and o.materialId = :materialId  ")
    List<U9ProduceOrderAndMoPick> findByDocNoAndMaterialId(@Param("docNo") String docNo, @Param("materialId") Long materialId);
}
