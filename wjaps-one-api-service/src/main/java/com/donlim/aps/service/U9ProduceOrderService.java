package com.donlim.aps.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.dao.U9BomDao;
import com.donlim.aps.dao.U9ProduceOrderDao;
import com.donlim.aps.dto.DateRange;
import com.donlim.aps.dto.StampingOrderDto;
import com.donlim.aps.entity.U9Bom;
import com.donlim.aps.entity.U9ProduceOrder;
import com.donlim.aps.entity.cust.U9OrderCust;
import com.donlim.aps.vo.OrderSplit;
import com.donlim.aps.vo.SplitLevel;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;


/**
 * 生产单表(U9)(U9ProduceOrder)业务逻辑实现类
 *
 * @author sei
 * @since 2022-05-18 11:14:06
 */
@Service
public class U9ProduceOrderService extends BaseEntityService<U9ProduceOrder> {
    @Autowired
    private U9ProduceOrderDao dao;
    @Autowired
    private U9BomDao bomDao;
    @Autowired
    private U9StockService stockService;

    @Override
    protected BaseEntityDao<U9ProduceOrder> getDao() {
        return dao;
    }

    /**
     * 根据需求分类号反回工单
     * @param orderNo 需求分类号
     * @return
     */
    List<U9ProduceOrder>findAllByDocNoStartsWith(String orderNo){
        return dao.findAllByDocNoStartsWith(orderNo);
    }

  public   List<U9ProduceOrder>findAllBySoIdAndMaterialCode(String orderNo, String materialCode){
        return dao.findAllBySoIdAndMaterialCode(orderNo,materialCode);
    }
    /**
     * 冲压车间订单拆分合并
     * split 拆分
     * merge 合并
     * searchStock 添加结果集库存信息
     * 根据拆分层级排序，按层级降序
     * @param range 时间范围，根据SCM交期
     * @return
     */
    public List<StampingOrderDto> stampingOrder(DateRange range){
        List<U9OrderCust> orderList = dao.queryStampingOrder(range.getEffectiveFrom(),range.getEffectiveTo());

        Map<String, List<OrderSplit>> splitMap = split(orderList);

        List<StampingOrderDto> dtos = merge(splitMap);

        searchStock(dtos);

        return dtos;
    }

    /**
     * 拆分组件物料，非组件直接写入集合
     * @param orderList 冲压车间订单列表
     * @return Map<String, List<OrderSplit>> 冲压生产物料集合（含拆分）
     */
    private Map<String, List<OrderSplit>> split(List<U9OrderCust> orderList) {
        Map<String,List<OrderSplit>> result = new HashMap<>(1);
        LinkedList<SplitLevel> searchQueue = new LinkedList<>();
        LinkedList<BigDecimal> usageQueue = new LinkedList<>();
        for (U9OrderCust order:orderList){
            if (StringUtils.contains(order.getU9Material().getName(),"组件")){
                searchQueue.add(new SplitLevel(Long.valueOf(order.getU9Material().getId()),2));
                while(!searchQueue.isEmpty()){
                    SplitLevel sl = searchQueue.remove();
                    List<U9Bom> bomList = bomDao.findByMasterId(sl.getMaterialId()+"");
                    for (U9Bom bom : bomList){
                        if (StringUtils.contains(bom.getMaterial().getName(),"组件")){
                            searchQueue.add(new SplitLevel(Long.valueOf(bom.getMaterial().getId()),sl.getLevel()+1));
                            usageQueue.add(bom.getQty());
                        }
                        // 拆分物料
                        OrderSplit child = createSplit(order, bom, sl.getLevel());
                        add(child,result);
                    }
                }
            }
            // 最外层物料
            OrderSplit parent = createSplit(order, null, 1);
            add(parent,result);
        }

        return result;

    }

    /**
     * 合并汇总拆分物料
     * @param splitMap 冲压生产物料集合
     * @return List<StampingOrderDto> 合并后数据
     *
     */
    private List<StampingOrderDto> merge(Map<String, List<OrderSplit>> splitMap) {
        List<StampingOrderDto> orderList = new ArrayList<>();

        for (Map.Entry<String,List<OrderSplit>> entry : splitMap.entrySet()){
            StampingOrderDto dto = new StampingOrderDto();
            List<OrderSplit> splits = entry.getValue();
            OrderSplit split = splits.get(0);
            dto.setMaterialId(split.getMaterialId());
            dto.setMaterialCode(split.getMaterialCode());
            dto.setMaterialName(split.getMaterialName());
            dto.setMaterialSpec(split.getMaterialSpec());

            dto.setScrap(split.getScrap());
            BigDecimal qty = new BigDecimal(0);
            BigDecimal pqty = new BigDecimal(0);
            BigDecimal completeQty = new BigDecimal(0) ;
            Integer level = 1;
            LocalDate scmDeliveryDate = LocalDate.of(1,1,1);

            for (OrderSplit item :splits){
                qty = qty.add(item.getBomUsage().multiply( item.getOrderQty()));
                pqty = pqty.add(item.getOrderQty().multiply(new BigDecimal(1).add(item.getScrap())));
                completeQty = completeQty.add(item.getCompleteQty());
                level = level.compareTo(item.getLevel()) >0? level:item.getLevel();
                scmDeliveryDate = item.getScmDelivery().compareTo(scmDeliveryDate) > 0 ? item.getScmDelivery():scmDeliveryDate;
            }
            dto.setOrderQty(qty.setScale(0,BigDecimal.ROUND_UP));
            dto.setProduceQty(pqty.setScale(0,BigDecimal.ROUND_UP));
            dto.setProduceOweQty(dto.getProduceQty().subtract(completeQty));
            dto.setScmDelivery(scmDeliveryDate);
            dto.setLevel(level);
            orderList.add(dto);
        }
        Collections.sort(orderList);
        return orderList;
    }

    private void add(OrderSplit split,Map<String,List<OrderSplit>> map){
        if (map.containsKey(split.getMaterialCode())){
            map.get(split.getMaterialCode()).add(split);
        }else{
            List<OrderSplit> list = new ArrayList<>();
            list.add(split);
            map.put(split.getMaterialCode(),list);
        }
    }

    private OrderSplit createSplit(U9OrderCust order, U9Bom bom, Integer level){
        OrderSplit split = new OrderSplit();
        split.setOrderId(order.getU9ProduceOrder().getId());
        split.setLevel(level);
        split.setMaterialId(bom != null ?Long.valueOf(bom.getMaterial().getId()) : Long.valueOf(order.getU9Material().getId()));
        split.setMaterialName(bom != null ? bom.getMaterial().getName() : order.getU9Material().getName());
        split.setMaterialCode(bom != null ? bom.getMaterial().getCode() : order.getU9Material().getCode());
        split.setMaterialSpec(bom != null ? bom.getMaterial().getSpec() : order.getU9Material().getSpec());
        split.setOrderQty(order.getU9ProduceOrder().getQty());
        split.setBomUsage(bom != null ? bom.getQty() : new BigDecimal(1));
        split.setScrap(order.getU9Material().getScrap() != null ? order.getU9Material().getScrap() : new BigDecimal(0));
        split.setScmDelivery(order.getScmXbDelivery().getDeliveryStartDate());
        split.setCompleteQty(order.getU9ProduceOrder().getTotalCompleteQty());
        return split;
    }

    /**
     * 添加库存信息
     * @param dtos
     */
    private void searchStock(List<StampingOrderDto> dtos) {
        List<Long> searchList = new ArrayList<>();
        Map<Long,BigDecimal> resultMap = new HashMap<>();
        for (int i = 0; i < dtos.size(); i++) {
            searchList.add(dtos.get(i).getMaterialId());
            if (searchList.size() % 100 == 0 || i == dtos.size()-1){
                try {
                    Map<Long, BigDecimal> map = stockService.sumStockGroupByMaterialId(searchList);
                    resultMap.putAll(map);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                searchList.clear();
            }
        }
        for (StampingOrderDto dto : dtos) {
            dto.setStockQty(resultMap.getOrDefault(dto.getMaterialId(),BigDecimal.ZERO));
        }

    }

    /**
     * 根据单号查生产单信息
     * @param orderNo 单号
     * @return
     */
    public U9ProduceOrder getListByOrderNo(String orderNo){
        return  dao.findAllByDocNo(orderNo);
    }

}
