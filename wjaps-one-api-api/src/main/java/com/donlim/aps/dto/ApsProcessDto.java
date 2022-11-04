package com.donlim.aps.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 工序表(ApsProcess)DTO类
 *
 * @author sei
 * @since 2022-04-19 14:13:40
 */
@ApiModel(description = "工序表DTO")
public class ApsProcessDto extends BaseEntityDto {
    private static final long serialVersionUID = 262523327957559814L;
    /**
     * 工序代码
     */
    @ApiModelProperty(value = "工序代码")
    private String processCode;
    /**
     * 工序名称
     */
    @ApiModelProperty(value = "工序名称")
    private String processName;
    /**
     * 排产类型
     */
    @ApiModelProperty(value = "排产类型")
    private String schedulingType;
    /**
     * 工段
     */
    @ApiModelProperty(value = "工段")
    private String processPartName;
    /**
     * 所属工段
     */
    @ApiModelProperty(value = "所属工段")
    private String workSection;
    /**
     * 工序描述
     */
    @ApiModelProperty(value = "工序描述")
    private String description;
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


    public String getProcessCode() {
        return processCode;
    }

    public void setProcessCode(String processCode) {
        this.processCode = processCode;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getSchedulingType() {
        return schedulingType;
    }

    public void setSchedulingType(String schedulingType) {
        this.schedulingType = schedulingType;
    }

    public String getProcessPartName() {
        return processPartName;
    }

    public void setProcessPartName(String processPartName) {
        this.processPartName = processPartName;
    }

    public String getWorkSection() {
        return workSection;
    }

    public void setWorkSection(String workSection) {
        this.workSection = workSection;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
