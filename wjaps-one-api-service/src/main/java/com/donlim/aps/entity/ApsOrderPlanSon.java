package com.donlim.aps.entity;

import com.changhong.sei.core.entity.BaseAuditableEntity;
import com.changhong.sei.core.entity.ITenant;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * 生产计划表(子件)(ApsOrderPlanSon)实体类
 *
 * @author sei
 * @since 2022-05-28 10:19:20
 */
@Entity
@Table(name = "aps_order_plan_son")
@DynamicInsert
@DynamicUpdate
public class ApsOrderPlanSon extends BaseAuditableEntity implements Serializable , ITenant {
    private static final long serialVersionUID = -12143789993984685L;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private String orderId;
    @ManyToOne
    @JoinColumn(name = "order_id",updatable = false,insertable = false)
    private ApsOrder apsOrder;
    /**
     * 父生产计划id
     */
    @Column(name = "plan_id")
    private String planId;
    /**
     * 生产计划批次号
     */
    @Column(name = "plan_num")
    private Integer planNum;
    /**
     * u9 工单号
     */
    @Column(name = "u9_no")
    private String u9No;
    /**
     * 产品id
     */
    @Column(name = "material_id")
    private Long materialId;

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
     * 标准PC产能
     */
    @Column(name = "pc_standard_qty")
    private BigDecimal pcStandardQty;
    /**
     * 标准ie产能
     */
    @Column(name = "ie_standard_qty")
    private BigDecimal ieStandardQty;
    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;
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
    @OneToMany(targetEntity = ApsOrderPlanSonDetail.class)
    @JoinColumn(name = "plan_id",insertable = false,updatable = false)
    @OrderBy("planDate asc")
    private List<ApsOrderPlanSonDetail> orderPlanSonDetails;


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getU9No() {
        return u9No;
    }

    public void setU9No(String u9No) {
        this.u9No = u9No;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public Integer getPlanNum() {
        return planNum;
    }

    public void setPlanNum(Integer planNum) {
        this.planNum = planNum;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialSpec() {
        return materialSpec;
    }

    public void setMaterialSpec(String materialSpec) {
        this.materialSpec = materialSpec;
    }

    public BigDecimal getHasQty() {
        return hasQty;
    }

    public void setHasQty(BigDecimal hasQty) {
        this.hasQty = hasQty;
    }

    public BigDecimal getOweQty() {
        return oweQty;
    }

    public void setOweQty(BigDecimal oweQty) {
        this.oweQty = oweQty;
    }

    public BigDecimal getPlanQty() {
        return planQty;
    }

    public void setPlanQty(BigDecimal planQty) {
        this.planQty = planQty;
    }

    public BigDecimal getAwaitQty() {
        return awaitQty;
    }

    public void setAwaitQty(BigDecimal awaitQty) {
        this.awaitQty = awaitQty;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getScmDeliveryDate() {
        return scmDeliveryDate;
    }

    public void setScmDeliveryDate(LocalDate scmDeliveryDate) {
        this.scmDeliveryDate = scmDeliveryDate;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    public BigDecimal getPcStandardQty() {
        return pcStandardQty;
    }

    public void setPcStandardQty(BigDecimal pcStandardQty) {
        this.pcStandardQty = pcStandardQty;
    }

    public BigDecimal getIeStandardQty() {
        return ieStandardQty;
    }

    public void setIeStandardQty(BigDecimal ieStandardQty) {
        this.ieStandardQty = ieStandardQty;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getWorkGroupId() {
        return workGroupId;
    }

    public void setWorkGroupId(String workGroupId) {
        this.workGroupId = workGroupId;
    }

    public String getWorkGroupName() {
        return workGroupName;
    }

    public void setWorkGroupName(String workGroupName) {
        this.workGroupName = workGroupName;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public List<ApsOrderPlanSonDetail> getOrderPlanSonDetails() {
        return orderPlanSonDetails;
    }

    public void setOrderPlanSonDetails(List<ApsOrderPlanSonDetail> orderPlanSonDetails) {
        this.orderPlanSonDetails = orderPlanSonDetails;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    @Override
    public String getTenantCode() {
        return tenantCode;
    }
    @Override
    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

    public ApsOrder getApsOrder() {
        return apsOrder;
    }

    public void setApsOrder(ApsOrder apsOrder) {
        this.apsOrder = apsOrder;
    }
}
