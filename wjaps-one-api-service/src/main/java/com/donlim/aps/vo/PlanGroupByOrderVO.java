package com.donlim.aps.vo;

import com.donlim.aps.dto.MaterialType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

/**
 * @ClassName PlanGroupByOrderVO
 * @Description 生产计划根据内排单号汇总数据实体
 * @Author p09835
 * @Date 2022/6/14 17:25
 **/
public class PlanGroupByOrderVO implements Serializable {

    private static final long serialVersionUID = 2606099747379253129L;

    /** 内排id */
    private String orderId;
    /** 工单号 */
    private String orderNo;
    /** 物料id */
    private Long materialId;
    /** 料号 */
    private String materialCode;
    /** 料名 */
    private String materialName;
    /** 规格 */
    private String materialSpec;
    /** 料属性 */
    private MaterialType materialType;
    /** 原料料id */
    private Long originMaterialId;
    /** 原料料号 */
    private String originMaterialCode;
    /** 原料料名 */
    private String originMaterialName;
    /** 原料料规格 */
    private String originMaterialSpec;
    /** 原料料属性 */
    private MaterialType originMaterialType;
    /** 备料表用量 */
    private BigDecimal bomQty;
    /** 排产数量 */
    private BigDecimal planQty;
    /** 领料数量 */
    private BigDecimal pullQty;
    /** 暂收数量 */
    private BigDecimal tempReceiveQty;
    /** 请购数量 */
    private BigDecimal poQty;
    /** 排产明细 */
    private Map<LocalDate,BigDecimal> details;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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

    public BigDecimal getPlanQty() {
        return planQty;
    }

    public void setPlanQty(BigDecimal planQty) {
        this.planQty = planQty;
    }

    public BigDecimal getPullQty() {
        return pullQty;
    }

    public void setPullQty(BigDecimal pullQty) {
        this.pullQty = pullQty;
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

    public Map<LocalDate, BigDecimal> getDetails() {
        return details;
    }

    public void setDetails(Map<LocalDate, BigDecimal> details) {
        this.details = details;
    }

    public Long getOriginMaterialId() {
        return originMaterialId;
    }

    public void setOriginMaterialId(Long originMaterialId) {
        this.originMaterialId = originMaterialId;
    }

    public String getOriginMaterialCode() {
        return originMaterialCode;
    }

    public void setOriginMaterialCode(String originMaterialCode) {
        this.originMaterialCode = originMaterialCode;
    }

    public String getOriginMaterialSpec() {
        return originMaterialSpec;
    }

    public void setOriginMaterialSpec(String originMaterialSpec) {
        this.originMaterialSpec = originMaterialSpec;
    }

    public String getOriginMaterialName() {
        return originMaterialName;
    }

    public void setOriginMaterialName(String originMaterialName) {
        this.originMaterialName = originMaterialName;
    }

    public MaterialType getOriginMaterialType() {
        return originMaterialType;
    }

    public void setOriginMaterialType(MaterialType originMaterialType) {
        this.originMaterialType = originMaterialType;
    }

    public BigDecimal getBomQty() {
        return bomQty;
    }

    public void setBomQty(BigDecimal bomQty) {
        this.bomQty = bomQty;
    }
}
