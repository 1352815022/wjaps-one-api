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
 * 欠料计划表(ApsOweMaterialPlan)实体类
 *
 * @author sei
 * @since 2022-06-14 11:53:37
 */
@Entity
@Table(name = "aps_owe_material_plan")
@DynamicInsert
@DynamicUpdate
public class ApsOweMaterialPlan extends BaseAuditableEntity implements Serializable , ITenant {
    private static final long serialVersionUID = 813611438925055571L;
    /**
     * 欠料汇总表id
     */
    @Column(name = "summary_id")
    private String summaryId;
    /**
     * 单号
     */
    @Column(name = "order_no")
    private String orderNo;
    /**
     * 生产部门/供应商
     */
    @Column(name = "work_group")
    private String workGroup;
    /**
     * 产品id(原料)
     */
    @Column(name = "origin_material")
    private Long originMaterial;
    /**
     * 产品id(工单)
     */
    @Column(name = "material_id")
    private Long materialId;

    @Column(name = "material_code")
    private String materialCode;
    /**
     * 料品名
     */
    @Column(name = "material_name")
    private String materialName;
    /**
     * 料规格
     */
    @Column(name = "material_spec")
    private String materialSpec;
    /**
     * 单位
     */
    @Column(name = "unit")
    private String unit;
    /**
     * 排产数
     */
    @Column(name = "plan_qty")
    private BigDecimal planQty;
    /**
     * 已领数量
     */
    @Column(name = "pull_qty")
    private BigDecimal pullQty;
    /**
     * 需求数量
     */
    @Column(name = "require_qty")
    private BigDecimal requireQty;
    /**
     * 欠发数量
     */
    @Column(name = "owe_qty")
    private BigDecimal oweQty;
    /**
     * 租户代码
     */
    @Column(name = "tenant_code")
    private String tenantCode;


    public String getSummaryId() {
        return summaryId;
    }

    public void setSummaryId(String summaryId) {
        this.summaryId = summaryId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getWorkGroup() {
        return workGroup;
    }

    public void setWorkGroup(String workGroup) {
        this.workGroup = workGroup;
    }

    public Long getOriginMaterial() {
        return originMaterial;
    }

    public void setOriginMaterial(Long originMaterial) {
        this.originMaterial = originMaterial;
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

    public String getMaterialSpec() {
        return materialSpec;
    }

    public void setMaterialSpec(String materialSpec) {
        this.materialSpec = materialSpec;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getPlanQty() {
        return planQty;
    }

    public void setPlanQty(BigDecimal planQty) {
        this.planQty = planQty;
    }

    public BigDecimal getPullQty() {
        return pullQty;
    }

    public void setPullQty(BigDecimal pullQty) {
        this.pullQty = pullQty;
    }

    public BigDecimal getRequireQty() {
        return requireQty;
    }

    public void setRequireQty(BigDecimal requireQty) {
        this.requireQty = requireQty;
    }

    public BigDecimal getOweQty() {
        return oweQty;
    }

    public void setOweQty(BigDecimal oweQty) {
        this.oweQty = oweQty;
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
