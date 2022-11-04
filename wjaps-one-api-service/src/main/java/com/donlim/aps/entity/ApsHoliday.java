package com.donlim.aps.entity;

import com.changhong.sei.core.entity.BaseAuditableEntity;
import com.changhong.sei.core.entity.IFrozen;
import com.changhong.sei.core.entity.ITenant;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * 放假表(ApsHoliday)实体类
 *
 * @author sei
 * @since 2022-05-03 08:29:53
 */
@Entity
@Table(name = "aps_holiday")
@DynamicInsert
@DynamicUpdate
public class ApsHoliday extends BaseAuditableEntity implements Serializable , IFrozen, ITenant {
    private static final long serialVersionUID = 375504061677960793L;

    /**
     * 组织
     */
    @ManyToOne
    @JoinColumn(name = "organize_id",insertable = false,updatable = false)
    private ApsOrganize apsOrganize;

    /**
     * 假期名称
     */
    @Column(name = "holiday_name")
    private String holidayName;
    /**
     * 开始日期
     */
    @Column(name = "start_date")
    private LocalDate startDate;
    /**
     * 结束日期
     */
    @Column(name = "end_date")
    private LocalDate endDate;
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

    public ApsOrganize getApsOrganize() {
        return apsOrganize;
    }

    public void setApsOrganize(ApsOrganize apsOrganize) {
        this.apsOrganize = apsOrganize;
    }

    public String getHolidayName() {
        return holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
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
