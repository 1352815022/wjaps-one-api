package com.donlim.aps.entity;

import com.changhong.sei.core.entity.BaseAuditableEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单表(内部)扩展表(ApsOrderExt)实体类
 *
 * @author sei
 * @since 2023-04-06 08:29:17
 */
@Entity
@Table(name = "aps_order_ext")
@DynamicInsert
@DynamicUpdate
public class ApsOrderExt implements Serializable {
    private static final long serialVersionUID = -90042732745769862L;


    /**
     * 主键
     */
    @Id
    @Column(length = 36, updatable = false)
    protected String id;
    /**
     * 工单号
     */
    @Column(name = "order_no")
    private String orderNo;
    /**
     * 已完成数量
     */
    @Column(name = "finish_qty")
    private BigDecimal finishQty;


    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getFinishQty() {
        return finishQty;
    }

    public void setFinishQty(BigDecimal finishQty) {
        this.finishQty = finishQty;
    }

}