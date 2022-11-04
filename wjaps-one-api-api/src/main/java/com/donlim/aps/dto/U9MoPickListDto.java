package com.donlim.aps.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * (U9MoPickList)DTO类
 *
 * @author sei
 * @since 2022-06-14 14:26:36
 */
@ApiModel(description = "DTO")
public class U9MoPickListDto extends BaseEntityDto {
    private static final long serialVersionUID = 571932432790707731L;
    /**
     * 工单id
     */
    @ApiModelProperty(value = "工单id")
    private Long moId;
    /**
     * 物料id
     */
    @ApiModelProperty(value = "物料id")
    private Long materialId;
    /**
     * 实际用量
     */
    @ApiModelProperty(value = "实际用量")
    private BigDecimal actualReqQty;
    /**
     * bom用量
     */
    @ApiModelProperty(value = "bom用量")
    private BigDecimal bomReqQty;
    /**
     * 标准用量
     */
    @ApiModelProperty(value = "标准用量")
    private BigDecimal stdReqQty;
    /**
     * 是否虚拟件
     */
    @ApiModelProperty(value = "是否虚拟件")
    private Object phantomPartFlag;
    /**
     * 每装配件数量
     */
    @ApiModelProperty(value = "每装配件数量")
    private BigDecimal qpa;
    /**
     * 租户代码
     */
    @ApiModelProperty(value = "租户代码")
    private String tenantCode;
    /**
     * U9组织id
     */
    @ApiModelProperty(value = "U9组织id")
    private Long orgId;


    public Long getMoId() {
        return moId;
    }

    public void setMoId(Long moId) {
        this.moId = moId;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public BigDecimal getActualReqQty() {
        return actualReqQty;
    }

    public void setActualReqQty(BigDecimal actualReqQty) {
        this.actualReqQty = actualReqQty;
    }

    public BigDecimal getBomReqQty() {
        return bomReqQty;
    }

    public void setBomReqQty(BigDecimal bomReqQty) {
        this.bomReqQty = bomReqQty;
    }

    public BigDecimal getStdReqQty() {
        return stdReqQty;
    }

    public void setStdReqQty(BigDecimal stdReqQty) {
        this.stdReqQty = stdReqQty;
    }

    public Object getPhantomPartFlag() {
        return phantomPartFlag;
    }

    public void setPhantomPartFlag(Object phantomPartFlag) {
        this.phantomPartFlag = phantomPartFlag;
    }

    public BigDecimal getQpa() {
        return qpa;
    }

    public void setQpa(BigDecimal qpa) {
        this.qpa = qpa;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

}
