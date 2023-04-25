package com.donlim.aps.entity;

import com.changhong.sei.core.entity.BaseAuditableEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * 采购计划表(ApsPurchasePlan)实体类
 *
 * @author sei
 * @since 2022-05-20 09:16:29
 */
@Entity
@Table(name = "aps_purchase_plan")
@DynamicInsert
@DynamicUpdate
@Data
public class ApsPurchasePlan extends BaseAuditableEntity implements Serializable {
    private static final long serialVersionUID = -62333276591878401L;
    /**
     * 订单号
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 需求分类号
     */
    @Column(name = "so_no")
    private String soNo;

    @ManyToOne
    @JoinColumn(name = "order_id",insertable = false,updatable = false)
    private ApsOrder order;
    /**
     * 订单id
     */
    @Column(name = "order_id")
    private String orderId;
    /**
     * 供应商编码
     */
    @Column(name = "supplier_code")
    private String supplierCode;
    /**
     * 供应商简称
     */
    @Column(name = "supplier_name")
    private String supplierName;

    /**
     * 品名
     */
    @Column(name = "product_model")
    private String productModel;
    /**
     * 产品id
     */
    @Column(name = "material_id")
    private Long materialId;
    /**
     * 物料编码
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
     * 料品类别
     */
    @Column(name = "material_type")
    private String materialType;
    /**
     * 单位
     */
    @Column(name = "unit")
    private String unit;
    /**
     * 已送货数量
     */
    @Column(name = "sum_arrival_qty")
    private BigDecimal sumArrivalQty;
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
     * 欠数
     */
    @Column(name = "owe_qty")
    private BigDecimal oweQty;
    /**
     * 下单日期
     */
    @Column(name = "order_date")
    private LocalDate orderDate;
    /**
     * 开始送货日期
     */
    @Column(name = "delivery_start_date")
    private LocalDate deliveryStartDate;
    /**
     * 最后送货日期
     */
    @Column(name = "delivery_end_date")
    private LocalDate deliveryEndDate;
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
     * 订单交期
     */
    @Column(name = "delivery_date")
    private LocalDate deliveryDate;
    /**
     * 组织ID
     */
    @Column(name = "org_id")
    private Long orgId;
    /**
     * 计划开始日期
     */
    @Column(name = "start_date")
    private LocalDate startDate;
    /**
     * 采购员
     */
    @Column(name = "buyer")
    private String buyer;
    /**
     * 采购订单行号
     */
    @Column(name = "po_line_no")
    private String poLineNo;
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

    @OneToMany(targetEntity = ApsPurchasePlanDetail.class)
    @JoinColumn(name = "plan_id")
    private List<ApsPurchasePlanDetail> purchasePlanDetails;

}
