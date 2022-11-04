package com.donlim.aps.dto;

import com.changhong.sei.util.DateUtils;
import com.donlim.aps.util.EnumJsonSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @Description:预排订单信息导出
 * @Author: chenzhiquan
 * @Date: 2022/8/31.
 */
@Data
public class InnerOrderExcelDto implements Serializable {

    private static final long serialVersionUID = -3847170103657627725L;
    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单号")
    private String orderNo;
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
     * 下单日期
     */
    @ApiModelProperty(value = "下单日期")
    @JsonFormat(timezone = DateUtils.DEFAULT_TIMEZONE,pattern = DateUtils.DEFAULT_DATE_FORMAT)
    private LocalDate orderDate;
    /**
     * 产能
     */
    @ApiModelProperty(value = "产能")
    private String capacity;
    /**
     * 料号
     */
    @ApiModelProperty(value = "料号")
    private String materialCode;
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
     * 客户名称
     */
    @ApiModelProperty(value = "客户名称")
    private String customerName;
    /**
     * 状态
     */
    @JsonSerialize(using = EnumJsonSerializer.class)
    @ApiModelProperty(value = "状态")
    private OrderStatusType status;
    /**
     * U9状态(0、开立、1、已核准。2、开工。3、完工。4、核准中。)
     */
    @JsonSerialize(using = EnumJsonSerializer.class)
    @ApiModelProperty(value = "U9状态")
    private U9OrderStatus u9Status;
    /**
     * 订单数量
     */
    @ApiModelProperty(value = "订单数量")
    private BigDecimal orderQty;
    /**
     * 生产数量
     */
    @ApiModelProperty(value = "生产数量")
    private BigDecimal produceQty;
    /**
     * 欠入库数
     */
    @ApiModelProperty(value = "欠入库数")
    private BigDecimal oweQty;
    /**
     * 已排数量
     */
    @ApiModelProperty(value = "已排数量")
    private BigDecimal totalPlanQty;
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
    @JsonFormat(timezone = DateUtils.DEFAULT_TIMEZONE,pattern = DateUtils.DEFAULT_DATE_FORMAT)
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
}
