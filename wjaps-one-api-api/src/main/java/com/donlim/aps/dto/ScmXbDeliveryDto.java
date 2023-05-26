package com.donlim.aps.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import com.donlim.aps.util.EnumJsonRemarkSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * scm送货需求(ScmXbDelivery)DTO类
 *
 * @author sei
 * @since 2022-05-18 08:12:55
 */
@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(description = "scm送货需求DTO")
public class ScmXbDeliveryDto extends BaseEntityDto {
    private static final long serialVersionUID = -89845970427420273L;
    /**
     * 采购单号
     */
    @ApiModelProperty(value = "采购单号")
    private String po;
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
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sortCode;
    /**
     * 租户代码
     */
    @ApiModelProperty(value = "租户代码")
    private String tenantCode;
    /**
     * 送货开始日期
     */
    @ApiModelProperty(value = "送货开始日期")
    private LocalDate deliveryStartDate;
    /**
     * 送货日期是否变更
     */
    @ApiModelProperty(value = "送货日期是否变更")
    private Boolean changeDateFlag;
    /**
     * 送货数量是否变更
     */
    @ApiModelProperty(value = "送货数量是否变更")
    private Boolean changeQtyFlag;

    /**
     * 原送货开始日期
     */
    @ApiModelProperty(value = "原送货开始日期")
    private LocalDate deliveryOldStartDate;

    /**
     * 原送货数量
     */
    @ApiModelProperty(value = "原送货数量")
    private BigDecimal deliveryOldQty;
    /**
     * 送货结束日期
     */
    @ApiModelProperty(value = "送货结束日期")
    private LocalDate deliveryEndDate;
    /**
     * U9组织编码
     */
    @ApiModelProperty(value = "U9组织编码")
    private String orgId;
    /**
     * 需求分类号
     */
    @ApiModelProperty(value = "需求分类号")
    private String orderNo;
    /**
     * 料号
     */
    @ApiModelProperty(value = "料号")
    private String materialCode;
    /**
     * 料品名称
     */
    @ApiModelProperty(value = "料品名称")
    private String materialName;
    /**
     * 料品规格
     */
    @ApiModelProperty(value = "料品规格")
    private String materialSpec;
    /**
     * 合作单位
     */
    @ApiModelProperty(value = "合作单位")
    private String companyName;
    /**
     * 供应商编码
     */
    @ApiModelProperty(value = "供应商编码")
    private String supplierCode;
    /**
     * 供应商简称
     */
    @ApiModelProperty(value = "供应商简称")
    private String supplierName;
    /**
     * 应交货数量
     */
    @ApiModelProperty(value = "应交货数量")
    private BigDecimal deliveryQty;
    /**
     * 日最大需求量
     */
    @ApiModelProperty(value = "日最大需求量")
    private BigDecimal dayQty;
    /**
     * 欠发数量
     */
    @ApiModelProperty(value = "欠发数量")
    private BigDecimal oweQty;
    /**
     * U9累计到货数量
     */
    @ApiModelProperty(value = "U9累计到货数量")
    private BigDecimal sumArrivalQty;
    /**
     * 采购员
     */
    @ApiModelProperty(value = "采购员")
    private String buyer;
    /**
     * 规格型号
     */
    @ApiModelProperty(value = "规格型号")
    private String spec;
    /**
     * 计量单位
     */
    @ApiModelProperty(value = "计量单位")
    private String unit;
    /**
     * aps排产状态
     */
    @ApiModelProperty(value = "aps排产状态")
    @JsonSerialize(using = EnumJsonRemarkSerializer.class)
    private OrderStatusType apsOrderStatus;
    /**
     * 车间
     */
    @ApiModelProperty(value = "车间")
    private String apsOrderWorkGroupName;
    /**
     * 班组
     */
    @ApiModelProperty(value = "班组")
    private String apsOrderWorkLineName;

}
