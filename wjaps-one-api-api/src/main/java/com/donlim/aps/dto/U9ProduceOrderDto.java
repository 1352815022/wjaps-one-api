package com.donlim.aps.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

/**
 * 生产单表(U9)(U9ProduceOrder)DTO类
 *
 * @author sei
 * @since 2022-05-18 09:35:36
 */
@ApiModel(description = "生产单表(U9)DTO")
public class U9ProduceOrderDto extends BaseEntityDto {
    private static final long serialVersionUID = 763516567834291128L;
    /**
     * 生产单号
     */
    @ApiModelProperty(value = "生产单号")
    private String docNo;
    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    private String type;
    /**
     * 组织ID
     */
    @ApiModelProperty(value = "组织ID")
    private Long orgId;
    /**
     * 下单日期
     */
    @ApiModelProperty(value = "下单日期")
    private LocalDateTime orderDate;
    /**
     * 料品id
     */
    @ApiModelProperty(value = "料品id")
    private String materialId;
    /**
     * 料号
     */
    @ApiModelProperty(value = "料号")
    private String materialCode;
    /**
     * 料名
     */
    @ApiModelProperty(value = "料名")
    private String materialName;
    /**
     * 客户id
     */
    @ApiModelProperty(value = "客户id")
    private String customerId;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;
    /**
     * 销售单号
     */
    @ApiModelProperty(value = "销售单号")
    private String soId;
    /**
     * 欠入库数
     */
    @ApiModelProperty(value = "欠入库数")
    private Integer oweQty;
    /**
     * 订单数量
     */
    @ApiModelProperty(value = "订单数量")
    private Integer qty;
    /**
     * 已入库数
     */
    @ApiModelProperty(value = "已入库数")
    private Integer totalCompleteQty;
    /**
     * U9创建人
     */
    @ApiModelProperty(value = "U9创建人")
    private String createBy;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSoId() {
        return soId;
    }

    public void setSoId(String soId) {
        this.soId = soId;
    }

    public Integer getOweQty() {
        return oweQty;
    }

    public void setOweQty(Integer oweQty) {
        this.oweQty = oweQty;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getTotalCompleteQty() {
        return totalCompleteQty;
    }

    public void setTotalCompleteQty(Integer totalCompleteQty) {
        this.totalCompleteQty = totalCompleteQty;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

}
