package com.donlim.aps.entity;

import com.changhong.sei.core.entity.BaseAuditableEntity;
import com.changhong.sei.core.entity.ITenant;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 生产计划明细表(子件)(ApsOrderPlanSonDetail)实体类
 *
 * @author sei
 * @since 2022-05-28 10:20:29
 */
@Entity
@Table(name = "aps_order_plan_son_detail")
@DynamicInsert
@DynamicUpdate
public class ApsOrderPlanSonDetail extends BaseAuditableEntity implements Serializable , ITenant {
    private static final long serialVersionUID = -55481976251490239L;
    /**
     * 排产id
     */
    @Column(name = "plan_id")
    private String planId;
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
