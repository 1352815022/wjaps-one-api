package com.donlim.aps.entity;

import com.changhong.sei.core.entity.BaseAuditableEntity;
import com.donlim.aps.dto.OrderStatusType;
import com.donlim.aps.util.EnumJsonRemarkSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * 生产计划表(内部)(ApsOrderPlan)实体类
 *
 * @author sei
 * @since 2022-05-11 16:06:27
 */
@Entity
@Table(name = "aps_order_plan")
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = false)
public class ApsOrderPlan extends BaseAuditableEntity implements Serializable {
    private static final long serialVersionUID = -10513423280855964L;


    /**
     * 订单
     */
    @ManyToOne
    @JoinColumn(name = "order_id",insertable = false,updatable = false)
    private ApsOrder order;
    /**
     * 订单id
     */
    @Column(name = "order_id")
    private String orderId;
    /**
     * 生产计划批次号
     */
    @Column(name = "plan_num")
    private Integer planNum;
    /**
     * 产品id
     */
    @Column(name = "material_id")
    private Long materialId;

    @ManyToOne
    @JoinColumn(name = "material_id",insertable = false,updatable = false)
    private U9Material material;

    @Column(name = "material_code")
    private String materialCode;
    /**
     * 料品名
     */
    @Column(name = "material_name")
    private String materialName;
    /**
     * 料规格
     */
    @Column(name = "material_spec")
    private String materialSpec;
    /**
     * 完成数量
     */
    @Column(name = "has_qty")
    private BigDecimal hasQty;
    /**
     * 欠数
     */
    @Column(name = "owe_qty")
    private BigDecimal oweQty;
    /**
     * 计划数
     */
    @Column(name = "plan_qty")
    private BigDecimal planQty;
    /**
     * 待排数
     */
    @Column(name = "await_qty")
    private BigDecimal awaitQty;
    /**
     * 下单日期
     */
    @Column(name = "order_date")
    private LocalDate orderDate;
    /**
     * SCM交期
     */
    @Column(name = "scm_delivery_date")
    private LocalDate scmDeliveryDate;
    /**
     * 开拉日期
     */
    @Column(name = "production_date")
    private LocalDate productionDate;
    /**
     * 标准产能
     */
    @Column(name = "standard_qty")
    private BigDecimal standardQty;

    /**
     * 实际产能
     */
    @Column(name = "actual_qty")
    private BigDecimal actualQty;

    /**
     * U9状态
     */
    @Column(name = "u9_status")
    private Integer u9Status;
    /**
     * 状态
     */
    @Enumerated(EnumType.STRING)
    @JsonSerialize(using = EnumJsonRemarkSerializer.class)
    @Column(name = "status")
    private OrderStatusType status;
    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;
    /**
     * 订单交期
     */
    @Column(name = "delivery_date")
    private LocalDate deliveryDate;
    /**
     * 是否冻结
     */
    @Column(name = "frozen")
    private Boolean frozen;
    /**
     * 车间id
     */
    @Column(name = "work_group_id")
    private String workGroupId;
    /**
     * 生产车间
     */
    @Column(name = "work_group_name")
    private String workGroupName;
    /**
     * 生产线id
     */
    @Column(name = "line_id")
    private String lineId;
    /**
     * 生产线
     */
    @Column(name = "line_name")
    private String lineName;
    /**
     * 计划开始日期
     */
    @Column(name = "start_date")
    private LocalDate startDate;
    /**
     * 计划结束日期
     */
    @Column(name = "end_date")
    private LocalDate endDate;
    /**
     * 租户代码
     */
    @Column(name = "tenant_code")
    private String tenantCode;

    /**
     * 映射出明细表
     */
    @OneToMany(targetEntity = ApsOrderPlanDetail.class)
    @JoinColumn(name = "plan_id",insertable = false,updatable = false)
    private List<ApsOrderPlanDetail> orderPlanDetails;





}
