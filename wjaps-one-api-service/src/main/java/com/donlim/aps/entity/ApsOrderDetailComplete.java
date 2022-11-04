package com.donlim.aps.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.changhong.sei.core.entity.BaseAuditableEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单齐套(ApsOrderDetailComplete)实体类
 *
 * @author sei
 * @since 2022-07-14 11:42:43
 */
@Entity
@Table(name = "aps_order_detail_complete")
@DynamicInsert
@DynamicUpdate
@ExcelIgnoreUnannotated
public class ApsOrderDetailComplete extends BaseAuditableEntity implements Serializable {
    private static final long serialVersionUID = 516552174124485918L;

    @Column(name = "parent_id")
    private String parentId;
    /**
     * 工单号
     */
    @Column(name = "product_order")
    @ExcelProperty("工单号")
    private String productOrder;
    /**
     * 料号
     */
    @Column(name = "material_code")
    @ExcelProperty("料号")
    private String materialCode;
    /**
     * 品名
     */
    @Column(name = "material_name")
    @ExcelProperty("品名")
    private String materialName;
    /**
     * 产品型号
     */
    @Column(name = "product_model")
    @ExcelProperty("产品型号")
    private String productModel;
    /**
     * 需求数
     */
    @Column(name = "require_qty")
    @ExcelProperty("需求数")
    private BigDecimal requireQty;
    /**
     * 完成数
     */
    @Column(name = "finish_qty")
    @ExcelProperty("完成数")
    private BigDecimal finishQty;
    /**
     * 齐套百分比
     */
    @Column(name = "complete_percent")
    @ExcelProperty("齐套百分比")
    private String completePercent;
    /**
     * 需求分类号
     */
    @Column(name = "order_no")
    @ExcelProperty("需求分类号")
    private String orderNo;
    /**
     * 租户代码
     */
    @Column(name = "tenant_code")
    private String tenantCode;


    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getProductOrder() {
        return productOrder;
    }

    public void setProductOrder(String productOrder) {
        this.productOrder = productOrder;
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

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public BigDecimal getRequireQty() {
        return requireQty;
    }

    public void setRequireQty(BigDecimal requireQty) {
        this.requireQty = requireQty;
    }

    public BigDecimal getFinishQty() {
        return finishQty;
    }

    public void setFinishQty(BigDecimal finishQty) {
        this.finishQty = finishQty;
    }

    public String getCompletePercent() {
        return completePercent;
    }

    public void setCompletePercent(String completePercent) {
        this.completePercent = completePercent;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

}
