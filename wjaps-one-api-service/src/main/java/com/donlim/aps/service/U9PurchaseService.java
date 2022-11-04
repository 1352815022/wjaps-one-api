package com.donlim.aps.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.dao.U9PurchaseDao;
import com.donlim.aps.entity.U9Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 采购/委外(U9Purchase)业务逻辑实现类
 *
 * @author sei
 * @since 2022-07-14 16:49:44
 */
@Service
public class U9PurchaseService extends BaseEntityService<U9Purchase> {
    @Autowired
    private U9PurchaseDao dao;

    @Override
    protected BaseEntityDao<U9Purchase> getDao() {
        return dao;
    }

    /**
     * 获取采购订单
     * @param orderNo 需求分类号
     * @param materialCode 料号
     * @return
     */
    public List<U9Purchase> findAllByDemandCodeAndMaterialCode(String orderNo, String materialCode){
        return dao.findAllByDemandCodeAndMaterialCode(orderNo,materialCode);
    }

}
