package com.donlim.aps.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @ClassName WashWorkshopScheduleDto
 * @Description 清洗车间进度表
 * @Author p09835
 * @Date 2022/7/21 14:27
 **/
@ApiModel("清洗车间进度表")
public class WashWorkshopScheduleDto implements Serializable {

    private static final long serialVersionUID = 2848434565057561927L;

    /**
     * MCAS 报工日期
     */
    @ApiModelProperty("报工日期")
    private String reportDate;
    /**
     * 车间
     */
    @ApiModelProperty("车间")
    private String workGroupName;
    /**
     * 班组
     */
    @ApiModelProperty("班组")
    private String workLineName;
    /**
     * 单号
     */
    @ApiModelProperty("单号")
    private String  orderNo;
    /**
     * 料号
     */
    @ApiModelProperty("料号")
    private String materialCode;
    /**
     * 料名
     */
    @ApiModelProperty("料名")
    private String materialName;
    /**
     * 规格
     */
    @ApiModelProperty("规格")
    private String materialSpec;
    /**
     * 订单数量
     */
    @ApiModelProperty("订单数量")
    private BigDecimal orderQty;
    /**
     * 排产数量
     */
    @ApiModelProperty("排产数量")
    private BigDecimal planQty;
    /**
     * 欠入库数
     */
    @ApiModelProperty("欠入库数")
    private BigDecimal oweQty;
    /**
     * SCM送货日期
     */
    @ApiModelProperty("SCM送货日期")
    private LocalDate deliveryDate;
    /**
     * 清洗报工数量
     */
    @ApiModelProperty("清洗报工数量")
    private BigDecimal washReportQty;
    /**
     * 喷粉报工数量
     */
    @ApiModelProperty("喷粉报工数量")
    private BigDecimal powderReportQty;
    /**
     * 产能
     */
    @ApiModelProperty("产能")
    private BigDecimal capacity;
    /**
     * 入库数
     */
    @ApiModelProperty("入库数")
    private BigDecimal inStockQty;
    /**
     * 上月期末数
     */
    @ApiModelProperty("上月期末数")
    private BigDecimal lastQty;
    /**
     * 喷粉类型
     */
    @ApiModelProperty("喷粉类型")
    private String powderModel;
    /**
     * 单件喷粉面积
     */
    @ApiModelProperty("单件喷粉面积")
    private BigDecimal powderArea;
    /**
     * 单件清洗面积
     */
    @ApiModelProperty("单件清洗面积")
    private BigDecimal washArea;
    /**
     * 总喷粉面积
     */
    @ApiModelProperty("总喷粉面积")
    private BigDecimal sumPowderArea;
    /**
     * 总清洗面积
     */
    @ApiModelProperty("总清洗面积")
    private BigDecimal sumWashArea;


    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getWorkGroupName() {
        return workGroupName;
    }

    public void setWorkGroupName(String workGroupName) {
        this.workGroupName = workGroupName;
    }

    public String getWorkLineName() {
        return workLineName;
    }

    public void setWorkLineName(String workLineName) {
        this.workLineName = workLineName;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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

    public BigDecimal getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(BigDecimal orderQty) {
        this.orderQty = orderQty;
    }

    public BigDecimal getPlanQty() {
        return planQty;
    }

    public void setPlanQty(BigDecimal planQty) {
        this.planQty = planQty;
    }

    public BigDecimal getOweQty() {
        return oweQty;
    }

    public void setOweQty(BigDecimal oweQty) {
        this.oweQty = oweQty;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public BigDecimal getWashReportQty() {
        return washReportQty;
    }

    public void setWashReportQty(BigDecimal washReportQty) {
        this.washReportQty = washReportQty;
    }

    public BigDecimal getPowderReportQty() {
        return powderReportQty;
    }

    public void setPowderReportQty(BigDecimal powderReportQty) {
        this.powderReportQty = powderReportQty;
    }

    public BigDecimal getCapacity() {
        return capacity;
    }

    public void setCapacity(BigDecimal capacity) {
        this.capacity = capacity;
    }

    public BigDecimal getInStockQty() {
        return inStockQty;
    }

    public void setInStockQty(BigDecimal inStockQty) {
        this.inStockQty = inStockQty;
    }

    public BigDecimal getLastQty() {
        return lastQty;
    }

    public void setLastQty(BigDecimal lastQty) {
        this.lastQty = lastQty;
    }

    public String getPowderModel() {
        return powderModel;
    }

    public void setPowderModel(String powderModel) {
        this.powderModel = powderModel;
    }

    public BigDecimal getPowderArea() {
        return powderArea;
    }

    public void setPowderArea(BigDecimal powderArea) {
        this.powderArea = powderArea;
    }

    public BigDecimal getWashArea() {
        return washArea;
    }

    public void setWashArea(BigDecimal washArea) {
        this.washArea = washArea;
    }

    public BigDecimal getSumPowderArea() {
        return sumPowderArea;
    }

    public void setSumPowderArea(BigDecimal sumPowderArea) {
        this.sumPowderArea = sumPowderArea;
    }

    public BigDecimal getSumWashArea() {
        return sumWashArea;
    }

    public void setSumWashArea(BigDecimal sumWashArea) {
        this.sumWashArea = sumWashArea;
    }
}
