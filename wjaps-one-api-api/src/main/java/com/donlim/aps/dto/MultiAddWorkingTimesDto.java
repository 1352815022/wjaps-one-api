package com.donlim.aps.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @ClassName MultiAddWorkingTimeDto
 * @Description 批量新增工作日期DTO
 * @Author p09835
 * @Date 2022/5/5 9:24
 **/
@ApiModel(description = "批量新增工作日期DTO")
public class MultiAddWorkingTimesDto extends BaseEntityDto {


    /**
     * 组织id(生产线)
     */
    @ApiModelProperty(value = "组织id(生产线)")
    private String organizeId;
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
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 开始日期
     */
    @ApiModelProperty(value = "开始日期")
    private LocalDate effectiveFrom;

    /**
     * 结束日期
     */
    @ApiModelProperty(value = "结束日期")
    private LocalDate effectiveTo;

    public String getOrganizeId() {
        return organizeId;
    }

    public void setOrganizeId(String organizeId) {
        this.organizeId = organizeId;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public LocalDate getEffectiveFrom() {
        return effectiveFrom;
    }

    public void setEffectiveFrom(LocalDate effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }

    public LocalDate getEffectiveTo() {
        return effectiveTo;
    }

    public void setEffectiveTo(LocalDate effectiveTo) {
        this.effectiveTo = effectiveTo;
    }
}
