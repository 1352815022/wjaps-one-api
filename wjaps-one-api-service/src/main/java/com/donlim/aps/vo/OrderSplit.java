package com.donlim.aps.vo;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @ClassName OrderSplit
 * @Description 内部待排组件拆分暂存实体类
 * @Author p09835
 * @Date 2022/5/12 16:54
 **/
public class OrderSplit {

    /**
     * 订单id
     */
    private String orderId;
    /**
     * 拆分层级
     */
    private Integer level;
    /**
     * 料品id
     */
    private Long materialId;

    /**
     * 料品编码
     */
    private String materialCode;

    /**
     * 料品名称
     */
    private String materialName;
    /**
     * 料品规格
     */
    private String materialSpec;

    /**
     * 订单数量
     */
    private BigDecimal orderQty;

    /**
     * 完成数
     */
    private BigDecimal completeQty;

    /**
     * bom计算用量
     */
    private BigDecimal bomUsage;

    /**
     * 损耗
     */
    private BigDecimal scrap;
    /**
     * scm交期
     */
    private LocalDate scmDelivery;

    public OrderSplit(){}

    public OrderSplit(Long materialId,BigDecimal bomUsage){
        this.materialId = materialId;
        this.bomUsage = bomUsage;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

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

    public String getMaterialSpec() {
        return materialSpec;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
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

    public BigDecimal getCompleteQty() {
        return completeQty;
    }

    public void setCompleteQty(BigDecimal completeQty) {
        this.completeQty = completeQty;
    }

    public BigDecimal getBomUsage() {
        return bomUsage;
    }

    public void setBomUsage(BigDecimal bomUsage) {
        this.bomUsage = bomUsage;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public BigDecimal getScrap() {
        return scrap;
    }

    public void setScrap(BigDecimal scrap) {
        this.scrap = scrap;
    }

    public LocalDate getScmDelivery() {
        return scmDelivery;
    }

    public void setScmDelivery(LocalDate scmDelivery) {
        this.scmDelivery = scmDelivery;
    }

}
