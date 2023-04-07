package com.donlim.aps.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 订单表(内部)扩展表(ApsOrderExt)DTO类
 *
 * @author sei
 * @since 2023-04-06 08:29:20
 */
@ApiModel(description = "订单表(内部)扩展表DTO")
public class ApsOrderExtDto extends BaseEntityDto {
    private static final long serialVersionUID = -78294868471090588L;
    /**
     * 工单号
     */
    @ApiModelProperty(value = "工单号")
    private String orderNo;
    /**
     * 已完成数量
     */
    @ApiModelProperty(value = "已完成数量")
    private Double finishQty;


    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Double getFinishQty() {
        return finishQty;
    }

    public void setFinishQty(Double finishQty) {
        this.finishQty = finishQty;
    }

}