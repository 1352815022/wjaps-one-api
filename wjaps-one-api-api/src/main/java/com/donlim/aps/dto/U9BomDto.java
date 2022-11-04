package com.donlim.aps.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 料品表bom(U9Bom)DTO类
 *
 * @author sei
 * @since 2022-05-12 14:58:08
 */
@ApiModel(description = "料品表bomDTO")
public class U9BomDto  {
    private static final long serialVersionUID = 721374591049781278L;
    /**
     * 料品id
     */
    @ApiModelProperty(value = "料品id")
    private Long materialId;
    /**
     * 母件id
     */
    @ApiModelProperty(value = "母件id")
    private Long masterId;
    /**
     * 组织id
     */
    @ApiModelProperty(value = "组织id")
    private Long orgId;
    /**
     * 用量
     */
    @ApiModelProperty(value = "用量")
    private BigDecimal qty;
    /**
     * 母料用量
     */
    @ApiModelProperty(value = "母料用量")
    private BigDecimal masterQty;
    /**
     * 不良率
     */
    @ApiModelProperty(value = "不良率")
    private Double badRate;
    /**
     * 工序
     */
    @ApiModelProperty(value = "工序")
    private String processGroupInfoId;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 启用状态
     */
    @ApiModelProperty(value = "启用状态")
    private Boolean frozen;
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

    public Long getMasterId() {
        return masterId;
    }

    public void setMasterId(Long masterId) {
        this.masterId = masterId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public BigDecimal getMasterQty() {
        return masterQty;
    }

    public void setMasterQty(BigDecimal masterQty) {
        this.masterQty = masterQty;
    }

    public Double getBadRate() {
        return badRate;
    }

    public void setBadRate(Double badRate) {
        this.badRate = badRate;
    }

    public String getProcessGroupInfoId() {
        return processGroupInfoId;
    }

    public void setProcessGroupInfoId(String processGroupInfoId) {
        this.processGroupInfoId = processGroupInfoId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getFrozen() {
        return frozen;
    }

    public void setFrozen(Boolean frozen) {
        this.frozen = frozen;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

}
