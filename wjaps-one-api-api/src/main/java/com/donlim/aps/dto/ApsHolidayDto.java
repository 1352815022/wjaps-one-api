package com.donlim.aps.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import com.changhong.sei.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;

/**
 * 放假表(ApsHoliday)DTO类
 *
 * @author sei
 * @since 2022-05-03 08:29:59
 */
@ApiModel(description = "放假表DTO")
public class ApsHolidayDto extends BaseEntityDto {
    private static final long serialVersionUID = 962995872764977562L;
    /**
     * 组织Dto
     */
    @ApiModelProperty(value = "组织Dto")
    private ApsOrganizeDto apsOrganizeDto;

    /**
     * 假期名称
     */
    @ApiModelProperty(value = "假期名称")
    private String holidayName;
    /**
     * 开始日期
     */
    @ApiModelProperty(value = "开始日期")
    @JsonFormat(timezone = DateUtils.DEFAULT_TIMEZONE,pattern = DateUtils.DEFAULT_DATE_FORMAT)
    private LocalDate startDate;
    /**
     * 结束日期
     */
    @ApiModelProperty(value = "结束日期")
    @JsonFormat(timezone = DateUtils.DEFAULT_TIMEZONE,pattern = DateUtils.DEFAULT_DATE_FORMAT)
    private LocalDate endDate;
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

    public ApsOrganizeDto getApsOrganizeDto() {
        return apsOrganizeDto;
    }

    public void setApsOrganizeDto(ApsOrganizeDto apsOrganizeDto) {
        this.apsOrganizeDto = apsOrganizeDto;
    }

    public String getHolidayName() {
        return holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
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
