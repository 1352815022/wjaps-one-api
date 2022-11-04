package com.donlim.aps.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import com.changhong.sei.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 生产计划明细表(子件)(ApsOrderPlanSonDetail)DTO类
 *
 * @author sei
 * @since 2022-05-28 10:20:30
 */
@ApiModel(description = "生产计划明细表(子件)DTO")
public class ApsOrderPlanSonDetailDto extends BaseEntityDto {
    private static final long serialVersionUID = 180762106913998087L;
    /**
     * 排产id
     */
    @ApiModelProperty(value = "排产id")
    private String planId;
    /**
     * 计划日期
     */
    @ApiModelProperty(value = "计划日期")
    @JsonFormat(pattern = DateUtils.DEFAULT_DATE_FORMAT)
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
