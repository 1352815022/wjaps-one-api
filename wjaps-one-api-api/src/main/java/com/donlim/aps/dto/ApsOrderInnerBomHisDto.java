package com.donlim.aps.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 订单表内部分解合并历史表(ApsOrderInnerBomHis)DTO类
 *
 * @author sei
 * @since 2022-05-13 16:38:38
 */
@ApiModel(description = "订单表内部分解合并历史表DTO")
public class ApsOrderInnerBomHisDto extends BaseEntityDto {
    private static final long serialVersionUID = 974462710196492261L;
    /**
     * 合并至的订单ID
     */
    @ApiModelProperty(value = "合并至的订单ID")
    private String newId;
    /**
     * 料品id
     */
    @ApiModelProperty(value = "料品id")
    private Long materialId;
    /**
     * 订单数量
     */
    @ApiModelProperty(value = "订单数量")
    private Integer orderQty;
    /**
     * 原订单id
     */
    @ApiModelProperty(value = "原订单id")
    private String originId;
    /**
     * bom用量
     */
    @ApiModelProperty(value = "bom用量")
    private BigDecimal usage;
    /**
     * 租户代码
     */
    @ApiModelProperty(value = "租户代码")
    private String tenantCode;


    public String getNewId() {
        return newId;
    }

    public void setNewId(String newId) {
        this.newId = newId;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public Integer getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(Integer orderQty) {
        this.orderQty = orderQty;
    }

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public BigDecimal getUsage() {
        return usage;
    }

    public void setUsage(BigDecimal usage) {
        this.usage = usage;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

}
