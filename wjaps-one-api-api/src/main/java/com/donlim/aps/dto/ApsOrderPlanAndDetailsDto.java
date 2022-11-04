package com.donlim.aps.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @ClassName ApsOrderPlanAndDetailsDto
 * @Description 生产计划保存实体类
 * @Author p09835
 * @Date 2022/5/17 16:28
 **/
public class ApsOrderPlanAndDetailsDto extends ApsOrderPlanDto implements Serializable {

    private static final long serialVersionUID = 2906340194857139996L;

    @ApiModelProperty(value = "订单编号")
    private String orderNo ;

    /**
     * 排产明细
     */
    @ApiModelProperty(value = "排产明细")
    private Map<String,BigDecimal> details;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Map<String, BigDecimal> getDetails() {
        return details;
    }

    public void setDetails(Map<String, BigDecimal> details) {
        this.details = details;
    }
}
