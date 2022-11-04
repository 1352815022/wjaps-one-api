package com.donlim.aps.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import com.changhong.sei.util.DateUtils;
import com.donlim.aps.util.EnumJsonRemarkSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 订单表(ApsOrder)DTO类
 *
 * @author sei
 * @since 2022-05-10 15:29:58
 */
@Data
@ApiModel(description = "订单表DTO")
public class ApsOrderDto extends BaseEntityDto {
    private static final long serialVersionUID = -47556945537507921L;

    /**
     * 下达类型 按时间、产能
     */
    @ApiModelProperty(value = "下达类型")
    private String planTypeCode;

    /**
     * 车间id
     */
    @ApiModelProperty(value = "车间id")
    private String workGroupId;
    /**
     * 生产线id
     */
    @ApiModelProperty(value = "生产线id")
    private String workLineId;
    /**
     * 车间
     */
    @ApiModelProperty(value = "车间")
    private String workGroupName;
    /**
     * 生产线
     */
    @ApiModelProperty(value = "生产线")
    private String workLineName;
    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单号")
    private String orderNo;
    /**
     * 采购单号
     */
    @ApiModelProperty(value = "采购单号")
    private String poNo;
    /**
     * 下单日期
     */
    @ApiModelProperty(value = "下单日期")
    @JsonFormat(pattern = DateUtils.DEFAULT_DATE_FORMAT)
    private LocalDate orderDate;
    /**
     * 料品id
     */
    @ApiModelProperty(value = "料品id")
    private Long materialId;

    /**
     * 料号
     */
    @ApiModelProperty(value = "料号")
    private String materialCode;

    /**
     * 产能
     */
    @ApiModelProperty(value = "产能")
    private BigDecimal capacity;
    /**
     * 料名
     */
    @ApiModelProperty(value = "料名")
    private String materialName;
    /**
     * 料规格
     */
    @ApiModelProperty(value = "料规格")
    private String materialSpec;
    /**
     * 客户code
     */
    @ApiModelProperty(value = "客户code")
    private String customerCode;
    /**
     * 客户名称
     */
    @ApiModelProperty(value = "客户名称")
    private String customerName;
    /**
     * 类型：INNER:内排 ； OUTER:委外
     */
    @JsonSerialize(using = EnumJsonRemarkSerializer.class)
    @ApiModelProperty(value = "类型：INNER:内排 ； OUTER:委外")
    private ApsOrderType type;
    /**
     * 状态
     */
    @JsonSerialize(using = EnumJsonRemarkSerializer.class)
    @ApiModelProperty(value = "状态")
    private OrderStatusType status;
    /**
     * U9状态
     */
    @JsonSerialize(using = EnumJsonRemarkSerializer.class)
    @ApiModelProperty(value = "U9状态")
    private U9OrderStatus u9Status;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 来源单号
     */
    @ApiModelProperty(value = "来源单号")
    private String sourceOrderNo;
    /**
     * 生产数量
     */
    @ApiModelProperty(value = "生产数量")
    private BigDecimal produceQty;
    /**
     * 计划数量
     */
    @ApiModelProperty(value = "计划数量")
    private BigDecimal planQty;
    /**
     * 已排数量
     */
    @ApiModelProperty(value = "已排数量")
    private BigDecimal totalPlanQty;
    /**
     * U9已完成数量
     */
    @ApiModelProperty(value = "U9已完成数量")
    private BigDecimal totalCompleteQty;
    /**
     * 未排数量
     */
    @ApiModelProperty(value = "未排数量")
    private BigDecimal noPlanQty;


    /**
     * 计划开工日期
     */
    @ApiModelProperty(value = "计划开工日期")
    @JsonFormat(pattern = DateUtils.DEFAULT_DATE_FORMAT)
    private LocalDate planStartDate;

    /**
     * 计划完工日期
     */
    @ApiModelProperty(value = "计划完工日期")
    @JsonFormat(pattern = DateUtils.DEFAULT_DATE_FORMAT)
    private LocalDate planFinishDate;

    /**
     * 交期/送货开始日期
     */
    @ApiModelProperty(value = "交期/送货开始日期")
    @JsonFormat(pattern = DateUtils.DEFAULT_DATE_FORMAT)
    private LocalDate deliveryStartDate;
    /**
     * 交期/送货借宿日期
     */
    @ApiModelProperty(value = "交期/送货结束日期")
    @JsonFormat(pattern = DateUtils.DEFAULT_DATE_FORMAT)
    private LocalDate deliveryEndDate;
    /**
     * 投料日期
     */
    @ApiModelProperty(value = "投料日期")
    @JsonFormat(pattern = DateUtils.DEFAULT_DATE_FORMAT)
    private LocalDate feedingDate;
    /**
     * 订单数量
     */
    @ApiModelProperty(value = "订单数量")
    private BigDecimal orderQty;
    /**
     * 欠入库数
     */
    @ApiModelProperty(value = "欠入库数")
    private BigDecimal oweQty;
    /**
     * 生产计划批次号
     */
    @ApiModelProperty(value = "生产计划批次号")
    private Integer planNum;
    /**
     * 租户代码
     */
    @ApiModelProperty(value = "租户代码")
    private String tenantCode;
    /**
     * scm送货单id
     */
    @ApiModelProperty(value = "SCM送货单id")
    private String scmId;


}
