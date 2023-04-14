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
 * 采购计划明细表(ApsPurchasePlanDetail)实体类
 *
 * @author sei
 * @since 2022-05-23 08:20:13
 */
@Entity
@Table(name = "aps_purchase_plan_detail")
@DynamicInsert
@DynamicUpdate
public class ApsPurchasePlanDetail extends BaseAuditableEntity implements Serializable , ITenant {
    private static final long serialVersionUID = 421686488526643909L;
    /**
     * 排产id
     */

    @Column(name = "plan_id")
    private String planId;

    @ManyToOne(targetEntity = ApsPurchasePlan.class)
    @JoinColumn(name = "plan_id",insertable = false,updatable = false)
    private ApsPurchasePlan apsPurchasePlan;
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


    public ApsPurchasePlan getApsPurchasePlan() {
        return apsPurchasePlan;
    }

    public void setApsPurchasePlan(ApsPurchasePlan apsPurchasePlan) {
        this.apsPurchasePlan = apsPurchasePlan;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
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
