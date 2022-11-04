package com.donlim.aps.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 请购单(U9Rp)DTO类
 *
 * @author sei
 * @since 2022-06-14 14:26:35
 */
@ApiModel(description = "请购单DTO")
public class U9RpDto extends BaseEntityDto {
    private static final long serialVersionUID = 283792446637899544L;
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
     * 核准数量
     */
    @ApiModelProperty(value = "核准数量")
    private BigDecimal approveQty;
    /**
     * 已实收数量
     */
    @ApiModelProperty(value = "已实收数量")
    private BigDecimal totalRecievedQty;
    /**
     * 需求数
     */
    @ApiModelProperty(value = "需求数")
    private BigDecimal reqQtyPu;
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
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createBy;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createOn;


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

    public BigDecimal getApproveQty() {
        return approveQty;
    }

    public void setApproveQty(BigDecimal approveQty) {
        this.approveQty = approveQty;
    }

    public BigDecimal getTotalRecievedQty() {
        return totalRecievedQty;
    }

    public void setTotalRecievedQty(BigDecimal totalRecievedQty) {
        this.totalRecievedQty = totalRecievedQty;
    }

    public BigDecimal getReqQtyPu() {
        return reqQtyPu;
    }

    public void setReqQtyPu(BigDecimal reqQtyPu) {
        this.reqQtyPu = reqQtyPu;
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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public LocalDateTime getCreateOn() {
        return createOn;
    }

    public void setCreateOn(LocalDateTime createOn) {
        this.createOn = createOn;
    }

}
