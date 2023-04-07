package com.donlim.aps.entity.cust;

import com.donlim.aps.entity.ApsOrder;
import com.donlim.aps.entity.ScmXbDelivery;
import com.donlim.aps.entity.U9Material;
import com.donlim.aps.entity.U9ProduceOrder;
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
public class OrderAndScm {

    private ApsOrder apsOrder ;

    private ScmXbDelivery scmXbDelivery;

    private U9ProduceOrder u9ProduceOrder;

    private U9Material u9Material;

    public OrderAndScm(ApsOrder apsOrder, ScmXbDelivery scmXbDelivery, U9ProduceOrder u9ProduceOrder, U9Material u9Material) {
        this.apsOrder = apsOrder;
        this.scmXbDelivery = scmXbDelivery;
        this.u9ProduceOrder = u9ProduceOrder;
        this.u9Material = u9Material;
    }

    public OrderAndScm(ApsOrder apsOrder, ScmXbDelivery scmXbDelivery, U9ProduceOrder u9ProduceOrder) {
        this.apsOrder = apsOrder;
        this.scmXbDelivery = scmXbDelivery;
        this.u9ProduceOrder = u9ProduceOrder;
    }

    public OrderAndScm(ApsOrder apsOrder, ScmXbDelivery scmXbDelivery) {
        this.apsOrder = apsOrder;
        this.scmXbDelivery = scmXbDelivery;
    }

    public OrderAndScm(ApsOrder apsOrder, ScmXbDelivery scmXbDelivery, U9Material u9Material) {
        this.apsOrder = apsOrder;
        this.scmXbDelivery = scmXbDelivery;
        this.u9Material = u9Material;
    }






    /**
     * 料品id
     */
    private Long materialId;
    /**
     * apsOrderId
     */
    private String apsOrderId;

    public ApsOrder buildApsOrder(OrderAndScm orderAndScm){
        if (apsOrder == null) {
            apsOrder = new ApsOrder();
        }
        apsOrder.setMaterialId(orderAndScm.getMaterialId());
        apsOrder.setId(orderAndScm.getApsOrderId());

        return apsOrder;
    }

    /**
     * U9订单数量
     */
    private BigDecimal qty;
    /**
     * 已入库数
     */
    private BigDecimal totalCompleteQty;
    /**
     * 状态
     */
    private Integer status;

    public U9ProduceOrder buildU9ProduceOrder(OrderAndScm orderExist) {
        if (u9ProduceOrder == null) {
            u9ProduceOrder = new U9ProduceOrder();
        }
        u9ProduceOrder.setQty(orderExist.getQty());
        u9ProduceOrder.setTotalCompleteQty(orderExist.getTotalCompleteQty());
        u9ProduceOrder.setStatus(orderExist.getStatus());

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
    /**
     * 型号
     */
    private String productModel;

    public ScmXbDelivery buildScmXbDelivery(OrderAndScm orderExist) {
        if (scmXbDelivery == null) {
            scmXbDelivery = new ScmXbDelivery();
        }
        scmXbDelivery.setDeliveryEndDate(orderExist.getDeliveryEndDate());
        scmXbDelivery.setDeliveryStartDate(orderExist.getDeliveryStartDate());
        scmXbDelivery.setProductModel(orderExist.getProductModel());

        return scmXbDelivery;
    }
}
