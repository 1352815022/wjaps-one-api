package com.donlim.aps.entity;

import com.changhong.sei.core.entity.BaseAuditableEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * (ApsDayReport)实体类
 *
 * @author sei
 * @since 2023-05-19 11:50:12
 */
@Entity
@Table(name = "aps_day_report")
@DynamicInsert
@DynamicUpdate
@Data
public class ApsDayReport extends BaseAuditableEntity implements Serializable {
    private static final long serialVersionUID = 420168238891456101L;
    /**
     * 日期
     */
    @Column(name = "date")
    private LocalDate date;
    /**
     * 计划数
     */
    @Column(name = "plan_qty")
    private Long planQty;
    /**
     * 完工数
     */
    @Column(name = "finish_qty")
    private Long finishQty;
    /**
     * 未排数
     */
    @Column(name = "no_plan_qty")
    private Long noPlanQty;
    /**
     * 达成率
     */
    @Column(name = "plan_rate")
    private String planRate;
    /**
     * 租户代码
     */
    @Column(name = "tenant_code")
    private String tenantCode;




}
