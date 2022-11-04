package com.donlim.aps.entity;

import com.changhong.sei.core.entity.BaseAuditableEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 生产计划明细表(ApsOrderPlanDetail)实体类
 *
 * @author sei
 * @since 2022-05-16 08:42:07
 */
@Entity
@Table(name = "aps_order_plan_detail")
@DynamicInsert
@DynamicUpdate
public class ApsOrderPlanDetail extends BaseAuditableEntity implements Serializable {
    private static final long serialVersionUID = -46679832606012979L;
    /**
     * 排产id
     */
    @Column(name = "plan_id")
    private String planId;

    @ManyToOne
    @JoinColumn(name = "plan_id",insertable = false,updatable = false)
    private ApsOrderPlan apsOrderPlan;
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


    public ApsOrderPlan getApsOrderPlan() {
        return apsOrderPlan;
    }

    public void setApsOrderPlan(ApsOrderPlan apsOrderPlan) {
        this.apsOrderPlan = apsOrderPlan;
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


}
