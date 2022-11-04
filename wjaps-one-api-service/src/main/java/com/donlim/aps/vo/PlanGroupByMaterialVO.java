package com.donlim.aps.vo;

import com.donlim.aps.dto.MaterialType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

/**
 * @ClassName PlanGroupByMaterialVO
 * @Description 生产计划按料品原料计算（欠料跟踪）
 * @Author p09835
 * @Date 2022/6/15 10:48
 **/
public class PlanGroupByMaterialVO implements Serializable {

    private static final long serialVersionUID = -6140390201299192212L;
    /** 原料id */
    private Long materialId;
    /** 原料料号*/
    private String materialCode;
    /** 原料料名*/
    private String materialName;
    /** 原料规格*/
    private String materialSpec;
    /** 原料属性*/
    private MaterialType materialType;
    /** 库存数*/
    private BigDecimal stockQty;
    /** 已领数*/
    private BigDecimal pullQty;
    /** 需求数*/
    private BigDecimal requireQty;
    /** 请购单数*/
    private BigDecimal poQty;
    /** 暂收数*/
    private BigDecimal tempReceiveQty;
    /** 超欠数*/
    private BigDecimal beyondQty;
    /** 欠料日期*/
    private LocalDate oweDate;
    /** 明细*/
    private Map<LocalDate,BigDecimal> details;

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

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialSpec() {
        return materialSpec;
    }

    public void setMaterialSpec(String materialSpec) {
        this.materialSpec = materialSpec;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public BigDecimal getStockQty() {
        return stockQty;
    }

    public void setStockQty(BigDecimal stockQty) {
        this.stockQty = stockQty;
    }

    public BigDecimal getPullQty() {
        return pullQty;
    }

    public void setPullQty(BigDecimal pullQty) {
        this.pullQty = pullQty;
    }

    public BigDecimal getRequireQty() {
        return requireQty;
    }

    public void setRequireQty(BigDecimal requireQty) {
        this.requireQty = requireQty;
    }

    public BigDecimal getTempReceiveQty() {
        return tempReceiveQty;
    }

    public void setTempReceiveQty(BigDecimal tempReceiveQty) {
        this.tempReceiveQty = tempReceiveQty;
    }

    public BigDecimal getPoQty() {
        return poQty;
    }

    public void setPoQty(BigDecimal poQty) {
        this.poQty = poQty;
    }

    public BigDecimal getBeyondQty() {
        return beyondQty;
    }

    public void setBeyondQty(BigDecimal beyondQty) {
        this.beyondQty = beyondQty;
    }

    public LocalDate getOweDate() {
        return oweDate;
    }

    public void setOweDate(LocalDate oweDate) {
        this.oweDate = oweDate;
    }

    public Map<LocalDate, BigDecimal> getDetails() {
        return details;
    }

    public void setDetails(Map<LocalDate, BigDecimal> details) {
        this.details = details;
    }
}
