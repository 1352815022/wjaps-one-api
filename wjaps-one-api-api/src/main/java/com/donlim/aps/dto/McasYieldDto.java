package com.donlim.aps.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * (McasYield)DTO类
 *
 * @author sei
 * @since 2022-06-14 10:26:07
 */
@ApiModel(description = "DTO")
@Data
@EqualsAndHashCode(callSuper=false)
public class McasYieldDto extends BaseEntityDto {
    private static final long serialVersionUID = -47787003435086473L;
    /**
     * 公司编码
     */
    @ApiModelProperty(value = "公司编码")
    private String companyCode;
    /**
     * 公司名称
     */
    @ApiModelProperty(value = "公司名称")
    private String companyName;
    /**
     * 线名称
     */
    @ApiModelProperty(value = "线名称")
    private String lineName;
    /**
     * 报工日期
     */
    @ApiModelProperty(value = "报工日期")
    private LocalDateTime date;
    /**
     * 工号
     */
    @ApiModelProperty(value = "工号")
    private String employeeCode;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String employeeName;
    /**
     * 工单号
     */
    @ApiModelProperty(value = "工单号")
    private String mo;
    /**
     * 料号
     */
    @ApiModelProperty(value = "料号")
    private String materialCode;
    /**
     * 品名
     */
    @ApiModelProperty(value = "品名")
    private String materialName;
    /**
     * 工序
     */
    @ApiModelProperty(value = "工序")
    private String process;
    /**
     * 产量
     */
    @ApiModelProperty(value = "产量")
    private Double qty;


}
