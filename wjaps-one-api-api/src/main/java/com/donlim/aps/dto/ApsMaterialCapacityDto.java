package com.donlim.aps.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * (ApsMaterialCapacity)DTO类
 *
 * @author sei
 * @since 2022-05-09 11:17:15
 */
@ApiModel(description = "DTO")
public class ApsMaterialCapacityDto extends BaseEntityDto {
    private static final long serialVersionUID = 558462208616155880L;
    /**
     * u9物料id
     */
    @ApiModelProperty(value = "u9物料id")
    private Long materialId;
    /**
     * u9物料编码
     */
    @ApiModelProperty(value = "u9物料编码")
    private String materialCode;
    /**
     * u9物料名称
     */
    @ApiModelProperty(value = "u9物料名称")
    private String materialName;
    /**
     * u9物料规格
     */
    @ApiModelProperty(value = "u9物料规格")
    private String materialDesc;
    /**
     * 标准产能
     */
    @ApiModelProperty(value = "标准产能")
    private BigDecimal standardQty;
    /**
     * 车间id
     */
    @ApiModelProperty(value = "车间id")
    private String workGroupId;
    /**
     * 车间编码
     */
    @ApiModelProperty(value = "车间编码")
    private String workGroupCode;
    /**
     * 车间名
     */
    @ApiModelProperty(value = "车间名")
    private String workGroupName;
    /**
     * 班组id
     */
    @ApiModelProperty(value = "班组id")
    private String workLineId;
    /**
     * 班组编码
     */
    @ApiModelProperty(value = "班组编码")
    private String workLineCode;
    /**
     * 班组名
     */
    @ApiModelProperty(value = "班组名")
    private String workLineName;
    /**
     * 是否冻结
     */
    @ApiModelProperty(value = "是否冻结")
    private Boolean frozen;
    /**
     * 租户代码
     */
    @ApiModelProperty(value = "租户代码")
    private String tenantCode;


    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
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

    public String getMaterialDesc() {
        return materialDesc;
    }

    public void setMaterialDesc(String materialDesc) {
        this.materialDesc = materialDesc;
    }

    public BigDecimal getStandardQty() {
        return standardQty;
    }

    public void setStandardQty(BigDecimal standardQty) {
        this.standardQty = standardQty;
    }

    public String getWorkGroupId() {
        return workGroupId;
    }

    public void setWorkGroupId(String workGroupId) {
        this.workGroupId = workGroupId;
    }

    public String getWorkGroupCode() {
        return workGroupCode;
    }

    public void setWorkGroupCode(String workGroupCode) {
        this.workGroupCode = workGroupCode;
    }

    public String getWorkGroupName() {
        return workGroupName;
    }

    public void setWorkGroupName(String workGroupName) {
        this.workGroupName = workGroupName;
    }

    public String getWorkLineId() {
        return workLineId;
    }

    public void setWorkLineId(String workLineId) {
        this.workLineId = workLineId;
    }

    public String getWorkLineCode() {
        return workLineCode;
    }

    public void setWorkLineCode(String workLineCode) {
        this.workLineCode = workLineCode;
    }

    public String getWorkLineName() {
        return workLineName;
    }

    public void setWorkLineName(String workLineName) {
        this.workLineName = workLineName;
    }

    public Boolean getFrozen() {
        return frozen;
    }

    public void setFrozen(Boolean frozen) {
        this.frozen = frozen;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

}
