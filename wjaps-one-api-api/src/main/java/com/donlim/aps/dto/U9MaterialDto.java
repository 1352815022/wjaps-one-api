package com.donlim.aps.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 料品表(U9Material)DTO类
 *
 * @author sei
 * @since 2022-05-13 11:12:00
 */
@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(description = "料品表DTO")
public class U9MaterialDto extends BaseEntityDto {
    private static final long serialVersionUID = -69254118145589000L;
    /**
     * 料号
     */
    @ApiModelProperty(value = "料号")
    private String code;
    /**
     * 料品名称
     */
    @ApiModelProperty(value = "料品名称")
    private String name;
    /**
     * 规格
     */
    @ApiModelProperty(value = "规格")
    private String spec;
    /**
     * 生产部门
     */
    @ApiModelProperty(value = "生产部门")
    private String deptCode;
    /**
     * 组织id
     */
    @ApiModelProperty(value = "组织id")
    private Long orgId;
    /**
     * 型号
     */
    @ApiModelProperty(value = "型号")
    private String productModel;
    /**
     * 单位
     */
    @ApiModelProperty(value = "单位")
    private String unit;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 料品类型
     */
    @ApiModelProperty(value = "料品类型")
    private String type;
    /**
     * 损耗
     */
    @ApiModelProperty(name = "损耗")
    private BigDecimal scrap;

    /**
     * 固定提前期
     */
    @ApiModelProperty(name = "固定提前期")
    private BigDecimal  fixedLt;

    /**
     * 产能（件/天）
     */
    @ApiModelProperty(name = "产能（件/天）")
    private Integer  capacity;
    /**
     * 上月期末数
     */
    @ApiModelProperty(value = "上月期末数")
    private BigDecimal endQty;

    /**
     * 喷粉类型
     */
    @ApiModelProperty(value = "喷粉类型")
    private String powderModel;

    /**
     * 喷粉面积
     */
    @ApiModelProperty(value = "喷粉面积")
    private BigDecimal powderArea;

    /**
     * 冲洗面积
     */
    @ApiModelProperty(value = "冲洗面积")
    private BigDecimal washArea;
    /**
     * 是否冻结
     */
    @ApiModelProperty(value = "是否冻结")
    private Boolean frozen;


    /**
     * 是否计算
     */
    @ApiModelProperty(value = "是否计算")
    private Boolean calc;

    /**
     * 租户代码
     */
    @ApiModelProperty(value = "租户代码")
    private String tenantCode;




}
