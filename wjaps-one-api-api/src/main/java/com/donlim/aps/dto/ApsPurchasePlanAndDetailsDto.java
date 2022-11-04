package com.donlim.aps.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @ClassName ApsPurchasePlanAndDetailsDto
 * @Description 委外计划保存实体类
 * @Author p09835
 * @Date 2022/5/23 9:58
 **/
public class ApsPurchasePlanAndDetailsDto extends ApsPurchasePlanDto implements Serializable {
    private static final long serialVersionUID = -7684386991847873090L;

    private Map<String,BigDecimal> details;

    public Map<String, BigDecimal> getDetails() {
        return details;
    }

    public void setDetails(Map<String, BigDecimal> details) {
        this.details = details;
    }
}
