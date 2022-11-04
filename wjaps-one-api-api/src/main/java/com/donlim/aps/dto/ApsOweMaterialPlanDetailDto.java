package com.donlim.aps.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import com.changhong.sei.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 欠料计划明细表(ApsOweMaterialPlanDetail)DTO类
 *
 * @author sei
 * @since 2022-06-14 11:53:38
 */
@ApiModel(description = "欠料计划明细表DTO")
public class ApsOweMaterialPlanDetailDto extends BaseEntityDto {
    private static final long serialVersionUID = 673321703071359521L;
    /**
     * 父id
     */
    @ApiModelProperty(value = "父id")
    private String parentId;
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


    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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
