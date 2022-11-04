package com.donlim.aps.service;

import com.donlim.aps.dao.U9BomDao;
import com.donlim.aps.entity.U9Bom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 料品表bom(U9Bom)业务逻辑实现类
 *
 * @author sei
 * @since 2022-05-12 14:58:02
 */
@Service
public class U9BomService {
    @Autowired
    private U9BomDao dao;


    /**
     * 获取子件集合
     * @param masterId 物料id
     * @return 子件id集合
     */
    public List<U9Bom> getMinorComponents(Long masterId){

        List<U9Bom> subComponent = dao.findByMasterId("");
        List<Long> queryIds = subComponent.stream().map(b -> Long.valueOf(b.getMaterial().getId())).collect(Collectors.toList());
        List<U9Bom> allBom = new ArrayList<>(subComponent);
        while(queryIds.size()>0){
            List<U9Bom> u9Boms = dao.getComponentByMasterIds(queryIds);
            queryIds = u9Boms.stream().map(b -> Long.valueOf(b.getMaterial().getId())).collect(Collectors.toList());
            allBom.addAll(u9Boms);
        }

        return allBom;
    }

}
