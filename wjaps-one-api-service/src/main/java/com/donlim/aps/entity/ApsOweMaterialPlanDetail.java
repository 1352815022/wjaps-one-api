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
 * 欠料计划明细表(ApsOweMaterialPlanDetail)实体类
 *
 * @author sei
 * @since 2022-06-14 11:53:37
 */
@Entity
@Table(name = "aps_owe_material_plan_detail")
@DynamicInsert
@DynamicUpdate
public class ApsOweMaterialPlanDetail extends BaseAuditableEntity implements Serializable , ITenant {
    private static final long serialVersionUID = 296186080466212257L;

    @ManyToOne
    @JoinColumn(name = "parent_id",insertable = false,updatable = false)
    private ApsOweMaterialPlan apsOweMaterialPlan;
    /**
     * 父id
     */
    @Column(name = "parent_id")
    private String parentId;
    /**
     * 计划日期
     */
    @Column(name = "plan_date")
    private LocalDate planDate;
    /**
     * 排产数量
     */
    @Column(name = "plan_qty")
    private BigDecimal planQty;
    /**
     * 租户代码
     */
    @Column(name = "tenant_code")
    private String tenantCode;

    public ApsOweMaterialPlan getApsOweMaterialPlan() {
        return apsOweMaterialPlan;
    }

    public void setApsOweMaterialPlan(ApsOweMaterialPlan apsOweMaterialPlan) {
        this.apsOweMaterialPlan = apsOweMaterialPlan;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public LocalDate getPlanDate() {
        return planDate;
    }

    public void setPlanDate(LocalDate planDate) {
        this.planDate = planDate;
    }

    public BigDecimal getPlanQty() {
        return planQty;
    }

    public void setPlanQty(BigDecimal planQty) {
        this.planQty = planQty;
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
