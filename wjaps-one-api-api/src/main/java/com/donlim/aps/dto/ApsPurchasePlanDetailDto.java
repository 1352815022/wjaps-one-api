package com.donlim.aps.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 采购计划明细表(ApsPurchasePlanDetail)DTO类
 *
 * @author sei
 * @since 2022-05-23 08:20:14
 */
@ApiModel(description = "采购计划明细表DTO")
public class ApsPurchasePlanDetailDto extends BaseEntityDto {
    private static final long serialVersionUID = -75784060511912955L;
    /**
     * 排产id
     */
    @ApiModelProperty(value = "排产id")
    private String planId;
    /**
     * 计划日期
     */
    @ApiModelProperty(value = "计划日期")
    private LocalDate planDate;
    /**
     * 排产数量
     */
    @ApiModelProperty(value = "排产数量")
    private BigDecimal planQty;
    /**
     * 租户代码
     */
    @ApiModelProperty(value = "租户代码")
    private String tenantCode;


    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public LocalDate getPlanDate() {
        return planDate;
    }

    public void setPlanDate(LocalDate planDate) {
        this.planDate = planDate;
    }

    public BigDecimal getPlanQty() {
        return planQty;
    }

    public void setPlanQty(BigDecimal planQty) {
        this.planQty = planQty;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

}
