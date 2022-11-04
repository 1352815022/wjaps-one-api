package com.donlim.aps.entity;

import com.changhong.sei.core.entity.BaseAuditableEntity;
import com.changhong.sei.core.entity.IFrozen;
import com.changhong.sei.core.entity.ITenant;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * (ApsMaterialCapacity)实体类
 *
 * @author sei
 * @since 2022-05-09 11:17:10
 */
@Entity
@Table(name = "aps_material_capacity")
@DynamicInsert
@DynamicUpdate
public class ApsMaterialCapacity extends BaseAuditableEntity implements Serializable, IFrozen, ITenant {
    private static final long serialVersionUID = 402375853372267333L;
    /**
     * u9物料id
     */
    @Column(name = "material_id")
    private Long materialId;
    /**
     * u9物料编码
     */
    @Column(name = "material_code")
    private String materialCode;
    /**
     * u9物料名称
     */
    @Column(name = "material_name")
    private String materialName;
    /**
     * u9物料规格
     */
    @Column(name = "material_desc")
    private String materialDesc;
    /**
     * PC工程师标准产能
     */
    @Column(name = "standard_qty")
    private BigDecimal standardQty;

    /**
     * 车间
     */
    @Column(name = "work_group_id")
    private String workGroupId;

    @ManyToOne
    @JoinColumn(name = "work_group_id",insertable = false,updatable = false)
    private ApsOrganize workGroup;

    /**
     * 班组
     */
    @Column(name = "work_line_id")
    private String workLineId;

    @ManyToOne
    @JoinColumn(name = "work_line_id",insertable = false,updatable = false)
    private ApsOrganize workLine;

    /**
     * 是否冻结
     */
    @Column(name = "frozen")
    private Boolean frozen;
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

    public String getMaterialDesc() {
        return materialDesc;
    }

    public void setMaterialDesc(String materialDesc) {
        this.materialDesc = materialDesc;
    }

    public BigDecimal getStandardQty() {
        return standardQty;
    }

    public void setStandardQty(BigDecimal standardQty) {
        this.standardQty = standardQty;
    }

    public String getWorkGroupId() {
        return workGroupId;
    }

    public void setWorkGroupId(String workGroupId) {
        this.workGroupId = workGroupId;
    }

    public ApsOrganize getWorkGroup() {
        return workGroup;
    }

    public void setWorkGroup(ApsOrganize workGroup) {
        this.workGroup = workGroup;
    }

    public String getWorkLineId() {
        return workLineId;
    }

    public void setWorkLineId(String workLineId) {
        this.workLineId = workLineId;
    }

    public ApsOrganize getWorkLine() {
        return workLine;
    }

    public void setWorkLine(ApsOrganize workLine) {
        this.workLine = workLine;
    }

    @Override
    public Boolean getFrozen() {
        return frozen;
    }

    @Override
    public void setFrozen(Boolean frozen) {
        this.frozen = frozen;
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
