package com.donlim.aps.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import com.changhong.sei.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 采购计划表(ApsPurchasePlan)DTO类
 *
 * @author sei
 * @since 2022-05-20 09:16:30
 */
@ApiModel(description = "采购计划表DTO")
public class ApsPurchasePlanDto extends BaseEntityDto {
    private static final long serialVersionUID = -76008747469305881L;
    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单号")
    private String orderNo;
    /**
     * 订单id
     */
    @ApiModelProperty(value = "订单id")
    private String orderId;
    /**
     * 供应商编码
     */
    @ApiModelProperty(value = "供应商编码")
    private String supplierCode;
    /**
     * 供应商简称
     */
    @ApiModelProperty(value = "供应商简称")
    private String supplierName;
    @ApiModelProperty(value = "采购员")
    private String buyer;
    /**
     * 产品id
     */
    @ApiModelProperty(value = "产品id")
    private Long materialId;
    /**
     * 物料编码
     */
    @ApiModelProperty(value = "物料编码")
    private String materialCode;
    /**
     * 料品名
     */
    @ApiModelProperty(value = "料品名")
    private String materialName;
    /**
     * 料规格
     */
    @ApiModelProperty(value = "料规格")
    private String materialSpec;
    /**
     * 料规格
     */
    @ApiModelProperty(value = "型号")
    private String productModel;
    /**
     * 料品类别
     */
    @ApiModelProperty(value = "料品类别")
    private String materialType;
    /**
     * 单位
     */
    @ApiModelProperty(value = "单位")
    private String unit;
    /**
     * 已送货数量
     */
    @ApiModelProperty(value = "已送货数量")
    private BigDecimal sumArrivalQty;
    /**
     * 计划数
     */
    @ApiModelProperty(value = "计划数")
    private BigDecimal planQty;
    /**
     * 待排数
     */
    @ApiModelProperty(value = "待排数")
    private BigDecimal awaitQty;
    /**
     * 欠数
     */
    @ApiModelProperty(value = "欠数")
    private BigDecimal oweQty;
    /**
     * 下单日期
     */
    @ApiModelProperty(value = "下单日期")
    @JsonFormat(pattern = DateUtils.DEFAULT_TIME_FORMAT)
    private LocalDate orderDate;
    /**
     * SCM交期
     */
    @ApiModelProperty(value = "开始送货日期")
    @JsonFormat(pattern = DateUtils.DEFAULT_DATE_FORMAT)
    private LocalDate deliveryStartDate;
    /**
     * 开拉日期
     */
    @ApiModelProperty(value = "最后送货日期")
    @JsonFormat(pattern = DateUtils.DEFAULT_DATE_FORMAT)
    private LocalDate deliveryEndDate;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 订单交期
     */
    @ApiModelProperty(value = "订单交期")
    @JsonFormat(pattern = DateUtils.DEFAULT_TIME_FORMAT)
    private LocalDate deliveryDate;
    /**
     * 组织ID
     */
    @ApiModelProperty(value = "组织ID")
    private Long orgId;
    /**
     * 计划开始日期
     */
    @ApiModelProperty(value = "计划开始日期")
    @JsonFormat(pattern = DateUtils.DEFAULT_TIME_FORMAT)
    private LocalDate startDate;
    /**
     * 计划结束日期
     */
    @ApiModelProperty(value = "计划结束日期")
    @JsonFormat(pattern = DateUtils.DEFAULT_TIME_FORMAT)
    private LocalDate endDate;
    /**
     * 租户代码
     */
    @ApiModelProperty(value = "租户代码")
    private String tenantCode;


    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
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

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getSumArrivalQty() {
        return sumArrivalQty;
    }

    public void setSumArrivalQty(BigDecimal sumArrivalQty) {
        this.sumArrivalQty = sumArrivalQty;
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

    public BigDecimal getOweQty() {
        return oweQty;
    }

    public void setOweQty(BigDecimal oweQty) {
        this.oweQty = oweQty;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getDeliveryStartDate() {
        return deliveryStartDate;
    }

    public void setDeliveryStartDate(LocalDate deliveryStartDate) {
        this.deliveryStartDate = deliveryStartDate;
    }

    public LocalDate getDeliveryEndDate() {
        return deliveryEndDate;
    }

    public void setDeliveryEndDate(LocalDate deliveryEndDate) {
        this.deliveryEndDate = deliveryEndDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
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

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

}
