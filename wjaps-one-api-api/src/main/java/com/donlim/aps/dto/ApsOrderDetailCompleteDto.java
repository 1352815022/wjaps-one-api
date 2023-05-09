package com.donlim.aps.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 订单齐套(ApsOrderDetailComplete)DTO类
 *
 * @author sei
 * @since 2022-07-13 14:01:28
 */
@ApiModel(description = "订单齐套DTO")
@Data
@EqualsAndHashCode(callSuper=false)
public class ApsOrderDetailCompleteDto extends BaseEntityDto {
    private static final long serialVersionUID = 485555502081957857L;

    @ApiModelProperty(value = "父id")
    private String parentId;
    /**
     * 工单号
     */
    @ApiModelProperty(value = "工单号")
    private String productOrder;
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
     * 产品型号
     */
    @ApiModelProperty(value = "产品型号")
    private String productModel;
    /**
     * 需求数
     */
    @ApiModelProperty(value = "需求数")
    private BigDecimal requireQty;

    /**
     * 完成数
     */
    @ApiModelProperty(value = "完成数")
    private BigDecimal finishQty;

    /**
     * 齐套百分比
     */
    @ApiModelProperty(value = "齐套百分比")
    private String completePercent;
    /**
     * 需求分类号
     */
    @ApiModelProperty(value = "需求分类号")
    private String orderNo;
    /**
     * 租户代码
     */
    @ApiModelProperty(value = "租户代码")
    private String tenantCode;



}
