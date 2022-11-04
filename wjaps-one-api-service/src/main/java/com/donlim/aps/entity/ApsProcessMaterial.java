package com.donlim.aps.entity;

import com.changhong.sei.core.entity.BaseAuditableEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 工序料品配置(ApsProcessMaterial)实体类
 *
 * @author sei
 * @since 2022-06-08 10:53:18
 */
@Entity
@Table(name = "aps_process_material")
@DynamicInsert
@DynamicUpdate
public class ApsProcessMaterial extends BaseAuditableEntity implements Serializable {
    private static final long serialVersionUID = -46879403747964298L;
    /**
     * 料品id
     */
    @Column(name = "material_id")
    private Long materialId;
    /**
     * 工序id
     */
    @Column(name = "process_id")
    private String processId;

    @ManyToOne
    @JoinColumn(name = "process_id",updatable = false,insertable = false)
    private ApsProcess apsProcess;
    /**
     * 组织id
     */
    @Column(name = "organize_id")
    private String organizeId;

    @ManyToOne
    @JoinColumn(name = "organize_id",updatable = false,insertable = false)
    private ApsOrganize apsOrganize;
    /**
     * 小时产能
     */
    @Column(name = "capacity")
    private BigDecimal capacity;
    /**
     * 标准人力
     */
    @Column(name = "standard_peoples")
    private Integer standardPeoples;
    /**
     * 启用状态
     */
    @Column(name = "frozen")
    private Boolean frozen;
    /**
     * 排序
     */
    @Column(name = "sort_code")
    private Integer sortCode;
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

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getOrganizeId() {
        return organizeId;
    }

    public void setOrganizeId(String organizeId) {
        this.organizeId = organizeId;
    }

    public BigDecimal getCapacity() {
        return capacity;
    }

    public void setCapacity(BigDecimal capacity) {
        this.capacity = capacity;
    }

    public Integer getStandardPeoples() {
        return standardPeoples;
    }

    public void setStandardPeoples(Integer standardPeoples) {
        this.standardPeoples = standardPeoples;
    }

    public Boolean getFrozen() {
        return frozen;
    }

    public void setFrozen(Boolean frozen) {
        this.frozen = frozen;
    }

    public Integer getSortCode() {
        return sortCode;
    }

    public void setSortCode(Integer sortCode) {
        this.sortCode = sortCode;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

    public ApsProcess getApsProcess() {
        return apsProcess;
    }

    public void setApsProcess(ApsProcess apsProcess) {
        this.apsProcess = apsProcess;
    }

    public ApsOrganize getApsOrganize() {
        return apsOrganize;
    }

    public void setApsOrganize(ApsOrganize apsOrganize) {
        this.apsOrganize = apsOrganize;
    }
}
