package com.donlim.aps.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 工序料品配置(ApsProcessMaterial)DTO类
 *
 * @author sei
 * @since 2022-06-08 10:53:21
 */
@ApiModel(description = "工序料品配置DTO")
public class ApsProcessMaterialDto extends BaseEntityDto {
    private static final long serialVersionUID = 138356779893541468L;
    /**
     * 料品id
     */
    @ApiModelProperty(value = "料品id")
    private Long materialId;
    /**
     * 工序id
     */
    @ApiModelProperty(value = "工序id")
    private String processId;

    @ApiModelProperty(value = "工序编码")
    private String apsProcessProcessCode;

    @ApiModelProperty(value = "工序名称")
    private String apsProcessProcessName;
    /**
     * 组织id
     */
    @ApiModelProperty(value = "组织id")
    private String organizeId;

    @ApiModelProperty(value = "组织编码")
    private String apsOrganizeCode;

    @ApiModelProperty(value = "组织名称")
    private String apsOrganizeName;
    /**
     * 小时产能
     */
    @ApiModelProperty(value = "小时产能")
    private BigDecimal capacity;
    /**
     * 标准人力
     */
    @ApiModelProperty(value = "标准人力")
    private Integer standardPeoples;
    /**
     * 启用状态
     */
    @ApiModelProperty(value = "启用状态")
    private Boolean frozen;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sortCode;
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

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getOrganizeId() {
        return organizeId;
    }

    public void setOrganizeId(String organizeId) {
        this.organizeId = organizeId;
    }

    public BigDecimal getCapacity() {
        return capacity;
    }

    public void setCapacity(BigDecimal capacity) {
        this.capacity = capacity;
    }

    public Integer getStandardPeoples() {
        return standardPeoples;
    }

    public void setStandardPeoples(Integer standardPeoples) {
        this.standardPeoples = standardPeoples;
    }

    public Boolean getFrozen() {
        return frozen;
    }

    public void setFrozen(Boolean frozen) {
        this.frozen = frozen;
    }

    public Integer getSortCode() {
        return sortCode;
    }

    public void setSortCode(Integer sortCode) {
        this.sortCode = sortCode;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

    public String getApsProcessProcessCode() {
        return apsProcessProcessCode;
    }

    public void setApsProcessProcessCode(String apsProcessProcessCode) {
        this.apsProcessProcessCode = apsProcessProcessCode;
    }

    public String getApsProcessProcessName() {
        return apsProcessProcessName;
    }

    public void setApsProcessProcessName(String apsProcessProcessName) {
        this.apsProcessProcessName = apsProcessProcessName;
    }

    public String getApsOrganizeCode() {
        return apsOrganizeCode;
    }

    public void setApsOrganizeCode(String apsOrganizeCode) {
        this.apsOrganizeCode = apsOrganizeCode;
    }

    public String getApsOrganizeName() {
        return apsOrganizeName;
    }

    public void setApsOrganizeName(String apsOrganizeName) {
        this.apsOrganizeName = apsOrganizeName;
    }
}
