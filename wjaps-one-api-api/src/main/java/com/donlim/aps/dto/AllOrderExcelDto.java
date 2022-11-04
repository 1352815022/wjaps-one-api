package com.donlim.aps.dto;


import com.changhong.sei.util.DateUtils;
import com.donlim.aps.util.EnumJsonSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @ClassName AllOrderExcelDto
 * @Description 订单一览表excel实体类
 * @Author p09835
 * @Date 2022/6/9 9:35
 **/
public class AllOrderExcelDto implements Serializable {

    private static final long serialVersionUID = -3847170103657627726L;
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
    @JsonFormat(timezone = DateUtils.DEFAULT_TIMEZONE,pattern = DateUtils.DEFAULT_DATE_FORMAT)
    private LocalDate orderDate;

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
     * 类型：INNER:内排 ； OUTER:委外
     */
    @JsonSerialize(using = EnumJsonSerializer.class)
    @ApiModelProperty(value = "类型")
    private ApsOrderType type;
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
     * 计划完工日期
     */
    @ApiModelProperty(value = "计划完工日期")
    @JsonFormat(timezone = DateUtils.DEFAULT_TIMEZONE,pattern = DateUtils.DEFAULT_DATE_FORMAT)
    private LocalDate planFinishDate;
    /**
     * 交期/送货开始日期
     */
    @ApiModelProperty(value = "开始送货日期")
    @JsonFormat(timezone = DateUtils.DEFAULT_TIMEZONE,pattern = DateUtils.DEFAULT_DATE_FORMAT)
    private LocalDate deliveryStartDate;

    /**
     * 投料日期
     */
    @ApiModelProperty(value = "投料日期")
    @JsonFormat(timezone = DateUtils.DEFAULT_TIMEZONE,pattern = DateUtils.DEFAULT_DATE_FORMAT)
    private LocalDate feedingDate;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPoNo() {
        return poNo;
    }

    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialSpec() {
        return materialSpec;
    }

    public void setMaterialSpec(String materialSpec) {
        this.materialSpec = materialSpec;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public ApsOrderType getType() {
        return type;
    }

    public void setType(ApsOrderType type) {
        this.type = type;
    }

    public OrderStatusType getStatus() {
        return status;
    }

    public void setStatus(OrderStatusType status) {
        this.status = status;
    }

    public U9OrderStatus getU9Status() {
        return u9Status;
    }

    public void setU9Status(U9OrderStatus u9Status) {
        this.u9Status = u9Status;
    }

    public BigDecimal getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(BigDecimal orderQty) {
        this.orderQty = orderQty;
    }

    public BigDecimal getProduceQty() {
        return produceQty;
    }

    public void setProduceQty(BigDecimal produceQty) {
        this.produceQty = produceQty;
    }

    public BigDecimal getOweQty() {
        return oweQty;
    }

    public void setOweQty(BigDecimal oweQty) {
        this.oweQty = oweQty;
    }

    public BigDecimal getTotalPlanQty() {
        return totalPlanQty;
    }

    public void setTotalPlanQty(BigDecimal totalPlanQty) {
        this.totalPlanQty = totalPlanQty;
    }

    public BigDecimal getNoPlanQty() {
        return noPlanQty;
    }

    public void setNoPlanQty(BigDecimal noPlanQty) {
        this.noPlanQty = noPlanQty;
    }

    public LocalDate getPlanFinishDate() {
        return planFinishDate;
    }

    public void setPlanFinishDate(LocalDate planFinishDate) {
        this.planFinishDate = planFinishDate;
    }

    public LocalDate getDeliveryStartDate() {
        return deliveryStartDate;
    }

    public void setDeliveryStartDate(LocalDate deliveryStartDate) {
        this.deliveryStartDate = deliveryStartDate;
    }

    public LocalDate getFeedingDate() {
        return feedingDate;
    }

    public void setFeedingDate(LocalDate feedingDate) {
        this.feedingDate = feedingDate;
    }
}
