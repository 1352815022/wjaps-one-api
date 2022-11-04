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
 * 工序表(ApsProcess)实体类
 *
 * @author sei
 * @since 2022-04-19 14:13:38
 */
@Entity
@Table(name = "aps_process")
@DynamicInsert
@DynamicUpdate
public class ApsProcess extends BaseAuditableEntity implements Serializable , ITenant , IFrozen {
    private static final long serialVersionUID = -93735469979235526L;
    /**
     * 工序代码
     */
    @Column(name = "process_code")
    private String processCode;
    /**
     * 工序名称
     */
    @Column(name = "process_name")
    private String processName;
    /**
     * 排产类型
     */
    @Column(name = "scheduling_type")
    private String schedulingType;
    /**
     * 工段
     */
    @Column(name = "process_part_name")
    private String processPartName;
    /**
     * 所属工段
     */
    @Column(name = "work_section")
    private String workSection;
    /**
     * 工序描述
     */
    @Column(name = "description")
    private String description;
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
    private Boolean frozen = Boolean.FALSE;
    /**
     * 租户代码
     */
    @Column(name = "tenant_code")
    private String tenantCode;


    public String getProcessCode() {
        return processCode;
    }

    public void setProcessCode(String processCode) {
        this.processCode = processCode;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getSchedulingType() {
        return schedulingType;
    }

    public void setSchedulingType(String schedulingType) {
        this.schedulingType = schedulingType;
    }

    public String getProcessPartName() {
        return processPartName;
    }

    public void setProcessPartName(String processPartName) {
        this.processPartName = processPartName;
    }

    public String getWorkSection() {
        return workSection;
    }

    public void setWorkSection(String workSection) {
        this.workSection = workSection;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
