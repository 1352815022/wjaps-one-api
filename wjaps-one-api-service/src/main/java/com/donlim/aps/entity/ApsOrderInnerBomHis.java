package com.donlim.aps.entity;

import com.changhong.sei.core.entity.BaseAuditableEntity;
import com.changhong.sei.core.entity.ITenant;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单表内部分解合并历史表(ApsOrderInnerBomHis)实体类
 *
 * @author sei
 * @since 2022-05-13 16:38:37
 */
@Entity
@Table(name = "aps_order_inner_bom_his")
@DynamicInsert
@DynamicUpdate
public class ApsOrderInnerBomHis extends BaseAuditableEntity implements Serializable , ITenant {
    private static final long serialVersionUID = -13936799886260405L;
    /**
     * 合并至的订单ID
     */
    @Column(name = "new_id")
    private String newId;
    /**
     * 料品id
     */
    @Column(name = "material_id")
    private Long materialId;
    /**
     * 订单数量
     */
    @Column(name = "order_qty")
    private BigDecimal orderQty;
    /**
     * 原订单id
     */
    @Column(name = "origin_id")
    private String originId;
    /**
     * bom用量
     */
    @Column(name = "usage")
    private BigDecimal usage;
    /**
     * 租户代码
     */
    @Column(name = "tenant_code")
    private String tenantCode;


    public String getNewId() {
        return newId;
    }

    public void setNewId(String newId) {
        this.newId = newId;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public BigDecimal getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(BigDecimal orderQty) {
        this.orderQty = orderQty;
    }

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public BigDecimal getUsage() {
        return usage;
    }

    public void setUsage(BigDecimal usage) {
        this.usage = usage;
    }

    @Override
    public String getTenantCode() {
        return tenantCode;
    }

    @Override
    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

}
