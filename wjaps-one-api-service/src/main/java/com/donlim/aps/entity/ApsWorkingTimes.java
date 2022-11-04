package com.donlim.aps.entity;

import com.changhong.sei.core.entity.BaseAuditableEntity;
import com.changhong.sei.core.entity.ITenant;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 上班日历配置表(ApsWorkingTimes)实体类
 *
 * @author sei
 * @since 2022-05-04 10:15:29
 */
@Entity
@Table(name = "aps_working_times")
@DynamicInsert
@DynamicUpdate
public class ApsWorkingTimes extends BaseAuditableEntity implements Serializable, ITenant {
    private static final long serialVersionUID = 236870149772245120L;
    /**
     * 上班日期
     */
    @Column(name = "work_date")
    private LocalDate workDate;

    /**
     * 组织id(生产线)
     */
    @Column(name = "organize_id")
    private String organizeId;

    /**
     * 组织(生产线)
     */
    @ManyToOne
    @JoinColumn(name = "organize_id",insertable = false,updatable = false)
    private ApsOrganize apsOrganize;
    /**
     * 标准上班时长
     */
    @Column(name = "work_hour")
    private BigDecimal workHour;
    /**
     * 加班时长
     */
    @Column(name = "over_time_hour")
    private BigDecimal overTimeHour;
    /**
     * 人数
     */
    @Column(name = "num_of_people")
    private Integer numOfPeople;
    /**
     * 总时长
     */
    @Column(name = "total_hour")
    private BigDecimal totalHour;
    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;
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

    public LocalDate getWorkDate() {
        return workDate;
    }

    public void setWorkDate(LocalDate workDate) {
        this.workDate = workDate;
    }

    public BigDecimal getWorkHour() {
        return workHour;
    }

    public void setWorkHour(BigDecimal workHour) {
        this.workHour = workHour;
    }

    public BigDecimal getOverTimeHour() {
        return overTimeHour;
    }

    public String getOrganizeId() {
        return organizeId;
    }

    public void setOrganizeId(String organizeId) {
        this.organizeId = organizeId;
    }

    public ApsOrganize getApsOrganize() {
        return apsOrganize;
    }

    public void setApsOrganize(ApsOrganize apsOrganize) {
        this.apsOrganize = apsOrganize;
    }

    public void setOverTimeHour(BigDecimal overTimeHour) {
        this.overTimeHour = overTimeHour;
    }

    public Integer getNumOfPeople() {
        return numOfPeople;
    }

    public void setNumOfPeople(Integer numOfPeople) {
        this.numOfPeople = numOfPeople;
    }

    public BigDecimal getTotalHour() {
        return totalHour;
    }

    public void setTotalHour(BigDecimal totalHour) {
        this.totalHour = totalHour;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
