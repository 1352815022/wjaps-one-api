package com.donlim.aps.entity;

import com.changhong.sei.core.entity.BaseAuditableEntity;
import com.changhong.sei.core.entity.ITenant;
import com.donlim.aps.dto.MaterialType;
import com.donlim.aps.util.EnumJsonRemarkSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 欠料汇总表(ApsOweMaterialSummary)实体类
 *
 * @author sei
 * @since 2022-06-14 11:52:45
 */
@Entity
@Table(name = "aps_owe_material_summary")
@DynamicInsert
@DynamicUpdate
public class ApsOweMaterialSummary extends BaseAuditableEntity implements Serializable , ITenant {
    private static final long serialVersionUID = 594164555995406722L;
    /**
     * 产品id(原料)
     */
    @Column(name = "material_id")
    private Long materialId;

    @Column(name = "material_code")
    private String materialCode;
    /**
     * 料品名(原料)
     */
    @Column(name = "material_name")
    private String materialName;
    /**
     * 料规格
     */
    @Column(name = "material_spec")
    private String materialSpec;
    /**
     * 料分类/属性
     */
    @Column(name = "material_type")
    @Enumerated(EnumType.STRING)
    @JsonSerialize(using = EnumJsonRemarkSerializer.class)
    private MaterialType materialType;
    /**
     * 库存数量(计算时)
     */
    @Column(name = "stock_qty")
    private BigDecimal stockQty;
    /**
     * 暂收
     */
    @Column(name = "temp_receive_qty")
    private BigDecimal tempReceiveQty;
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
     * 超欠数量
     */
    @Column(name = "beyond_qty")
    private BigDecimal beyondQty;
    /**
     * 请购数量
     */
    @Column(name = "po_qty")
    private BigDecimal poQty;
    /**
     * 欠料日期
     */
    @Column(name = "owe_date")
    private LocalDate oweDate;
    /**
     * 累计排产
     */
    @Column(name = "sum_plan_qty")
    private BigDecimal sumPlanQty;
    /**
     * 租户代码
     */
    @Column(name = "tenant_code")
    private String tenantCode;


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

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public BigDecimal getStockQty() {
        return stockQty;
    }

    public void setStockQty(BigDecimal stockQty) {
        this.stockQty = stockQty;
    }

    public BigDecimal getTempReceiveQty() {
        return tempReceiveQty;
    }

    public void setTempReceiveQty(BigDecimal tempReceiveQty) {
        this.tempReceiveQty = tempReceiveQty;
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

    public BigDecimal getBeyondQty() {
        return beyondQty;
    }

    public void setBeyondQty(BigDecimal beyondQty) {
        this.beyondQty = beyondQty;
    }

    public BigDecimal getPoQty() {
        return poQty;
    }

    public void setPoQty(BigDecimal poQty) {
        this.poQty = poQty;
    }

    public LocalDate getOweDate() {
        return oweDate;
    }

    public void setOweDate(LocalDate oweDate) {
        this.oweDate = oweDate;
    }

    public BigDecimal getSumPlanQty() {
        return sumPlanQty;
    }

    public void setSumPlanQty(BigDecimal sumPlanQty) {
        this.sumPlanQty = sumPlanQty;
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
