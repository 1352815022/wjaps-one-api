package com.donlim.aps.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import com.changhong.sei.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * (U9Stock)DTO类
 *
 * @author sei
 * @since 2022-05-13 09:34:29
 */
@ApiModel(description = "DTO")
public class U9StockDto extends BaseEntityDto {
    private static final long serialVersionUID = -65335921078763596L;
    /**
     * 组织ID
     */
    @ApiModelProperty(value = "组织ID")
    private Long orgId;
    /**
     * 物料ID
     */
    @ApiModelProperty(value = "物料ID")
    private Long materialId;
    /**
     * 物料编码
     */
    @ApiModelProperty(value = "物料编码")
    private String materialCode;
    /**
     * 物料名称
     */
    @ApiModelProperty(value = "物料名称")
    private String materialName;
    /**
     * 物料规格
     */
    @ApiModelProperty(value = "物料规格")
    private String materialDesc;
    /**
     * 库存可用量
     */
    @ApiModelProperty(value = "库存可用量")
    private BigDecimal storeQty;
    /**
     * 预留数量
     */
    @ApiModelProperty(value = "预留数量")
    private BigDecimal reserveQty;
    /**
     * 实际库存
     */
    @ApiModelProperty(value = "实际库存")
    private BigDecimal actualQty;
    /**
     * 单位
     */
    @ApiModelProperty(value = "单位")
    private String unit;
    /**
     * 仓库编码
     */
    @ApiModelProperty(value = "仓库编码")
    private String whName;
    /**
     * 同步时间
     */
    @ApiModelProperty(value = "同步时间")
    @JsonFormat(pattern = DateUtils.DEFAULT_TIME_FORMAT)
    private LocalDateTime syncTime;
    /**
     * 租户代码
     */
    @ApiModelProperty(value = "租户代码")
    private String tenantCode;


    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
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

    public String getMaterialDesc() {
        return materialDesc;
    }

    public void setMaterialDesc(String materialDesc) {
        this.materialDesc = materialDesc;
    }

    public BigDecimal getStoreQty() {
        return storeQty;
    }

    public void setStoreQty(BigDecimal storeQty) {
        this.storeQty = storeQty;
    }

    public BigDecimal getReserveQty() {
        return reserveQty;
    }

    public void setReserveQty(BigDecimal reserveQty) {
        this.reserveQty = reserveQty;
    }

    public BigDecimal getActualQty() {
        return actualQty;
    }

    public void setActualQty(BigDecimal actualQty) {
        this.actualQty = actualQty;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getWhName() {
        return whName;
    }

    public void setWhName(String whName) {
        this.whName = whName;
    }

    public LocalDateTime getSyncTime() {
        return syncTime;
    }

    public void setSyncTime(LocalDateTime syncTime) {
        this.syncTime = syncTime;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

}
