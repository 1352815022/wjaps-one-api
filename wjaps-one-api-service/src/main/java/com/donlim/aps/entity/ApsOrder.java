package com.donlim.aps.entity;

import com.changhong.sei.core.entity.BaseAuditableEntity;
import com.donlim.aps.dto.ApsOrderType;
import com.donlim.aps.dto.OrderStatusType;
import com.donlim.aps.dto.U9OrderStatus;
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

/**
 * 订单表(内部)(ApsOrder)实体类
 *
 * @author sei
 * @since 2022-05-10 15:29:56
 */
@Entity
@Table(name = "aps_order")
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
public class ApsOrder extends BaseAuditableEntity implements Serializable {
    private static final long serialVersionUID = 868441185468762785L;

    /**
     * 订单号
     */
    @Column(name = "order_no")
    private String orderNo;
    /**
     * 采购单号
     */
    @Column(name = "po_no")
    private String poNo;
    /**
     * 下单日期
     */
    @Column(name = "order_date")
    private LocalDate orderDate;
    /**
     * 料品id
     */
    @Column(name = "material_id")
    private Long materialId;
    /**
     * 料号
     */
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
     * 产能（件/天）
     */
    @Column(name = "capacity")
    private BigDecimal capacity;
    /**
     * 客户code
     */
    @Column(name = "customer_code")
    private String customerCode;
    /**
     * 客户名称
     */
    @Column(name = "customer_name")
    private String customerName;
    /**
     * 类型：SCM:送货组件 ; SUB:子件 ; INV:库存
     */
    @Enumerated(EnumType.STRING)
    @JsonSerialize(using = EnumJsonRemarkSerializer.class)
    @Column(name = "type")
    private ApsOrderType type;
    /**
     * 状态
     */
    @Enumerated(EnumType.STRING)
    @JsonSerialize(using = EnumJsonRemarkSerializer.class)
    @Column(name = "status")
    private OrderStatusType status;
    /**
     * U9状态(0、开立、1、已核准。2、开工。3、完工。4、核准中。)
     */
    @Enumerated(EnumType.STRING)
    @JsonSerialize(using = EnumJsonRemarkSerializer.class)
    @Column(name = "u9_status")
    private U9OrderStatus u9Status;
    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;
    /**
     * 来源单号
     */
    @Column(name = "source_order_no")
    private String sourceOrderNo;
    /**
     * 生产数量
     */
    @Column(name = "produce_qty")
    private BigDecimal produceQty;
    /**
     * 计划数量
     */
    @Column(name = "plan_qty")
    private BigDecimal planQty;
    /**
     * U9已完成数量
     */
    @Column(name = "total_complete_qty")
    private BigDecimal totalCompleteQty;
    /**
     * 已排数量
     */
    @Column(name = "total_plan_qty")
    private BigDecimal totalPlanQty;
    /**
     * 未排数量
     */
    @Column(name = "no_plan_qty")
    private BigDecimal noPlanQty;

    @Column(name = "plan_start_date")
    private LocalDate planStartDate;
    /**
     * 计划完工日期
     */
    @Column(name = "plan_finish_date")
    private LocalDate planFinishDate;
    /**
     * 交期/送货开始日期
     */
    @Column(name = "delivery_start_date")
    private LocalDate deliveryStartDate;
    /**
     * 送货结束日期
     */
    @Column(name = "delivery_end_date")
    private LocalDate deliveryEndDate;
    /**
     * 投料日期
     */
    @Column(name = "feeding_date")
    private LocalDate feedingDate;
    /**
     * 订单数量
     */
    @Column(name = "order_qty")
    private BigDecimal orderQty;
    /**
     * 欠入库数
     */
    @Column(name = "owe_qty")
    private BigDecimal oweQty;
    /**
     * SCM欠数
     */
    @Column(name = "scm_owe_qty")
    private BigDecimal scmOweQty;
    /**
     * 车间id
     */
    @Column(name = "work_group_id")
    private String workGroupId;
    /**
     * 车间名称
     */
    @Column(name = "work_group_name")
    private String workGroupName;
    /**
     * 班组id
     */
    @Column(name = "work_line_id")
    private String workLineId;
    /**
     * 班组名称
     */
    @Column(name = "work_line_name")
    private String workLineName;
    /**
     * 生产计划批次
     */
    @Column(name = "plan_num")
    private Integer planNum;

    /**
     * 租户代码
     */
    @Column(name = "tenant_code")
    private String tenantCode;
    /**
     * SCM送货单id
     */
    @Column(name = "scm_id")
    private String scmId;


    @Column(name = "product_model")
    private String productModel;

    /**
     * 已完成数量-冗余
     */
    @Column(name = "finish_qty")
    private BigDecimal finishQty;

    ///**
    // * 料品
    // */
    //@OneToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "order_no" , referencedColumnName = "doc_no" ,insertable = false,updatable = false )
    //private U9ProduceOrder u9ProduceOrder;
    //
    //
    ///**
    // * 料品
    // */
    //@OneToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "scm_id" , referencedColumnName = "id" ,insertable = false,updatable = false )
    //private ScmXbDelivery scmXbDelivery;




}
