package com.donlim.aps.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description:订单统计
 * @Author: chenzhiquan
 * @Date: 2023/5/8.
 */
@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(description = "订单统计")
public class OrderStatisticsDto implements Serializable {

    private static final long serialVersionUID = 2848434565057562927L;

    /**
     * 日完工数
     */
    @ApiModelProperty(value = "完工数")
    private Integer finishNumByDay;


    /**
     * 日排产数
     */
    @ApiModelProperty(value = "排产总数")
    private Integer planNumByDay;


    /**
     * 日排产及时率
     */
    @ApiModelProperty(value = "排产及时率")
    private String prodSchedRateByDay;

    /**
     * 周完工数
     */
    @ApiModelProperty(value = "完工数")
    private Integer finishNumByWeek;


    /**
     * 周排产数
     */
    @ApiModelProperty(value = "排产总数")
    private Integer planNumByWeek;


    /**
     * 周排产及时率
     */
    @ApiModelProperty(value = "排产及时率")
    private String prodSchedRateByWeek;


    /**
     * 月完工数
     */
    @ApiModelProperty(value = "完工数")
    private Integer finishNumByMonth;


    /**
     * 月排产数
     */
    @ApiModelProperty(value = "排产总数")
    private Integer planNumByMonth;


    /**
     *月排产及时率
     */
    @ApiModelProperty(value = "排产及时率")
    private String prodSchedRateByMonth;


}
