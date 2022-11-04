package com.donlim.aps.dao;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.donlim.aps.entity.McasProcess;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * (McasProcess)数据库访问类
 *
 * @author sei
 * @since 2022-06-15 14:04:12
 */
@Repository
public interface McasProcessDao extends BaseEntityDao<McasProcess> {
    /**
     *获取产品工序
     * @param itemList 产品型号集合
     * @return
     */
    List<McasProcess>findMcasProcessesByItemNameIn(List<String>itemList);

    Optional<McasProcess> findTopByItemNameAndType(String itemName, String type );
}
