package com.donlim.aps.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 工艺表(ApsProcessGroup)DTO类
 *
 * @author sei
 * @since 2022-04-20 16:11:42
 */
@ApiModel(description = "工艺表DTO")
public class ApsProcessGroupDto extends BaseEntityDto {
    private static final long serialVersionUID = 365361308833553833L;
    /**
     * 工艺
     */
    @ApiModelProperty(value = "工艺")
    private String processGroupName;
    /**
     * 组织id
     */
    @ApiModelProperty(value = "组织id")
    private String organizeId;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;
    /**
     * 备注1
     */
    @ApiModelProperty(value = "备注1")
    private String remark;
    /**
     * 按区域排产
     */
    @ApiModelProperty(value = "按区域排产")
    private Boolean byRegion;
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
    private Boolean enabledMark;
    /**
     * 租户代码
     */
    @ApiModelProperty(value = "租户代码")
    private String tenantCode;


    public String getProcessGroupName() {
        return processGroupName;
    }

    public void setProcessGroupName(String processGroupName) {
        this.processGroupName = processGroupName;
    }

    public String getOrganizeId() {
        return organizeId;
    }

    public void setOrganizeId(String organizeId) {
        this.organizeId = organizeId;
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

    public Boolean getByRegion() {
        return byRegion;
    }

    public void setByRegion(Boolean byRegion) {
        this.byRegion = byRegion;
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

    public Boolean getEnabledMark() {
        return enabledMark;
    }

    public void setEnabledMark(Boolean enabledMark) {
        this.enabledMark = enabledMark;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

}
