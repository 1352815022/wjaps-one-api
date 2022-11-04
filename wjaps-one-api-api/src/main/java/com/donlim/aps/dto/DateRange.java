package com.donlim.aps.dto;

import com.changhong.sei.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @ClassName DateRange
 * @Description 日期范围实体类
 * @Author p09835
 * @Date 2022/5/12 15:50
 **/
@ApiModel(description = "日期范围实体类")
public class DateRange implements Serializable {

    private static final long serialVersionUID = 3009281956987253028L;
    /**
     * 开始日期
     */
    @ApiModelProperty(value = "开始日期")
    @JsonFormat(pattern = DateUtils.DEFAULT_DATE_FORMAT)
    private LocalDate effectiveFrom;

    /**
     * 结束日期
     */
    @ApiModelProperty(value = "结束日期")
    @JsonFormat(pattern = DateUtils.DEFAULT_DATE_FORMAT)
    private LocalDate effectiveTo;

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
