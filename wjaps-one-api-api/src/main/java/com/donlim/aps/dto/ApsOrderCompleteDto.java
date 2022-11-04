package com.donlim.aps.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 订单齐套(ApsOrderComplete)DTO类
 *
 * @author sei
 * @since 2022-07-13 14:01:51
 */
@ApiModel(description = "订单齐套DTO")
public class ApsOrderCompleteDto extends BaseEntityDto {
    private static final long serialVersionUID = -65947104184938215L;
    /**
     * 公司名
     */
    @ApiModelProperty(value = "公司名")
    private String company;
    /**
     * 需求分类号
     */
    @ApiModelProperty(value = "需求分类号")
    private String orderNo;
    /**
     * 产品型号
     */
    @ApiModelProperty(value = "产品型号")
    private String productModel;
    /**
     * 订单个数
     */
    @ApiModelProperty(value = "订单个数")
    private Integer orderNum;
    /**
     * 订单齐套个数
     */
    @ApiModelProperty(value = "订单齐套个数")
    private Integer completeNum;
    /**
     * 齐套率
     */
    @ApiModelProperty(value = "齐套率")
    private String completePercent;
    /**
     * 租户代码
     */
    @ApiModelProperty(value = "租户代码")
    private String tenantCode;


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getCompleteNum() {
        return completeNum;
    }

    public void setCompleteNum(Integer completeNum) {
        this.completeNum = completeNum;
    }

    public String getCompletePercent() {
        return completePercent;
    }

    public void setCompletePercent(String completePercent) {
        this.completePercent = completePercent;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

}
