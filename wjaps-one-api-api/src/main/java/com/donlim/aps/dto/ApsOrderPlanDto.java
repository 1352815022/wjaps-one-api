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
import java.util.Date;

/**
 * 生产计划表(内部)(ApsOrderPlan)DTO类
 *
 * @author sei
 * @since 2022-05-11 16:06:29
 */
@ApiModel(description = "生产计划表(内部)DTO")
@Data
public class ApsOrderPlanDto extends BaseEntityDto {
    private static final long serialVersionUID = -55777916414295323L;
    /**
     * 订单id
     */
    @ApiModelProperty(value = "订单id")
    private String orderId;
    /**
     * 生产计划批次号
     */
    @ApiModelProperty(value = "生产计划批次号")
    private Integer planNum;
    /**
     * 产品id
     */
    @ApiModelProperty(value = "产品id")
    private Long materialId;


    private String materialCode;
    /**
     * 料规格
     */
    @ApiModelProperty(value = "料规格")
    private String materialSpec;
    /**
     * 完成数量
     */
    @ApiModelProperty(value = "完成数量")
    private BigDecimal hasQty;
    /**
     * 欠数
     */
    @ApiModelProperty(value = "欠数")
    private BigDecimal oweQty;
    /**
     * 计划数
     */
    @ApiModelProperty(value = "计划数")
    private BigDecimal planQty;
    /**
     * 待排数
     */
    @ApiModelProperty(value = "待排数")
    private BigDecimal awaitQty;
    /**
     * 下单日期
     */
    @ApiModelProperty(value = "下单日期")
    @JsonFormat(pattern = DateUtils.DEFAULT_DATE_FORMAT)
    private LocalDate orderDate;
    /**
     * SCM交期
     */
    @ApiModelProperty(value = "SCM交期")
    @JsonFormat(pattern = DateUtils.DEFAULT_DATE_FORMAT)
    private LocalDate scmDeliveryDate;
    /**
     * 开拉日期
     */
    @ApiModelProperty(value = "开拉日期")
    @JsonFormat(pattern = DateUtils.DEFAULT_DATE_FORMAT)
    private LocalDate productionDate;
    /**
     * 标准产能
     */
    @ApiModelProperty(value = "标准产能")
    private BigDecimal standardQty;

    /**
     * 实际产能
     */
    @ApiModelProperty(value = "实际产能")
    private BigDecimal actualQty;

    /**
     * U9状态
     */
    @ApiModelProperty(value = "U9状态")
    private Integer u9Status;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @JsonSerialize(using = EnumJsonRemarkSerializer.class)
    private OrderStatusType status;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 订单交期
     */
    @ApiModelProperty(value = "订单交期")
    @JsonFormat(pattern = DateUtils.DEFAULT_DATE_FORMAT)
    private LocalDate deliveryDate;
    /**
     * 是否冻结
     */
    @ApiModelProperty(value = "是否冻结")
    private Boolean frozen;
    /**
     * 车间id
     */
    @ApiModelProperty(value = "车间id")
    private String workGroupId;
    /**
     * 生产车间
     */
    @ApiModelProperty(value = "生产车间")
    private String workGroupName;
    /**
     * 生产线id
     */
    @ApiModelProperty(value = "生产线id")
    private String lineId;
    /**
     * 生产线
     */
    @ApiModelProperty(value = "生产线")
    private String lineName;
    /**
     * 计划开始日期
     */
    @ApiModelProperty(value = "计划开始日期")
    @JsonFormat(pattern = DateUtils.DEFAULT_DATE_FORMAT)
    private LocalDate startDate;
    /**
     * 计划结束日期
     */
    @ApiModelProperty(value = "计划结束日期")
    @JsonFormat(pattern = DateUtils.DEFAULT_DATE_FORMAT)
    private LocalDate endDate;
    /**
     * 租户代码
     */
    @ApiModelProperty(value = "租户代码")
    private String tenantCode;


    protected String lastEditorName;
    /**
     * 最后修改时间
     */
    @JsonFormat(pattern = DateUtils.DEFAULT_DATE_FORMAT)
    protected LocalDate lastEditedDate;

}
