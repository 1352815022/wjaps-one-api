package com.donlim.aps.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * 生产工序报工表
 * (ApsProductionProcessSchedule)DTO类
 *
 * @author sei
 * @since 2022-06-20 11:34:57
 */
@Data
@ApiModel(description = "生产工序报工表DTO")
public class ApsProductionProcessScheduleDto extends BaseEntityDto {
    private static final long serialVersionUID = -94137082702753306L;
    /**
     * 生产单号
     */
    @ApiModelProperty(value = "生产单号")
    private String productOrder;
    /**
     * 报工日期
     */
    @ApiModelProperty(value = "报工日期")
    private LocalDate productionDate;
    /**
     * 料品编码
     */
    @ApiModelProperty(value = "料品编码")
    private String materialCode;
    /**
     * 品名
     */
    @ApiModelProperty(value = "品名")
    private String materialName;
    /**
     * 料品规格
     */
    @ApiModelProperty(value = "料品规格")
    private String materialSpec;
    /**
     * 第一道工序报工数
     */
    @ApiModelProperty(value = "第一道工序报工数")
    private Integer process1;


    private Integer process2;


    private Integer process3;


    private Integer process4;


    private Integer process5;


    private Integer process6;


    private Integer process7;


    private Integer process8;


    private Integer process9;


    private Integer process10;


    private Integer process11;


    private Integer process12;


    private Integer process13;


    private Integer process14;


    private Integer process15;


    private Integer process16;


    private Integer process17;


    private Integer process18;


    private Integer process19;


    private Integer process20;


    private Integer process21;


    private Integer process22;


    private Integer process23;


    private Integer process24;


    private Integer process25;


    private Integer process26;


    private Integer process27;


    private Integer process28;


    private Integer process29;


    private Integer process30;
    /**
     * 租户代码
     */
    @ApiModelProperty(value = "租户代码")
    private String tenantCode;




}
