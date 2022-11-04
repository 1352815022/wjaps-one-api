package com.donlim.aps.dto;

import com.changhong.sei.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @ClassName StampingOrderDto
 * @Description 冲压车间订单DTO
 * @Author p09835
 * @Date 2022/5/18 14:44
 **/
@ApiModel("冲压车间订单DTO")
public class StampingOrderDto implements Comparable<StampingOrderDto>{

    @JsonIgnore
    private Long materialId;
    @ApiModelProperty(value = "料号")
    private String materialCode;

    @ApiModelProperty(value = "料名")
    private String materialName;

    @ApiModelProperty(value = "规格")
    private String materialSpec;

    @ApiModelProperty(value = "拆分层级")
    private Integer level;

    @ApiModelProperty(value = "订单数量")
    private BigDecimal orderQty;

    @ApiModelProperty(value = "耗损比例")
    private BigDecimal scrap;

    @ApiModelProperty(value = "生产欠数")
    private BigDecimal produceOweQty;

    @ApiModelProperty(value = "生产工单数")
    private BigDecimal produceQty;

    @ApiModelProperty(value = "SCM交期")
    @JsonFormat(pattern = DateUtils.DEFAULT_DATE_FORMAT)
    private LocalDate scmDelivery;

    @ApiModelProperty(value = "库存")
    private BigDecimal stockQty;

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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

    public BigDecimal getScrap() {
        return scrap;
    }

    public void setScrap(BigDecimal scrap) {
        this.scrap = scrap;
    }

    public BigDecimal getProduceOweQty() {
        return produceOweQty;
    }

    public void setProduceOweQty(BigDecimal produceOweQty) {
        this.produceOweQty = produceOweQty;
    }

    public LocalDate getScmDelivery() {
        return scmDelivery;
    }

    public void setScmDelivery(LocalDate scmDelivery) {
        this.scmDelivery = scmDelivery;
    }

    public BigDecimal getProduceQty() {
        return produceQty;
    }

    public void setProduceQty(BigDecimal produceQty) {
        this.produceQty = produceQty;
    }

    public BigDecimal getStockQty() {
        return stockQty;
    }

    public void setStockQty(BigDecimal stockQty) {
        this.stockQty = stockQty;
    }

    @Override
    public int compareTo(StampingOrderDto o) {
        // 升序
//        return this.getLevel().compareTo(o.getLevel());
        // 降序
        return o.getLevel().compareTo(this.getLevel());
    }
}
