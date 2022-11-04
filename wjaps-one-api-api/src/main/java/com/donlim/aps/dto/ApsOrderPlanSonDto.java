package com.donlim.aps.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import com.changhong.sei.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 生产计划表(子件)(ApsOrderPlanSon)DTO类
 *
 * @author sei
 * @since 2022-05-28 10:19:22
 */
@ApiModel(description = "生产计划表(子件)DTO")
public class ApsOrderPlanSonDto extends BaseEntityDto {
    private static final long serialVersionUID = 591272265819275243L;
    /**
     * 订单id
     */
    @ApiModelProperty(value = "订单id")
    private String orderId;
    /**
     * 父生产计划id
     */
    @ApiModelProperty(value = "父生产计划id")
    private String planId;
    /**
     * 生产计划批次号
     */
    @ApiModelProperty(value = "生产计划批次号")
    private Integer planNum;
    /**
     * u9工单号
     */
    @ApiModelProperty(value = "u9工单号")
    private String u9No;
    /**
     * 产品id
     */
    @ApiModelProperty(value = "产品id")
    private Long materialId;


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
     * 完成数量
     */
    @ApiModelProperty(value = "完成数量")
    private BigDecimal hasQty;
    /**
     * 欠数
     */
    @ApiModelProperty(value = "欠数")
    private BigDecimal oweQty;
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
     * 下单日期
     */
    @ApiModelProperty(value = "下单日期")
    @JsonFormat(pattern = DateUtils.DEFAULT_DATE_FORMAT)
    private LocalDate orderDate;
    /**
     * SCM交期
     */
    @ApiModelProperty(value = "SCM交期")
    @JsonFormat(pattern = DateUtils.DEFAULT_DATE_FORMAT)
    private LocalDate scmDeliveryDate;
    /**
     * 开拉日期
     */
    @ApiModelProperty(value = "开拉日期")
    @JsonFormat(pattern = DateUtils.DEFAULT_DATE_FORMAT)
    private LocalDate productionDate;
    /**
     * 标准PC产能
     */
    @ApiModelProperty(value = "标准PC产能")
    private BigDecimal pcStandardQty;
    /**
     * 标准ie产能
     */
    @ApiModelProperty(value = "标准ie产能")
    private BigDecimal ieStandardQty;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 车间id
     */
    @ApiModelProperty(value = "车间id")
    private String workGroupId;
    /**
     * 生产车间
     */
    @ApiModelProperty(value = "生产车间")
    private String workGroupName;
    /**
     * 生产线id
     */
    @ApiModelProperty(value = "生产线id")
    private String lineId;
    /**
     * 生产线
     */
    @ApiModelProperty(value = "生产线")
    private String lineName;
    /**
     * 计划开始日期
     */
    @ApiModelProperty(value = "计划开始日期")
    @JsonFormat(pattern = DateUtils.DEFAULT_DATE_FORMAT)
    private LocalDate startDate;
    /**
     * 计划结束日期
     */
    @ApiModelProperty(value = "计划结束日期")
    @JsonFormat(pattern = DateUtils.DEFAULT_DATE_FORMAT)
    private LocalDate endDate;
    /**
     * 租户代码
     */
    @ApiModelProperty(value = "租户代码")
    private String tenantCode;

    public String getU9No() {
        return u9No;
    }

    public void setU9No(String u9No) {
        this.u9No = u9No;
    }

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

    public Integer getPlanNum() {
        return planNum;
    }

    public void setPlanNum(Integer planNum) {
        this.planNum = planNum;
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
