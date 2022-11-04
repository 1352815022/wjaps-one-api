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
import java.time.LocalDateTime;

/**
 * (U9Stock)实体类
 *
 * @author sei
 * @since 2022-05-13 09:34:26
 */
@Entity
@Table(name = "u9_stock")
@DynamicInsert
@DynamicUpdate
public class U9Stock extends BaseAuditableEntity implements Serializable, ITenant {
    private static final long serialVersionUID = 282962967859497230L;
    /**
     * 组织ID
     */
    @Column(name = "org_id")
    private Long orgId;
    /**
     * 物料ID
     */
    @Column(name = "material_id")
    private Long materialId;
    /**
     * 物料编码
     */
    @Column(name = "material_code")
    private String materialCode;
    /**
     * 物料名称
     */
    @Column(name = "material_name")
    private String materialName;
    /**
     * 物料规格
     */
    @Column(name = "material_desc")
    private String materialDesc;
    /**
     * 库存可用量
     */
    @Column(name = "store_qty")
    private BigDecimal storeQty;
    /**
     * 预留数量
     */
    @Column(name = "reserve_qty")
    private BigDecimal reserveQty;
    /**
     * 实际库存
     */
    @Column(name = "actual_qty")
    private BigDecimal actualQty;
    /**
     * 单位
     */
    @Column(name = "unit")
    private String unit;
    /**
     * 仓库编码
     */
    @Column(name = "wh_name")
    private String whName;
    /**
     * 同步时间
     */
    @Column(name = "sync_time")
    private LocalDateTime syncTime;
    /**
     * 租户代码
     */
    @Column(name = "tenant_code")
    private String tenantCode;


    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
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

    public String getMaterialDesc() {
        return materialDesc;
    }

    public void setMaterialDesc(String materialDesc) {
        this.materialDesc = materialDesc;
    }

    public BigDecimal getStoreQty() {
        return storeQty;
    }

    public void setStoreQty(BigDecimal storeQty) {
        this.storeQty = storeQty;
    }

    public BigDecimal getReserveQty() {
        return reserveQty;
    }

    public void setReserveQty(BigDecimal reserveQty) {
        this.reserveQty = reserveQty;
    }

    public BigDecimal getActualQty() {
        return actualQty;
    }

    public void setActualQty(BigDecimal actualQty) {
        this.actualQty = actualQty;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getWhName() {
        return whName;
    }

    public void setWhName(String whName) {
        this.whName = whName;
    }

    public LocalDateTime getSyncTime() {
        return syncTime;
    }

    public void setSyncTime(LocalDateTime syncTime) {
        this.syncTime = syncTime;
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
