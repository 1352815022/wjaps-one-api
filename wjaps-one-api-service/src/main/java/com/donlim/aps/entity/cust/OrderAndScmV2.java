package com.donlim.aps.entity.cust;

import com.donlim.aps.entity.*;
import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @ClassName OrderAndScm
 * @Description 内部待排与scm送货单与U9订单关联实体
 * @Author p09835
 * @Date 2022/5/26 10:22
 **/
@Data
public class OrderAndScmV2  extends ApsOrder{

    //private ApsOrder apsOrder ;

    private ScmXbDelivery scmXbDelivery;

    private U9ProduceOrder u9ProduceOrder;

    private U9Material u9Material;

    private ApsOrderExt apsOrderExt ;

    /**
     * U9订单数量
     */
    private BigDecimal u9qty;
    /**
     * 已入库数
     */
    private BigDecimal u9totalCompleteQty;
    /**
     * 状态
     */
    private Integer u9OrderStatus;

    public U9ProduceOrder buildU9ProduceOrder(OrderAndScmV2 orderExist) {
        if (u9ProduceOrder == null) {
            u9ProduceOrder = new U9ProduceOrder();
        }
        u9ProduceOrder.setQty(orderExist.getU9qty());
        u9ProduceOrder.setTotalCompleteQty(orderExist.getU9totalCompleteQty());
        u9ProduceOrder.setStatus(orderExist.getU9OrderStatus());

        return u9ProduceOrder;
    }

    /**
     * 送货开始日期
     */
    private LocalDate deliveryStartDate;
    /**
     * 送货结束日期
     */
    private LocalDate deliveryEndDate;


    private String scmXbProductModel;

    /**
     * 欠发数量
     */
    private BigDecimal scmOweQty;
    /**
     * 应交货数量
     */
    private BigDecimal scmDeliveryQty;
    /**
     * 已完成数量-冗余
     */
    private BigDecimal finishQty;



    public ScmXbDelivery buildScmXbDelivery(OrderAndScmV2 orderExist) {
        if (scmXbDelivery == null) {
            scmXbDelivery = new ScmXbDelivery();
        }
        scmXbDelivery.setDeliveryEndDate(orderExist.getDeliveryEndDate());
        scmXbDelivery.setDeliveryStartDate(orderExist.getDeliveryStartDate());
        scmXbDelivery.setProductModel(orderExist.getScmXbProductModel());
        scmXbDelivery.setOweQty(orderExist.getScmOweQty());
        scmXbDelivery.setDeliveryQty(orderExist.getScmDeliveryQty());


        return scmXbDelivery;
    }

    public ApsOrderExt buildApsOrderExt(OrderAndScmV2 orderExist){

        if (apsOrderExt == null) {
            apsOrderExt = new ApsOrderExt();
        }
        apsOrderExt.setOrderNo(orderExist.getOrderNo());
        apsOrderExt.setFinishQty(orderExist.getFinishQty());

        return apsOrderExt;
    }



}
