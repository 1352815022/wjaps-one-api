package com.donlim.aps.dao;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.donlim.aps.entity.U9Receive;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (U9Receive)数据库访问类
 *
 * @author sei
 * @since 2022-06-14 14:26:36
 */
@Repository
public interface U9ReceiveDao extends BaseEntityDao<U9Receive> {


    /**
     * 根据需求分类号与料品id获取暂收记录
     * @param demandCode 需求分类号 U9ProduceOrder.soId
     * @param materialId 料品Id
     * @param status 属性
     * @return
     */
    List<U9Receive> findByDemandCodeAndMaterialIdAndStatus(String demandCode, Long materialId, String status);

}
