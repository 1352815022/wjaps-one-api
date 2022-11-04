package com.donlim.aps.util;

import com.donlim.aps.dao.U9BomDao;
import com.donlim.aps.dao.U9MaterialDao;
import com.donlim.aps.dto.CalcBomDto;
import com.donlim.aps.entity.U9Bom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:BOM帮助类
 * @Author: chenzhiquan
 * @Date: 2022/10/24.
 */
@Component
public class BomUtil {
    @Autowired
    private U9BomDao u9BomDao;
    @Autowired
    private U9MaterialDao u9MaterialDao;
    private static BomUtil bomUtil;

    @PostConstruct
    public void init() {
        bomUtil = this;
    }

    /**
     * 根据料号获取所有子料id
     * @param materialId
     * @return
     */
    public static Map<String,List<String>> getChildren(String materialId,Map<String,List<String>>materialIdMapList) {
        List<U9Bom> childrenList = bomUtil.u9BomDao.findByMasterId(materialId);
        for (U9Bom children : childrenList) {
           if(materialIdMapList==null){
                materialIdMapList=new HashMap<>();
                List<String>parentIds=new ArrayList<>();
                parentIds.add(materialId);
                materialIdMapList.put(children.getMaterialId()+"", parentIds);
            }else{
                List<String> list =new ArrayList<>();
                if(materialIdMapList.get(materialId)!=null){
                    list.addAll(materialIdMapList.get(materialId));
                }
                list.add(materialId);
                materialIdMapList.put(children.getMaterialId()+"",list);
            }
            getChildren(children.getMaterialId()+"",materialIdMapList);
        }
       return  materialIdMapList;
    }

    /**
     * 计算母子料的用量比例以及前置期
     * @param materialId
     * @param childrenId
     */
    public static void calcBom(String materialId, String childrenId, CalcBomDto calcBomDto){
      //  u9MaterialDao.findById(materialId).get().getFixedLt()
   //     List<U9Bom> childrenList = bomUtil.u9BomDao.findByMasterId(materialId);

    }
}
