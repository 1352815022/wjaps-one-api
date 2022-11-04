package com.donlim.aps.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 欠料汇总表(ApsOweMaterialSummary)DTO类
 *
 * @author sei
 * @since 2022-06-14 11:52:46
 */
@ApiModel(description = "欠料汇总表DTO")
public class ApsOweMaterialSummaryDto extends BaseEntityDto {
    private static final long serialVersionUID = -23784953383509562L;
    /**
     * 产品id(原料)
     */
    @ApiModelProperty(value = "产品id(原料)")
    private Long materialId;


    private String materialCode;
    /**
     * 料品名(原料)
     */
    @ApiModelProperty(value = "料品名(原料)")
    private String materialName;
    /**
     * 料规格
     */
    @ApiModelProperty(value = "料规格")
    private String materialSpec;
    /**
     * 料分类/属性
     */
    @ApiModelProperty(value = "料分类/属性")
    private String materialType;
    /**
     * 库存数量(计算时)
     */
    @ApiModelProperty(value = "库存数量(计算时)")
    private BigDecimal stockQty;
    /**
     * 暂收
     */
    @ApiModelProperty(value = "暂收")
    private BigDecimal tempReceiveQty;
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
     * 超欠数量
     */
    @ApiModelProperty(value = "超欠数量")
    private BigDecimal beyondQty;
    /**
     * 请购数量
     */
    @ApiModelProperty(value = "请购数量")
    private BigDecimal poQty;
    /**
     * 欠料日期
     */
    @ApiModelProperty(value = "欠料日期")
    private LocalDateTime oweDate;
    /**
     * 累计排产
     */
    @ApiModelProperty(value = "累计排产")
    private BigDecimal sumPlanQty;
    /**
     * 租户代码
     */
    @ApiModelProperty(value = "租户代码")
    private String tenantCode;


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

    public BigDecimal getStockQty() {
        return stockQty;
    }

    public void setStockQty(BigDecimal stockQty) {
        this.stockQty = stockQty;
    }

    public BigDecimal getTempReceiveQty() {
        return tempReceiveQty;
    }

    public void setTempReceiveQty(BigDecimal tempReceiveQty) {
        this.tempReceiveQty = tempReceiveQty;
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

    public BigDecimal getBeyondQty() {
        return beyondQty;
    }

    public void setBeyondQty(BigDecimal beyondQty) {
        this.beyondQty = beyondQty;
    }

    public BigDecimal getPoQty() {
        return poQty;
    }

    public void setPoQty(BigDecimal poQty) {
        this.poQty = poQty;
    }

    public LocalDateTime getOweDate() {
        return oweDate;
    }

    public void setOweDate(LocalDateTime oweDate) {
        this.oweDate = oweDate;
    }

    public BigDecimal getSumPlanQty() {
        return sumPlanQty;
    }

    public void setSumPlanQty(BigDecimal sumPlanQty) {
        this.sumPlanQty = sumPlanQty;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

}
