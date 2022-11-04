package com.donlim.aps.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * (OneApsPlanData)DTO类
 *
 * @author sei
 * @since 2022-07-27 18:02:54
 */
@ApiModel(description = "DTO")
public class OneApsPlanDataDto extends BaseEntityDto {
    private static final long serialVersionUID = 483011948916503038L;
    /**
     * 需求分类号
     */
    @ApiModelProperty(value = "需求分类号")
    private String orderNo;
    /**
     * 生产日期
     */
    @ApiModelProperty(value = "生产日期")
    private Date date;
    /**
     * 生产数量
     */
    @ApiModelProperty(value = "生产数量")
    private Integer qty;
    /**
     * 租户代码
     */
    @ApiModelProperty(value = "租户代码")
    private String tenantCode;


    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

}
