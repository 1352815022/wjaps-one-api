package com.donlim.aps.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 工艺工序关连表(ApsProcessGroupInfo)DTO类
 *
 * @author sei
 * @since 2022-04-20 16:11:45
 */
@ApiModel(description = "工艺工序关连表DTO")
public class ApsProcessGroupInfoDto extends BaseEntityDto  {
    private static final long serialVersionUID = 841774615065105288L;

    /**
     * 工序Dto
     */
    @ApiModelProperty(value = "工序Dto")
    private ApsProcessDto apsProcessDto;

    /**
     * 工艺Dto
     */
    @ApiModelProperty(value = "工艺Dto")
    private ApsProcessGroupDto apsProcessGroupDto;
    /**
     * 是否排产
     */
    @ApiModelProperty(value = "是否排产")
    private Boolean schedulingFlag;
    /**
     * 自动后工序关联
     */
    @ApiModelProperty(value = "自动后工序关联")
    private String postProcessId;
    /**
     * 是否采集
     */
    @ApiModelProperty(value = "是否采集")
    private Boolean acquisitionFlag;
    /**
     * 采集次数
     */
    @ApiModelProperty(value = "采集次数")
    private Integer acquisitionTimes;
    /**
     * 是否报产
     */
    @ApiModelProperty(value = "是否报产")
    private Boolean productionFlag;
    /**
     * 记录报产人
     */
    @ApiModelProperty(value = "记录报产人")
    private Boolean personFlag;
    /**
     * 优先级
     */
    @ApiModelProperty(value = "优先级")
    private Integer processPriority;
    /**
     * 报产来源
     */
    @ApiModelProperty(value = "报产来源")
    private String productionProcessId;
    /**
     * 组织id
     */
    @ApiModelProperty(value = "组织id")
    private String organizeId;
    /**
     * 加工次数
     */
    @ApiModelProperty(value = "加工次数")
    private Integer processingTimes;
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
     * 报产方式
     */
    @ApiModelProperty(value = "报产方式")
    private String outputType;
    /**
     * 提前期
     */
    @ApiModelProperty(value = "提前期")
    private Integer fixDay;
    /**
     * 后段约束
     */
    @ApiModelProperty(value = "后段约束")
    private String postConstraint;
    /**
     * 额外报产
     */
    @ApiModelProperty(value = "额外报产")
    private Boolean extraYield;
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


    public ApsProcessDto getApsProcessDto() {
        return apsProcessDto;
    }

    public void setApsProcessDto(ApsProcessDto apsProcessDto) {
        this.apsProcessDto = apsProcessDto;
    }

    public ApsProcessGroupDto getApsProcessGroupDto() {
        return apsProcessGroupDto;
    }

    public void setApsProcessGroupDto(ApsProcessGroupDto apsProcessGroupDto) {
        this.apsProcessGroupDto = apsProcessGroupDto;
    }

    public Boolean getSchedulingFlag() {
        return schedulingFlag;
    }

    public void setSchedulingFlag(Boolean schedulingFlag) {
        this.schedulingFlag = schedulingFlag;
    }

    public String getPostProcessId() {
        return postProcessId;
    }

    public void setPostProcessId(String postProcessId) {
        this.postProcessId = postProcessId;
    }

    public Boolean getAcquisitionFlag() {
        return acquisitionFlag;
    }

    public void setAcquisitionFlag(Boolean acquisitionFlag) {
        this.acquisitionFlag = acquisitionFlag;
    }

    public Integer getAcquisitionTimes() {
        return acquisitionTimes;
    }

    public void setAcquisitionTimes(Integer acquisitionTimes) {
        this.acquisitionTimes = acquisitionTimes;
    }

    public Boolean getProductionFlag() {
        return productionFlag;
    }

    public void setProductionFlag(Boolean productionFlag) {
        this.productionFlag = productionFlag;
    }

    public Boolean getPersonFlag() {
        return personFlag;
    }

    public void setPersonFlag(Boolean personFlag) {
        this.personFlag = personFlag;
    }

    public Integer getProcessPriority() {
        return processPriority;
    }

    public void setProcessPriority(Integer processPriority) {
        this.processPriority = processPriority;
    }

    public String getProductionProcessId() {
        return productionProcessId;
    }

    public void setProductionProcessId(String productionProcessId) {
        this.productionProcessId = productionProcessId;
    }

    public String getOrganizeId() {
        return organizeId;
    }

    public void setOrganizeId(String organizeId) {
        this.organizeId = organizeId;
    }

    public Integer getProcessingTimes() {
        return processingTimes;
    }

    public void setProcessingTimes(Integer processingTimes) {
        this.processingTimes = processingTimes;
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

    public String getOutputType() {
        return outputType;
    }

    public void setOutputType(String outputType) {
        this.outputType = outputType;
    }

    public Integer getFixDay() {
        return fixDay;
    }

    public void setFixDay(Integer fixDay) {
        this.fixDay = fixDay;
    }

    public String getPostConstraint() {
        return postConstraint;
    }

    public void setPostConstraint(String postConstraint) {
        this.postConstraint = postConstraint;
    }

    public Boolean getExtraYield() {
        return extraYield;
    }

    public void setExtraYield(Boolean extraYield) {
        this.extraYield = extraYield;
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
