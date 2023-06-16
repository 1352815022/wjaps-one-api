package com.donlim.aps.entity;

import com.changhong.sei.core.entity.BaseAuditableEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * (OneApsPlanData)实体类
 *
 * @author sei
 * @since 2022-07-27 17:42:03
 */
@Entity
@Table(name = "one_aps_plan_data")
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
public class OneApsPlanData extends BaseAuditableEntity implements Serializable {
    private static final long serialVersionUID = -52004342963786180L;
    /**
     * 需求分类号
     */
    @Column(name = "order_no")
    private String orderNo;
    /**
     * 生产日期
     */
    @Column(name = "date")
    private LocalDate date;
    /**
     * 生产数量
     */
    @Column(name = "qty")
    private Integer qty;
    /**
     * 租户代码
     */
    @Column(name = "tenant_code")
    private String tenantCode;




}
