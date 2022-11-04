package com.donlim.aps.entity;

import com.changhong.sei.core.entity.BaseAuditableEntity;
import com.changhong.sei.core.entity.IFrozen;
import com.changhong.sei.core.entity.ITenant;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 组织工艺配置(ApsOrganizeProcessId)实体类
 *
 * @author sei
 * @since 2022-04-26 09:22:12
 */
@Entity
@Table(name = "aps_organize_process_id")
@DynamicInsert
@DynamicUpdate
public class ApsOrganizeProcessId extends BaseAuditableEntity implements Serializable , ITenant, IFrozen {
    private static final long serialVersionUID = 859971922073195488L;
    /**
     * 工艺
     */
    @ManyToOne
    @JoinColumn(name = "process_id",insertable = false,updatable = false)
    private ApsProcess apsProcess;
    /**
     * 组织
     */
    @ManyToOne
    @JoinColumn(name = "organize_id",insertable = false,updatable = false)
    private ApsOrganize apsOrganize;
    /**
     * 状态
     */
    @Column(name = "status")
    private Integer status;
    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

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
     * 排序
     */
    @Column(name = "sort_code")
    private Integer sortCode;

    /**
     * 租户代码
     */
    @Column(name = "tenant_code")
    private String tenantCode;


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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark1) {
        this.remark = remark;
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

    public Integer getSortCode() {
        return sortCode;
    }

    public void setSortCode(Integer sortCode) {
        this.sortCode = sortCode;
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
