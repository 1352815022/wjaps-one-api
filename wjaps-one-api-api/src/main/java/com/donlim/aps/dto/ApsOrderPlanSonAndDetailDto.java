package com.donlim.aps.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @ClassName ApsOrderPlanSonAndDetailDto
 * @Description TODO
 * @Author p09835
 * @Date 2022/5/30 15:58
 **/
public class ApsOrderPlanSonAndDetailDto extends ApsOrderPlanSonDto {

    private static final long serialVersionUID = -6057917891335392261L;
    /**
     * 排产明细
     */
    @ApiModelProperty(value = "排产明细")
    private Map<String,BigDecimal> details;

    public Map<String, BigDecimal> getDetails() {
        return details;
    }

    public void setDetails(Map<String, BigDecimal> details) {
        this.details = details;
    }
}
