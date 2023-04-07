package com.donlim.aps.entity.cust;

import com.donlim.aps.entity.ApsOrder;
import com.donlim.aps.entity.ScmXbDelivery;
import com.donlim.aps.entity.U9Material;
import com.donlim.aps.entity.U9ProduceOrder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @ClassName OrderAndScm
 * @Description 内部待排与scm送货单与U9订单关联实体
 * @Author p09835
 * @Date 2022/5/26 10:22
 **/
@Data
public class OrderAndScmV3 {




    /**
     * 料品id
     */
    private Long materialId;
    /**
     * apsOrderId
     */
    private String apsOrderId;

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


}
