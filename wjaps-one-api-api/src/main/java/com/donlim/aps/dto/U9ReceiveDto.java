package com.donlim.aps.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * (U9Receive)DTO类
 *
 * @author sei
 * @since 2022-06-14 14:26:36
 */
@ApiModel(description = "DTO")
public class U9ReceiveDto extends BaseEntityDto {
    private static final long serialVersionUID = -74475188421480745L;
    /**
     * 单号
     */
    @ApiModelProperty(value = "单号")
    private String docNo;
    /**
     * 采购单号
     */
    @ApiModelProperty(value = "采购单号")
    private String srcDocNo;
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
     * 已实收数量
     */
    @ApiModelProperty(value = "已实收数量")
    private BigDecimal arriveQty;
    /**
     * 组织id
     */
    @ApiModelProperty(value = "组织id")
    private Long orgId;
    /**
     * 状态   开立0，审核中3，入库确认4，业务关闭5，检验完成2
     */
    @ApiModelProperty(value = "状态开立0，审核中3，入库确认4，业务关闭5，检验完成2")
    private String status;


    private String demandCode;
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

    public String getSrcDocNo() {
        return srcDocNo;
    }

    public void setSrcDocNo(String srcDocNo) {
        this.srcDocNo = srcDocNo;
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

    public BigDecimal getArriveQty() {
        return arriveQty;
    }

    public void setArriveQty(BigDecimal arriveQty) {
        this.arriveQty = arriveQty;
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

    public LocalDateTime getCreateOn() {
        return createOn;
    }

    public void setCreateOn(LocalDateTime createOn) {
        this.createOn = createOn;
    }

}
