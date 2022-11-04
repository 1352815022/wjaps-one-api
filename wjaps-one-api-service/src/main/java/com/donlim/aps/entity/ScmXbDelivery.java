package com.donlim.aps.entity;

import com.changhong.sei.core.entity.BaseAuditableEntity;
import com.changhong.sei.core.entity.ITenant;
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
 * scm送货需求(ScmXbDelivery)实体类
 *
 * @author sei
 * @since 2022-05-18 08:12:55
 */
@Entity
@Table(name = "scm_xb_delivery")
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = false)
public class ScmXbDelivery extends BaseAuditableEntity implements Serializable , ITenant {
    private static final long serialVersionUID = -36882059350015676L;
    /**
     * 采购单号
     */
    @Column(name = "po")
    private String po;
    /**
     * 适用组织
     */
    @Column(name = "company_id")
    private String companyId;
    /**
     * 适用部门
     */
    @Column(name = "dept_id")
    private String deptId;
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
    /**
     * 送货开始日期
     */
    @Column(name = "delivery_start_date")
    private LocalDate deliveryStartDate;
    /**
     * 上一次送货开始日期
     */
    @Column(name = "delivery_old_start_date")
    private LocalDate deliveryOldStartDate;
    /**
     * 送货结束日期
     */
    @Column(name = "delivery_end_date")
    private LocalDate deliveryEndDate;
    /**
     * U9组织编码
     */
    @Column(name = "org_id")
    private long orgId;
    /**
     * 需求分类号
     */
    @Column(name = "order_no")
    private String orderNo;
    /**
     * 型号
     */
    @Column(name = "product_model")
    private String productModel;
    /**
     * 料号
     */
    @Column(name = "material_code")
    private String materialCode;
    /**
     * 料品名称
     */
    @Column(name = "material_name")
    private String materialName;
    /**
     * 合作单位
     */
    @Column(name = "company_name")
    private String companyName;
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
     * 应交货数量
     */
    @Column(name = "delivery_qty")
    private BigDecimal deliveryQty;
    /**
     * 上一次应交货数量
     */
    @Column(name = "delivery_old_qty")
    private BigDecimal deliveryOldQty;

    /**
     * 送货日期是否变更
     */
    @Column(name = "change_date_flag")
    private boolean changeDateFlag;

    /**
     * 送货数量是否变更
     */
    @Column(name = "change_qty_flag")
    private boolean changeQtyFlag;
    /**
     * 日最大需求量
     */
    @Column(name = "day_qty")
    private BigDecimal dayQty;
    /**
     * 欠发数量
     */
    @Column(name = "owe_qty")
    private BigDecimal oweQty;
    /**
     * U9累计到货数量
     */
    @Column(name = "sum_arrival_qty")
    private BigDecimal sumArrivalQty;
    /**
     * 采购员
     */
    @Column(name = "buyer")
    private String buyer;
    /**
     * 规格型号
     */
    @Column(name = "spec")
    private String spec;
    /**
     * 计量单位
     */
    @Column(name = "unit")
    private String unit;
    /**
     * 采购订单行Id
     */
    @Column(name = "po_line_id")
    private long poLineId;
    /**
     * 采购订单行Id
     */
    @Column(name = "po_line_no")
    private String poLineNo;
    /**
     * 标识：0为采购，1为销售
     */
    @Column(name = "type")
    private String type;

    /**
     * 送货计划明细
     */
    @OneToMany(targetEntity = ScmXbDeliveryPlan.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id",insertable = false,updatable = false)
    private List<ScmXbDeliveryPlan> deliveryPlans;
}
