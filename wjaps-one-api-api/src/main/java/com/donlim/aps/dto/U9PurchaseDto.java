package com.donlim.aps.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 采购/委外(U9Purchase)DTO类
 *
 * @author sei
 * @since 2022-07-14 17:26:27
 */
@ApiModel(description = "采购/委外DTO")
public class U9PurchaseDto extends BaseEntityDto {
    private static final long serialVersionUID = 522474411726840333L;
    /**
     * 单号
     */
    @ApiModelProperty(value = "单号")
    private String docNo;
    /**
     * 料id
     */
    @ApiModelProperty(value = "料id")
    private Long materialId;
    /**
     * 品名
     */
    @ApiModelProperty(value = "品名")
    private String materialName;
    /**
     * 料号
     */
    @ApiModelProperty(value = "料号")
    private String materialCode;
    /**
     * 需求数量
     */
    @ApiModelProperty(value = "需求数量")
    private Double reqQty;
    /**
     * 实收数量
     */
    @ApiModelProperty(value = "实收数量")
    private Double receiveQty;
    /**
     * 供应商名称
     */
    @ApiModelProperty(value = "供应商名称")
    private String supplierName;
    /**
     * 送货时间
     */
    @ApiModelProperty(value = "送货时间")
    private Date deliveryDate;
    /**
     * 组织id
     */
    @ApiModelProperty(value = "组织id")
    private Long orgId;
    /**
     * 状态 已核准  2 核准中  1  超额关闭  5  自然关闭  3  开立  0  短缺关闭  4
     */
    @ApiModelProperty(value = "状态已核准2核准中1超额关闭5自然关闭3开立0短缺关闭4")
    private String status;


    private String demandCode;
    /**
     * 租户代码
     */
    @ApiModelProperty(value = "租户代码")
    private String tenantCode;


    public String getDocNo() {
        return docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public Double getReqQty() {
        return reqQty;
    }

    public void setReqQty(Double reqQty) {
        this.reqQty = reqQty;
    }

    public Double getReceiveQty() {
        return receiveQty;
    }

    public void setReceiveQty(Double receiveQty) {
        this.receiveQty = receiveQty;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDemandCode() {
        return demandCode;
    }

    public void setDemandCode(String demandCode) {
        this.demandCode = demandCode;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

}
