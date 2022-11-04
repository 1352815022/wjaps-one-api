package com.donlim.aps.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * (McasProcess)DTO类
 *
 * @author sei
 * @since 2022-06-15 14:04:13
 */
@ApiModel(description = "DTO")
public class McasProcessDto extends BaseEntityDto {
    private static final long serialVersionUID = 628890148351545771L;
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
     * 线别编码
     */
    @ApiModelProperty(value = "线别编码")
    private String lineCode;
    /**
     * 线别名称
     */
    @ApiModelProperty(value = "线别名称")
    private String lineName;
    /**
     * 料名
     */
    @ApiModelProperty(value = "料名")
    private String itemName;
    /**
     * 工序
     */
    @ApiModelProperty(value = "工序")
    private String process;


    private Object lastFlag;
    /**
     * 顺序
     */
    @ApiModelProperty(value = "顺序")
    private Integer sortNo;
    /**
     * 租户代码
     */
    @ApiModelProperty(value = "租户代码")
    private String tenantCode;


}
