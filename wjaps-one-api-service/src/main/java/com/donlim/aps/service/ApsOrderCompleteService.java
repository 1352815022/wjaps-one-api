package com.donlim.aps.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.changhong.sei.core.service.bo.OperateResultWithData;
import com.donlim.aps.dao.ApsOrderCompleteDao;
import com.donlim.aps.dto.DeliveryOrderDto;
import com.donlim.aps.entity.*;
import com.donlim.aps.util.CompanyEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * 订单齐套(ApsOrderComplete)业务逻辑实现类
 *
 * @author czq
 * @since 2022-07-13 13:59:13
 */
@Service
public class ApsOrderCompleteService extends BaseEntityService<ApsOrderComplete> {
    @Autowired
    private ApsOrderCompleteDao dao;

    @Override
    protected BaseEntityDao<ApsOrderComplete> getDao() {
        return dao;
    }

    @Autowired
    private ApsOrderDetailCompleteService apsOrderDetailCompleteService;

    @Autowired
    private ScmXbDeliveryService scmXbDeliveryService;

    @Autowired
    private U9ProduceOrderService u9ProduceOrderService;

    @Autowired
    private U9PurchaseService u9PurchaseService;



    /**
     * 获取SCM订单数据
     * @return
     */
    private List<DeliveryOrderDto> getOrderList() {
        LocalDate ld = LocalDate.now().plusDays(-60);
        List orderNos = scmXbDeliveryService.findOrderNum(ld);
        List<DeliveryOrderDto> orderList = new ArrayList<>();
        for (Object row : orderNos) {
            DeliveryOrderDto deliveryOrderDto = new DeliveryOrderDto();
            Object[] cells = (Object[]) row;
            deliveryOrderDto.setNum((BigInteger) cells[0]);
            deliveryOrderDto.setOrderNo((String) cells[1]);
            deliveryOrderDto.setProductModel((String)cells[2]);
            orderList.add(deliveryOrderDto);
        }
        return orderList;
    }
    /**
     * 更新齐套信息
     */
    @Transactional(rollbackFor = Exception.class)
    public void orderCompleteTask() {
        List<DeliveryOrderDto> orderList = getOrderList();
        List<String>orders=new ArrayList<>();
        for (DeliveryOrderDto deliveryOrderDto : orderList) {
            orders.add(deliveryOrderDto.getOrderNo());
        }
        //把已经存在的取出来，避免后面重复查询
        List<ApsOrderComplete> hasOrderList = dao.findByOrderNoIn(orders);
        for (DeliveryOrderDto deliveryOrderDto : orderList) {
            //根据需求分类号判断是否已经存在
            Optional<ApsOrderComplete> findOrderNoEntity = hasOrderList.stream().filter(a -> a.getOrderNo().equals(deliveryOrderDto.getOrderNo())).findFirst();
            ApsOrderComplete apsOrderComplete;
            if(findOrderNoEntity.isPresent()){
                apsOrderComplete=findOrderNoEntity.get();
                apsOrderDetailCompleteService.deleteByParentId(apsOrderComplete.getId());
            }else{
                apsOrderComplete=new ApsOrderComplete();
            }

            //订单数
            int allNum=0;
            //完成数
            int finishNum=0;
            List<ApsOrderDetailComplete>apsOrderDetailCompleteList=new ArrayList<>();
            //查出同一需求分类号订单
            List<ScmXbDelivery>scmXbDeliveryList=scmXbDeliveryService.findAllByOrderNo(deliveryOrderDto.getOrderNo());
            for (ScmXbDelivery scmXbDelivery : scmXbDeliveryList) {
                //根据需求分类号和料号匹配工单
                List<U9ProduceOrder> productOrderList = u9ProduceOrderService.findAllBySoIdAndMaterialCode(scmXbDelivery.getOrderNo(),scmXbDelivery.getMaterialCode());
                for (U9ProduceOrder u9ProduceOrder : productOrderList) {
                    allNum++;
                    ApsOrderDetailComplete apsOrderDetailComplete=new ApsOrderDetailComplete();
                    apsOrderDetailComplete.setProductOrder(u9ProduceOrder.getDocNo());
                    apsOrderDetailComplete.setOrderNo(u9ProduceOrder.getSoId());
                    apsOrderDetailComplete.setMaterialCode(u9ProduceOrder.getMaterialCode());
                    apsOrderDetailComplete.setMaterialName(u9ProduceOrder.getMaterialName());
                    apsOrderDetailComplete.setProductModel(apsOrderComplete.getProductModel());
                    apsOrderDetailComplete.setRequireQty(u9ProduceOrder.getQty());
                    apsOrderDetailComplete.setFinishQty(u9ProduceOrder.getTotalCompleteQty());
                    if(apsOrderDetailComplete.getFinishQty().compareTo(apsOrderDetailComplete.getRequireQty())>=0){
                        apsOrderDetailComplete.setCompletePercent("100%");
                        finishNum++;
                    }else if(apsOrderDetailComplete.getFinishQty().intValue()==0){
                        apsOrderDetailComplete.setCompletePercent("0%");
                    }else{
                        BigDecimal completePercent= apsOrderDetailComplete.getFinishQty().divide(apsOrderDetailComplete.getRequireQty(),2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100).setScale(2, RoundingMode.HALF_UP));
                        apsOrderDetailComplete.setCompletePercent(completePercent.toString()+"%");
                    }
                    apsOrderDetailCompleteList.add(apsOrderDetailComplete);
                }
                //根据需求分类号和料号匹配采购单
                List<U9Purchase> purchaseList = u9PurchaseService.findAllByDemandCodeAndMaterialCode(scmXbDelivery.getOrderNo(),scmXbDelivery.getMaterialCode());
                for (U9Purchase purchase : purchaseList) {
                    allNum++;
                    ApsOrderDetailComplete apsOrderDetailComplete=new ApsOrderDetailComplete();
                    apsOrderDetailComplete.setMaterialCode(purchase.getMaterialCode());
                    apsOrderDetailComplete.setOrderNo(purchase.getDemandCode());
                    apsOrderDetailComplete.setProductOrder(purchase.getDocNo());
                    apsOrderDetailComplete.setMaterialName(purchase.getMaterialName());
                    apsOrderDetailComplete.setProductModel(apsOrderComplete.getProductModel());
                    apsOrderDetailComplete.setRequireQty(purchase.getReqQty());
                    apsOrderDetailComplete.setFinishQty(purchase.getReceiveQty());
                    if(apsOrderDetailComplete.getFinishQty().compareTo(apsOrderDetailComplete.getRequireQty())>=0){
                        apsOrderDetailComplete.setCompletePercent("100%");
                        finishNum++;
                    }else if(apsOrderDetailComplete.getFinishQty().intValue()==0){
                        apsOrderDetailComplete.setCompletePercent("0%");
                    }else{
                        BigDecimal completePercent= apsOrderDetailComplete.getFinishQty().divide(apsOrderDetailComplete.getRequireQty(),2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100).setScale(2, RoundingMode.HALF_UP));
                        apsOrderDetailComplete.setCompletePercent(completePercent.toString()+"%");
                    }
                    apsOrderDetailCompleteList.add(apsOrderDetailComplete);
                }

            }
            apsOrderComplete.setCompleteNum(finishNum);
            apsOrderComplete.setOrderNum(allNum);
            if(apsOrderComplete.getCompleteNum()>=apsOrderComplete.getOrderNum()){
                apsOrderComplete.setCompletePercent("100%");
            }else if (finishNum==0){
                apsOrderComplete.setCompletePercent("0%");
            }else{
                float percent=(float)apsOrderComplete.getCompleteNum()/(float)apsOrderComplete.getOrderNum()*100;
                DecimalFormat df = new DecimalFormat("0.00");
                apsOrderComplete.setCompletePercent(df.format(percent)+"%");
            }
            apsOrderComplete.setCompany(CompanyEnum.WJ1_SCM.getName());
            apsOrderComplete.setOrderNo(deliveryOrderDto.getOrderNo());
            apsOrderComplete.setProductModel(deliveryOrderDto.getProductModel());
            OperateResultWithData<ApsOrderComplete> save = save(apsOrderComplete);
            for (ApsOrderDetailComplete apsOrderDetailComplete : apsOrderDetailCompleteList) {
                apsOrderDetailComplete.setParentId(save.getData().getId());
            }
            apsOrderDetailCompleteService.save(apsOrderDetailCompleteList);
        }
    }

}
