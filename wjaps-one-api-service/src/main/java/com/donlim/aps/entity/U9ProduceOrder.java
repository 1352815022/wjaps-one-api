package com.donlim.aps.entity;

import com.changhong.sei.core.entity.BaseAuditableEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 生产单表(U9)(U9ProduceOrder)实体类
 *
 * @author sei
 * @since 2022-05-18 09:35:35
 */
@Entity
@Table(name = "u9_produce_order")
@DynamicInsert
@DynamicUpdate
public class U9ProduceOrder extends BaseAuditableEntity implements Serializable {
    private static final long serialVersionUID = 205861934019331953L;
    /**
     * 生产单号
     */
    @Column(name = "doc_no")
    private String docNo;
    /**
     * 类型
     */
    @Column(name = "type")
    private String type;
    /**
     * 组织ID
     */
    @Column(name = "org_id")
    private Long orgId;
    /**
     * 下单日期
     */
    @Column(name = "order_date")
    private LocalDateTime orderDate;
    /**
     * 料品
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "material_id",insertable = false,updatable = false )
    private U9Material material;

    /**
     * 物料id
     */
    @Column(name = "material_id")
    private Long materialId;
    /**
     * 料号
     */
    @Column(name = "material_code")
    private String materialCode;
    /**
     * 料名
     */
    @Column(name = "material_name")
    private String materialName;
    /**
     * 客户id
     */
    @Column(name = "customer_id")
    private String customerId;
    /**
     * 状态
     */
    @Column(name = "status")
    private Integer status;
    /**
     * 销售单号
     */
    @Column(name = "so_id")
    private String soId;

    /**
     * 欠入库数
     */
    @Column(name = "owe_qty")
    private BigDecimal oweQty;
    /**
     * 订单数量
     */
    @Column(name = "qty")
    private BigDecimal qty;
    /**
     * 已入库数
     */
    @Column(name = "total_complete_qty")
    private BigDecimal totalCompleteQty;
    /**
     * U9创建人
     */
    @Column(name = "create_by")
    private String createBy;
    /**
     * 租户代码
     */
    @Column(name = "tenant_code")
    private String tenantCode;


    public String getDocNo() {
        return docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public U9Material getMaterial() {
        return material;
    }

    public void setMaterial(U9Material material) {
        this.material = material;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSoId() {
        return soId;
    }

    public void setSoId(String soId) {
        this.soId = soId;
    }

    public BigDecimal getOweQty() {
        return oweQty;
    }

    public void setOweQty(BigDecimal oweQty) {
        this.oweQty = oweQty;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public BigDecimal getTotalCompleteQty() {
        return totalCompleteQty;
    }

    public void setTotalCompleteQty(BigDecimal totalCompleteQty) {
        this.totalCompleteQty = totalCompleteQty;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

}
