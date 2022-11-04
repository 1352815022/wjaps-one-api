package com.donlim.aps.dao;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.donlim.aps.entity.ApsMaterialCapacity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * (ApsMaterialCapacity)数据库访问类
 *
 * @author sei
 * @since 2022-05-09 11:17:11
 */
@Repository
public interface ApsMaterialCapacityDao extends BaseEntityDao<ApsMaterialCapacity> {

    /**
     * 根据物料id and 车间id and 班组id 查询物料标准用量
     * @return
     */
    Optional<ApsMaterialCapacity> findTopByMaterialIdAndWorkGroupIdAndWorkLineIdAndFrozenIsFalse(Long materialId,String workGroupId,String workLineId);

}
