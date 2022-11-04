package com.donlim.aps.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * scm送货明细(ScmXbDeliveryPlan)DTO类
 *
 * @author sei
 * @since 2022-06-20 11:15:03
 */
@ApiModel(description = "scm送货明细DTO")
public class ScmXbDeliveryPlanDto extends BaseEntityDto {
    private static final long serialVersionUID = 669469499533185751L;
    /**
     * 上级id
     */
    @ApiModelProperty(value = "上级id")
    private String parentId;
    /**
     * 送货数量
     */
    @ApiModelProperty(value = "送货数量")
    private BigDecimal qty;
    /**
     * 送货日期
     */
    @ApiModelProperty(value = "送货日期")
    private LocalDate deliveryDate;
    /**
     * 租户代码
     */
    @ApiModelProperty(value = "租户代码")
    private String tenantCode;


    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

}
