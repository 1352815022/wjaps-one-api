package com.donlim.aps.entity;

import com.changhong.sei.core.entity.BaseAuditableEntity;
import com.changhong.sei.core.entity.IFrozen;
import com.changhong.sei.core.entity.ITenant;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 工艺表(ApsProcessGroup)实体类
 *
 * @author sei
 * @since 2022-04-20 16:11:37
 */
@Entity
@Table(name = "aps_process_group")
@DynamicInsert
@DynamicUpdate
public class ApsProcessGroup extends BaseAuditableEntity implements Serializable , ITenant , IFrozen {
    private static final long serialVersionUID = -34885900311556214L;
    /**
     * 工艺
     */
    @Column(name = "process_group_name")
    private String processGroupName;
    /**
     * 组织id
     */
    @Column(name = "organize_id")
    private String organizeId;
    /**
     * 状态
     */
    @Column(name = "status")
    private Integer status;
    /**
     * 备注1
     */
    @Column(name = "remark")
    private String remark;
    /**
     * 按区域排产
     */
    @Column(name = "by_region")
    private Boolean byRegion;
    /**
     * 适用组织
     */
    @Column(name = "company_id")
    private String companyId;
    /**
     * 适用部门
     */
    @Column(name = "dept_id")
    private String deptId;
    /**
     * 启用状态
     */
    @Column(name = "frozen")
    private Boolean frozen;
    /**
     * 租户代码
     */
    @Column(name = "tenant_code")
    private String tenantCode;


    public String getProcessGroupName() {
        return processGroupName;
    }

    public void setProcessGroupName(String processGroupName) {
        this.processGroupName = processGroupName;
    }

    public String getOrganizeId() {
        return organizeId;
    }

    public void setOrganizeId(String organizeId) {
        this.organizeId = organizeId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getByRegion() {
        return byRegion;
    }

    public void setByRegion(Boolean byRegion) {
        this.byRegion = byRegion;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
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
