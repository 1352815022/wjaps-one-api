package com.donlim.aps.entity;

import com.changhong.sei.core.entity.BaseAuditableEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * (U9MoPickList)实体类
 *
 * @author sei
 * @since 2022-06-14 14:26:35
 */
@Entity
@Table(name = "u9_mo_pick_list")
@DynamicInsert
@DynamicUpdate
public class U9MoPickList extends BaseAuditableEntity implements Serializable {
    private static final long serialVersionUID = -51391443035636797L;
    /**
     * 工单id
     */
    @Column(name = "mo_id")
    private Long moId;
    /**
     * 物料id
     */
    @Column(name = "material_id")
    private Long materialId;

    @ManyToOne
    @JoinColumn(name = "material_id" ,insertable = false,updatable = false)
    private U9Material material;
    /**
     * 实际用量
     */
    @Column(name = "actual_req_qty")
    private BigDecimal actualReqQty;
    /**
     * bom用量
     */
    @Column(name = "bom_req_qty")
    private BigDecimal bomReqQty;
    /**
     * 标准用量
     */
    @Column(name = "std_req_qty")
    private BigDecimal stdReqQty;
    /**
     * 是否虚拟件
     */
    @Column(name = "phantom_part_flag")
    private Integer phantomPartFlag;
    /**
     * 每装配件数量
     */
    @Column(name = "qpa")
    private BigDecimal qpa;
    /**
     * 租户代码
     */
    @Column(name = "tenant_code")
    private String tenantCode;
    /**
     * U9组织id
     */
    @Column(name = "org_id")
    private Long orgId;


    public Long getMoId() {
        return moId;
    }

    public void setMoId(Long moId) {
        this.moId = moId;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public U9Material getMaterial() {
        return material;
    }

    public void setMaterial(U9Material material) {
        this.material = material;
    }

    public BigDecimal getActualReqQty() {
        return actualReqQty;
    }

    public void setActualReqQty(BigDecimal actualReqQty) {
        this.actualReqQty = actualReqQty;
    }

    public BigDecimal getBomReqQty() {
        return bomReqQty;
    }

    public void setBomReqQty(BigDecimal bomReqQty) {
        this.bomReqQty = bomReqQty;
    }

    public BigDecimal getStdReqQty() {
        return stdReqQty;
    }

    public void setStdReqQty(BigDecimal stdReqQty) {
        this.stdReqQty = stdReqQty;
    }

    public Integer getPhantomPartFlag() {
        return phantomPartFlag;
    }

    public void setPhantomPartFlag(Integer phantomPartFlag) {
        this.phantomPartFlag = phantomPartFlag;
    }

    public BigDecimal getQpa() {
        return qpa;
    }

    public void setQpa(BigDecimal qpa) {
        this.qpa = qpa;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

}
