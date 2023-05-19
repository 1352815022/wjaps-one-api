package com.donlim.aps.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * (ApsDayReport)DTO类
 *
 * @author sei
 * @since 2023-05-19 16:17:08
 */
@ApiModel(description = "DTO")
public class ApsDayReportDto extends BaseEntityDto {
    private static final long serialVersionUID = -22135606811277139L;
    /**
     * 日期
     */
    @ApiModelProperty(value = "日期")
    private Date date;
    /**
     * 计划数
     */
    @ApiModelProperty(value = "计划数")
    private Integer planQty;
    /**
     * 完工数
     */
    @ApiModelProperty(value = "完工数")
    private Integer finishQty;
    /**
     * 未排数
     */
    @ApiModelProperty(value = "未排数")
    private Integer noPlanQty;
    /**
     * 达成率
     */
    @ApiModelProperty(value = "达成率")
    private String planRate;
    /**
     * 租户代码
     */
    @ApiModelProperty(value = "租户代码")
    private String tenantCode;


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getPlanQty() {
        return planQty;
    }

    public void setPlanQty(Integer planQty) {
        this.planQty = planQty;
    }

    public Integer getFinishQty() {
        return finishQty;
    }

    public void setFinishQty(Integer finishQty) {
        this.finishQty = finishQty;
    }

    public Integer getNoPlanQty() {
        return noPlanQty;
    }

    public void setNoPlanQty(Integer noPlanQty) {
        this.noPlanQty = noPlanQty;
    }

    public String getPlanRate() {
        return planRate;
    }

    public void setPlanRate(String planRate) {
        this.planRate = planRate;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

}
