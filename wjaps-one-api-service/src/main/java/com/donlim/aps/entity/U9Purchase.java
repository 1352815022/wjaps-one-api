package com.donlim.aps.entity;

import com.changhong.sei.core.entity.BaseAuditableEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 采购/委外(U9Purchase)实体类
 *
 * @author sei
 * @since 2022-07-14 16:49:44
 */
@Entity
@Table(name = "u9_purchase")
@DynamicInsert
@DynamicUpdate
public class U9Purchase extends BaseAuditableEntity implements Serializable {
    private static final long serialVersionUID = -81644150618532675L;
    /**
     * 单号
     */
    @Column(name = "doc_no")
    private String docNo;
    /**
     * 料id
     */
    @Column(name = "material_id")
    private Long materialId;
    /**
     * 品名
     */
    @Column(name = "material_name")
    private String materialName;
    /**
     * 料号
     */
    @Column(name = "material_code")
    private String materialCode;
    /**
     * 需求数量
     */
    @Column(name = "req_qty")
    private BigDecimal reqQty;
    /**
     * 实收数量
     */
    @Column(name = "receive_qty")
    private BigDecimal receiveQty;
    /**
     * 供应商名称
     */
    @Column(name = "supplier_name")
    private String supplierName;
    /**
     * 送货时间
     */
    @Column(name = "delivery_date")
    private Date deliveryDate;
    /**
     * 组织id
     */
    @Column(name = "org_id")
    private Long orgId;
    /**
     * 状态 已核准  2 核准中  1  超额关闭  5  自然关闭  3  开立  0  短缺关闭  4
     */
    @Column(name = "status")
    private String status;

    @Column(name = "demand_code")
    private String demandCode;
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

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public BigDecimal getReqQty() {
        return reqQty;
    }

    public void setReqQty(BigDecimal reqQty) {
        this.reqQty = reqQty;
    }

    public BigDecimal getReceiveQty() {
        return receiveQty;
    }

    public void setReceiveQty(BigDecimal receiveQty) {
        this.receiveQty = receiveQty;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDemandCode() {
        return demandCode;
    }

    public void setDemandCode(String demandCode) {
        this.demandCode = demandCode;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

}
