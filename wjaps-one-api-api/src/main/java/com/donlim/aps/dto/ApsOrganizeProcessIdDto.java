package com.donlim.aps.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 组织工艺配置(ApsOrganizeProcessId)DTO类
 *
 * @author sei
 * @since 2022-04-26 09:22:22
 */
@ApiModel(description = "组织工艺配置DTO")
public class ApsOrganizeProcessIdDto extends BaseEntityDto {
    private static final long serialVersionUID = -54359913755704575L;

    /**
     * 工艺
     */
    @ApiModelProperty(value = "工艺Dto")
    private ApsProcessDto apsProcessDto;

    /**
     * 组织
     */
    @ApiModelProperty(value = "组织Dto")
    private ApsOrganizeDto apsOrganizeDto;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 适用组织
     */
    @ApiModelProperty(value = "适用组织")
    private String companyId;

    /**
     * 适用部门
     */
    @ApiModelProperty(value = "适用部门")
    private String deptId;

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

    public ApsProcessDto getApsProcessDto() {
        return apsProcessDto;
    }

    public void setApsProcessDto(ApsProcessDto apsProcessDto) {
        this.apsProcessDto = apsProcessDto;
    }

    public ApsOrganizeDto getApsOrganizeDto() {
        return apsOrganizeDto;
    }

    public void setApsOrganizeDto(ApsOrganizeDto apsOrganizeDto) {
        this.apsOrganizeDto = apsOrganizeDto;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
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

}
