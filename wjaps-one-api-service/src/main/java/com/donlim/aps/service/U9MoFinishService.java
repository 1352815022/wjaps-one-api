package com.donlim.aps.service;

import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSON;
import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.log.LogUtil;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.dao.U9MoFinishDao;
import com.donlim.aps.entity.ApsOrder;
import com.donlim.aps.entity.ApsOrderExt;
import com.donlim.aps.entity.U9MoFinish;
import com.donlim.aps.entity.U9MoPickList;
import com.donlim.aps.entity.cust.OrderAndScmV2;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * U9数据扩展字段(U9MoFinish)业务逻辑实现类
 *
 * @author sei
 * @since 2023-04-08 11:27:30
 */
@Service
public class U9MoFinishService  extends BaseEntityService<U9MoFinish>  {
    @Autowired
    private U9MoFinishDao u9MoFinishDao;
    @Autowired
    private ApsOrderExtService apsOrderExtService;


    @Override
    protected BaseEntityDao<U9MoFinish> getDao() {
        return u9MoFinishDao;
    }

    private final Integer partitionSize = 100000;
    private final Integer partitionCountLimit = 500;
    /**
     * 根据订单与日期维度U9完工数
     */
    public void countU9FinishQtyHandler() {
        List<ApsOrderExt> apsOrderExts = countU9FinishQtyQuery();
        //数据量大于5000做分片处理，避免大事务
        if (apsOrderExts.size() > partitionCountLimit) {
            List<List<ApsOrderExt>> partition = Lists.partition(apsOrderExts, partitionSize);
            for (List<ApsOrderExt> orderExts : partition) {
                apsOrderExtService.save(orderExts);
            }
        }else{
            apsOrderExtService.save(apsOrderExts);
        }

    }


    /**
     * 获取需要更新内排订单
     * @return
     */
    public List<ApsOrderExt> countU9FinishQtyQuery() {
        List<Map<String,Object>>  u9FinishTotal = u9MoFinishDao.countU9FinishQtyQuery();
        List<ApsOrderExt> u9FinishTotalDtos = new ArrayList<ApsOrderExt>(u9FinishTotal.size());
        //这里会有内存溢出、处理方法做分片
        List<List<Map<String, Object>>> partitionLists = Lists.partition(u9FinishTotal, partitionSize);
        if (partitionLists.size() > 0) {
            for (List<Map<String, Object>> partitionList : partitionLists) {
                partitionList = partitionList.stream().map(MapUtil::toCamelCaseMap).collect(Collectors.toList());
                String irsStr = JSON.toJSONString(partitionList);
                List<ApsOrderExt> partitionDtos = JSON.parseArray(irsStr, ApsOrderExt.class);
                u9FinishTotalDtos.addAll(partitionDtos);
            }
        }
        return u9FinishTotalDtos;
    }

}