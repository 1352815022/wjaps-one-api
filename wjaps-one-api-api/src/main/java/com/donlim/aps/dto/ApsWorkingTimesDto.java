package com.donlim.aps.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 上班日历配置表(ApsWorkingTimes)DTO类
 *
 * @author sei
 * @since 2022-05-04 10:15:33
 */
@ApiModel(description = "上班日历配置表DTO")
public class ApsWorkingTimesDto extends BaseEntityDto {
    private static final long serialVersionUID = -93960542340523017L;

    /**
     * 上班日期
     */
    @ApiModelProperty(value = "上班日期")
    private LocalDate workDate;
    /**
     * 组织id(生产线)
     */
    @ApiModelProperty(value = "组织id(生产线)")
    private String organizeId;

    /**
     * 组织名称
     */
    @ApiModelProperty(value = "组织名称")
    private String apsOrganizeName;

    /**
     * 标准上班时长
     */
    @ApiModelProperty(value = "标准上班时长")
    private BigDecimal workHour;
    /**
     * 加班时长
     */
    @ApiModelProperty(value = "加班时长")
    private BigDecimal overTimeHour;
    /**
     * 人数
     */
    @ApiModelProperty(value = "人数")
    private Integer numOfPeople;
    /**
     * 总时长
     */
    @ApiModelProperty(value = "总时长")
    private BigDecimal totalHour;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
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

    public LocalDate getWorkDate() {
        return workDate;
    }

    public void setWorkDate(LocalDate workDate) {
        this.workDate = workDate;
    }

    public String getOrganizeId() {
        return organizeId;
    }

    public void setOrganizeId(String organizeId) {
        this.organizeId = organizeId;
    }

    public String getApsOrganizeName() {
        return apsOrganizeName;
    }

    public void setApsOrganizeName(String apsOrganizeName) {
        this.apsOrganizeName = apsOrganizeName;
    }

    public BigDecimal getWorkHour() {
        return workHour;
    }

    public void setWorkHour(BigDecimal workHour) {
        this.workHour = workHour;
    }

    public BigDecimal getOverTimeHour() {
        return overTimeHour;
    }

    public void setOverTimeHour(BigDecimal overTimeHour) {
        this.overTimeHour = overTimeHour;
    }

    public Integer getNumOfPeople() {
        return numOfPeople;
    }

    public void setNumOfPeople(Integer numOfPeople) {
        this.numOfPeople = numOfPeople;
    }

    public BigDecimal getTotalHour() {
        return totalHour;
    }

    public void setTotalHour(BigDecimal totalHour) {
        this.totalHour = totalHour;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
