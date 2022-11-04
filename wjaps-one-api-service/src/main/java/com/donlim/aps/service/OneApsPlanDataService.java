package com.donlim.aps.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.connector.OneApsConnector;
import com.donlim.aps.dao.OneApsPlanDataDao;
import com.donlim.aps.entity.OneApsPlanData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


/**
 * (OneApsPlanData)业务逻辑实现类
 *
 * @author sei
 * @since 2022-07-27 17:42:04
 */
@Service
public class OneApsPlanDataService extends BaseEntityService<OneApsPlanData> {
    @Autowired
    private OneApsPlanDataDao dao;

    @Override
    protected BaseEntityDao<OneApsPlanData> getDao() {
        return dao;
    }


    /**
     * 更新一级APS生产计划
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateOneApsData(LocalDate localDate){
        //先获取
        dao.deleteByDateEquals(localDate);
        List<OneApsPlanData> list = null;
        try {
            list = OneApsConnector.getOneApsData(localDate);
            dao.save(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
