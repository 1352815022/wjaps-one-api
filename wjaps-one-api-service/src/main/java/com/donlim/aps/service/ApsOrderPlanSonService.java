package com.donlim.aps.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.dao.ApsMaterialCapacityDao;
import com.donlim.aps.dao.ApsOrderPlanSonDao;
import com.donlim.aps.dao.U9BomDao;
import com.donlim.aps.dao.U9ProduceOrderDao;
import com.donlim.aps.dto.ApsOrderDto;
import com.donlim.aps.dto.ColumnDto;
import com.donlim.aps.entity.ApsOrderPlan;
import com.donlim.aps.entity.ApsOrderPlanSon;
import com.donlim.aps.entity.ApsOrderPlanSonDetail;
import com.donlim.aps.entity.U9Bom;
import com.donlim.aps.entity.cust.OrderAndU9;
import com.donlim.aps.util.ColumnUtils;
import com.donlim.aps.util.DateUtils;
import com.donlim.aps.util.NumberUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 生产计划表(子件)(ApsOrderPlanSon)业务逻辑实现类
 *
 * @author sei
 * @since 2022-05-28 10:19:20
 */
@Service
public class ApsOrderPlanSonService extends BaseEntityService<ApsOrderPlanSon> {
    @Autowired
    private ApsOrderPlanSonDao dao;
    @Autowired
    private U9BomDao bomDao;
    @Autowired
    private U9ProduceOrderDao produceOrderDao;
    @Autowired
    private ApsMaterialCapacityDao materialCapacityDao;
    @Autowired
    private U9BomService u9BomService;

    @Override
    protected BaseEntityDao<ApsOrderPlanSon> getDao() {
        return dao;
    }


    /**
     * excel导出数据
     * @param search
     * @param cols
     * @return
     */
    public List<List<Object>> findByFilter(Search search,Integer cols){
        List<ApsOrderPlanSon> sons = this.findByFilters(search);
        LocalDate now = LocalDate.now();
        LocalDate end = now.plusDays(cols);
        List<List<Object>> result = new ArrayList<>();
        for (ApsOrderPlanSon plan : sons) {
            List<Object> row = new ArrayList<>();
            row.add(plan.getApsOrder().getOrderNo());
            row.add(plan.getU9No());
            row.add(plan.getWorkGroupName());
            row.add(plan.getLineName());
            row.add(plan.getMaterialCode());
            row.add(plan.getMaterialName());
            row.add(plan.getMaterialSpec());
            row.add(plan.getPlanNum());
            row.add(DateUtils.LocalDateToString(plan.getScmDeliveryDate()));
            row.add(plan.getIeStandardQty());
            row.add(plan.getPcStandardQty());
            row.add(plan.getPlanQty());
            row.add(plan.getAwaitQty());
            List<ApsOrderPlanSonDetail> orderPlanSonDetails = plan.getOrderPlanSonDetails();
            for (ApsOrderPlanSonDetail detail : orderPlanSonDetails) {
                LocalDate planDate = detail.getPlanDate();
                if (planDate.compareTo(now) >= 0 && planDate.compareTo(end)<= 0){
                    row.add(detail.getPlanQty());
                }
            }
            result.add(row);
        }
        return result;
    }

    /**
     * excel导出表格标题
     * @param cols
     * @return
     */
    public List<List<String>> exportHeader(Integer cols){
        List<ColumnDto> dynamicCol = ColumnUtils.getColumnsByDate(cols, LocalDate.now(),false);
        List<List<String>> header = new ArrayList<>();
        header.add(addHead("内排单号"));
        header.add(addHead("子件单号"));
        header.add(addHead("车间"));
        header.add(addHead("生产线"));
        header.add(addHead("料号"));
        header.add(addHead("料名"));
        header.add(addHead("规格"));
        header.add(addHead("批次号"));
        header.add(addHead("交货日期"));
        header.add(addHead("IE产能"));
        header.add(addHead("PC产能"));
        header.add(addHead("计划数量"));
        header.add(addHead("待排数量"));
        for (ColumnDto columnDto : dynamicCol) {
            header.add(addHead(columnDto.getTitle()));
        }
        return header;
    }

    private List<String> addHead(String title){
        List<String> head = new ArrayList<>();
        head.add(title);
        return head;
    }

    /**
     * 子件排产分页查询
     * @param search
     * @param cols
     * @return
     */
    @Transactional
    public PageResult findPlanByPage(Search search,Integer cols){
        PageResult<ApsOrderPlanSon> plans = this.findByPage(search);
        LocalDate now = LocalDate.now();
        LocalDate end = now.plusDays(cols);

        ArrayList<ApsOrderPlanSon> plansRows = plans.getRows();
        List<Map> colsRows = new ArrayList<>();
        for (ApsOrderPlanSon plansRow : plansRows) {
            Map<String, Object> map = toMapDto(plansRow);
            List<ApsOrderPlanSonDetail> orderPlanSonDetails = plansRow.getOrderPlanSonDetails();
            for (ApsOrderPlanSonDetail detail : orderPlanSonDetails) {
                LocalDate planDate = detail.getPlanDate();
                map.put(DateUtils.LocalDateToString(planDate),detail.getPlanQty());
            }
            colsRows.add(map);
        }
        PageResult result = new PageResult(plans);
        result.setRows(colsRows);
        return result;
    }


    /**
     * 子件生成生产计划
     * 遍历排产中料品查找bom，存在多条则往下遍历，找到所有子件（记作M）
     * 根据销售单号与子件集M，查找工单，存在即写入子件表
     * * 找到组件集合 {@link #u9BomService.getMinorComponents(Long)}
     * * 根据组件masterId找到子件存入子件集合，并一直查找直到不存在组件
     * * 根据子件集合查询工单
     * * 按照母件下达数量比例，子件下达相同比例的数量;若母件数量下达完，子件同样下达完
     * 使用待排单id作关联，总计划数量=生产数量
     * @param innerDto
     */
    public void planSubComponent(ApsOrderDto innerDto , ApsOrderPlan orderPlan){
        List<U9Bom> boms = u9BomService.getMinorComponents(innerDto.getMaterialId());
        List<Long> subComponentIds = boms.stream().map(b -> Long.valueOf(b.getMaterial().getId())).collect(Collectors.toList());
        if (StringUtils.isNotEmpty(innerDto.getOrderNo()) && innerDto.getOrderNo().indexOf("&")>0){
            String soNo = innerDto.getOrderNo().split("&")[0];
            List<OrderAndU9> orders = produceOrderDao.queryInnerOrderBySoIdAndMaterialIdIn(soNo, subComponentIds);

            List<ApsOrderPlanSon> sonList = new ArrayList<>();
            for (OrderAndU9 order : orders) {
                ApsOrderPlanSon apsOrderPlanSon = new ApsOrderPlanSon();
                apsOrderPlanSon.setOrderId(innerDto.getId());
                apsOrderPlanSon.setPlanId(orderPlan.getId());
                apsOrderPlanSon.setU9No(order.getU9ProduceOrder().getDocNo());
                apsOrderPlanSon.setPlanNum(innerDto.getPlanNum());
                apsOrderPlanSon.setWorkGroupId(innerDto.getWorkGroupId());
                apsOrderPlanSon.setWorkGroupName(innerDto.getWorkGroupName());
                apsOrderPlanSon.setLineId(innerDto.getWorkLineId());
                apsOrderPlanSon.setLineName(innerDto.getWorkLineName());
                apsOrderPlanSon.setMaterialId(order.getApsOrder().getMaterialId());
                apsOrderPlanSon.setMaterialCode(order.getU9ProduceOrder().getMaterial().getCode());
                apsOrderPlanSon.setMaterialName(order.getU9ProduceOrder().getMaterial().getName());
                apsOrderPlanSon.setMaterialSpec(order.getU9ProduceOrder().getMaterial().getSpec());
                apsOrderPlanSon.setScmDeliveryDate(innerDto.getDeliveryStartDate());
//                Optional<ApsMaterialCapacity> capacityOptional = materialCapacityDao.findTopByMaterialIdAndFrozenIsFalse(order.getU9ProduceOrder().getMaterialId());
//                if (capacityOptional.isPresent()){
//                    ApsMaterialCapacity capacity = capacityOptional.get();
//                    apsOrderPlanSon.setIeStandardQty(capacity.getStandardIeQty());
//                    apsOrderPlanSon.setPcStandardQty(capacity.getStandardPcQty());
//                }
//                BigDecimal subPlanQty = getSubPlanQty(innerDto, order);
//                apsOrderPlanSon.setPlanQty(subPlanQty);
//                apsOrderPlanSon.setAwaitQty(subPlanQty);
//
//                sonList.add(apsOrderPlanSon);
            }
//            this.save(sonList);
        }
    }



    /**
     * 计算子件排产数量
     * 情况1：母件全部下达完毕
     * * 若 本次下达数量+已下达数量 = 生产数量 ， 则子件下达剩余数量 子件生产数量 - 已下达数量 = 下达数量
     * 情况2：母件部分下达
     * * 计算母件下达比例，子件按照母件比例  下达
     * * 母件下达数量 / 生产数量 = 下达比例(保留4位小数)
     * * 子件生产数量 * 下达比例 = 下达数量(向下取整)
     * @param innerDto
     * @param order
     * @return
     */
    private BigDecimal getSubPlanQty(ApsOrderDto innerDto, OrderAndU9 order) {
        BigDecimal planedQty = NumberUtils.addBigDecimal(innerDto.getPlanQty(),innerDto.getTotalPlanQty());
        if (planedQty.equals(innerDto.getProduceQty())){
            List<ApsOrderPlanSon> sons = dao.findByOrderIdAndMaterialId(innerDto.getId(),order.getApsOrder().getMaterialId());
            BigDecimal sonsQty = BigDecimal.ZERO;
            for (ApsOrderPlanSon son : sons) {
                sonsQty = NumberUtils.addBigDecimal(sonsQty,son.getPlanQty());
            }
            return order.getApsOrder().getProduceQty().subtract(sonsQty);
        }else{
            BigDecimal proportion = innerDto.getPlanQty().divide(innerDto.getProduceQty(), 4, BigDecimal.ROUND_HALF_UP);
            return order.getApsOrder().getProduceQty().multiply(proportion).setScale(0,BigDecimal.ROUND_DOWN);
        }
    }

    private Map<String, Object> toMapDto(ApsOrderPlanSon row) {
        Map<String, Object> map = new HashMap<>();
        map.put("id",row.getId());
        map.put("lineId",row.getLineId());
        map.put("lineName",row.getLineName());
        map.put("workGroupId",row.getWorkGroupId());
        map.put("workGroupName",row.getWorkGroupName());
        map.put("awaitQty",row.getAwaitQty());
        map.put("planQty",row.getPlanQty());
        map.put("planNum",row.getPlanNum());
        map.put("materialId",row.getMaterialId());
        map.put("materialCode",row.getMaterialCode());
        map.put("materialName",row.getMaterialName());
        map.put("materialSpec",row.getMaterialSpec());
        map.put("orderDate",row.getOrderDate());
        map.put("scmDeliveryDate",row.getScmDeliveryDate());
        map.put("remark",row.getRemark());
        map.put("oweQty",row.getOweQty());
        map.put("hasQty",row.getHasQty());
        map.put("ieStandardQty",row.getIeStandardQty());
        map.put("pcStandardQty",row.getPcStandardQty());
        map.put("orderId",row.getOrderId());
        map.put("planId",row.getPlanId());
        map.put("u9No",row.getU9No());
        map.put("componentOrderNo",row.getApsOrder().getOrderNo());
        map.put("tenantCode",row.getTenantCode());
        return map;
    }


}
