package com.donlim.aps.service;

import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSON;
import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.dto.serach.SearchFilter;
import com.changhong.sei.core.log.LogUtil;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.dao.ApsOrderPlanDao;
import com.donlim.aps.dao.U9MaterialDao;
import com.donlim.aps.dao.U9MoFinishDao;
import com.donlim.aps.dto.U9MoFinishDto;
import com.donlim.aps.entity.*;
import com.donlim.aps.entity.cust.OrderAndScmV2;
import com.donlim.aps.util.DateUtils;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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
    @Autowired
    U9ProduceOrderService u9ProduceOrderService;
    @Autowired
    private ApsOrderPlanDao apsOrderPlanDao;
    @Autowired
    private U9MaterialDao u9MaterialDao;


    @Override
    protected BaseEntityDao<U9MoFinish> getDao() {
        return u9MoFinishDao;
    }

    private final Integer partitionSize = 500;
    private final Integer partitionCountLimit = 1000;
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

    /**
     * 加载工单信息
     * @param search
     * @return
     */
    public PageResult<U9MoFinishDto>getList(Search search){
        PageResult<U9MoFinish> page = findByPage(search);
        ArrayList<U9MoFinish> rows = page.getRows();
        List<U9MoFinishDto>dtoList=new ArrayList<>();
        for (U9MoFinish u9MoFinish : rows) {
            U9MoFinishDto u9MoFinishDto=new U9MoFinishDto();
            BeanUtils.copyProperties(u9MoFinish,u9MoFinishDto);
            U9ProduceOrder listByOrderNo = u9ProduceOrderService.getListByOrderNo(u9MoFinish.getOrderNo());
            u9MoFinishDto.setMaterialCode(listByOrderNo.getMaterialCode());
            u9MoFinishDto.setMaterialName(listByOrderNo.getMaterialName());
            dtoList.add(u9MoFinishDto);
        }
        PageResult<U9MoFinishDto> pageDto=new PageResult<>();
        BeanUtils.copyProperties(page,pageDto);
        pageDto.setRows(dtoList);
        return pageDto;
    }

    /**
     * 没有排计划的完工单
     * @param search
     * @return
     */
    public List<U9MoFinishDto> findNoPlan(Search search){
        LocalDate date = LocalDate.now();
        Optional<SearchFilter> finishDate = search.getFilters().stream().filter(a -> a.getFieldName().equals("finishDate")).findFirst();
        if(finishDate.isPresent()){
            date= DateUtils.date2LocalDate((Date)finishDate.get().getValue());
        }
        List<U9MoFinishDto> dtoList=new ArrayList<>();
        //取出当天计划单
        List<String> planNumByDay = apsOrderPlanDao.findPlanByDate(date,date).stream().map(a -> a.getOrder().getOrderNo()).collect(Collectors.toList());
        //当天完工数
        List<U9MoFinish> finishListByDay = u9MoFinishDao.findByFinishDateBetween(date, date.plusDays(1));
        //先取出不计算的料号
        List<String> noCalcMaterial = u9MaterialDao.findByCalcIsFalse().stream().map(a -> a.getCode()).collect(Collectors.toList());
        //剔除不计算料号
        List<String> finishDayMoList = finishListByDay.stream().map(a -> a.getOrderNo()).filter(b->!noCalcMaterial.contains(b)).collect(Collectors.toList());
        List<String> list = finishDayMoList.stream().filter(a -> !planNumByDay.contains(a)).collect(Collectors.toList());
        for (String mo : list) {
            Optional<U9MoFinish> u9MoFinish = finishListByDay.stream().filter(s -> s.getOrderNo().equals(mo)).findFirst();
            if(u9MoFinish.isPresent()){
                U9MoFinishDto u9MoFinishDto=new U9MoFinishDto();
                BeanUtils.copyProperties(u9MoFinish.get(),u9MoFinishDto);
                U9ProduceOrder listByOrderNo = u9ProduceOrderService.getListByOrderNo(u9MoFinish.get().getOrderNo());
                u9MoFinishDto.setMaterialCode(listByOrderNo.getMaterialCode());
                u9MoFinishDto.setMaterialName(listByOrderNo.getMaterialName());
                dtoList.add(u9MoFinishDto);
            }
        }
        return  dtoList;
    }

}
