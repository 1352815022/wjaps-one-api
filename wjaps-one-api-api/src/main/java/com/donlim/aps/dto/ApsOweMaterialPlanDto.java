package com.donlim.aps.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 欠料计划表(ApsOweMaterialPlan)DTO类
 *
 * @author sei
 * @since 2022-06-14 11:53:37
 */
@ApiModel(description = "欠料计划表DTO")
public class ApsOweMaterialPlanDto extends BaseEntityDto {
    private static final long serialVersionUID = 212790510492276221L;
    /**
     * 欠料汇总表id
     */
    @ApiModelProperty(value = "欠料汇总表id")
    private String summaryId;
    /**
     * 单号
     */
    @ApiModelProperty(value = "单号")
    private String orderNo;
    /**
     * 生产部门/供应商
     */
    @ApiModelProperty(value = "生产部门/供应商")
    private String workGroup;
    /**
     * 产品id(原料)
     */
    @ApiModelProperty(value = "产品id(原料)")
    private Long originMaterial;
    /**
     * 产品id(工单)
     */
    @ApiModelProperty(value = "产品id(工单)")
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
     * 单位
     */
    @ApiModelProperty(value = "单位")
    private String unit;
    /**
     * 排产数
     */
    @ApiModelProperty(value = "排产数")
    private BigDecimal planQty;
    /**
     * 已领数量
     */
    @ApiModelProperty(value = "已领数量")
    private BigDecimal pullQty;
    /**
     * 需求数量
     */
    @ApiModelProperty(value = "需求数量")
    private BigDecimal requireQty;
    /**
     * 欠发数量
     */
    @ApiModelProperty(value = "欠发数量")
    private BigDecimal oweQty;
    /**
     * 租户代码
     */
    @ApiModelProperty(value = "租户代码")
    private String tenantCode;


    public String getSummaryId() {
        return summaryId;
    }

    public void setSummaryId(String summaryId) {
        this.summaryId = summaryId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getWorkGroup() {
        return workGroup;
    }

    public void setWorkGroup(String workGroup) {
        this.workGroup = workGroup;
    }

    public Long getOriginMaterial() {
        return originMaterial;
    }

    public void setOriginMaterial(Long originMaterial) {
        this.originMaterial = originMaterial;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getPlanQty() {
        return planQty;
    }

    public void setPlanQty(BigDecimal planQty) {
        this.planQty = planQty;
    }

    public BigDecimal getPullQty() {
        return pullQty;
    }

    public void setPullQty(BigDecimal pullQty) {
        this.pullQty = pullQty;
    }

    public BigDecimal getRequireQty() {
        return requireQty;
    }

    public void setRequireQty(BigDecimal requireQty) {
        this.requireQty = requireQty;
    }

    public BigDecimal getOweQty() {
        return oweQty;
    }

    public void setOweQty(BigDecimal oweQty) {
        this.oweQty = oweQty;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

}
