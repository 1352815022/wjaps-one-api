package com.donlim.aps.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.changhong.sei.core.entity.BaseAuditableEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 订单齐套(ApsOrderComplete)实体类
 *
 * @author sei
 * @since 2022-07-13 13:59:11
 */
@Entity
@Table(name = "aps_order_complete")
@DynamicInsert
@DynamicUpdate
@ExcelIgnoreUnannotated
public class ApsOrderComplete extends BaseAuditableEntity implements Serializable {
    private static final long serialVersionUID = -46972485270352301L;
    /**
     * 公司名
     */
    @Column(name = "company")
    @ExcelProperty("公司名")
    private String company;
    /**
     * 需求分类号
     */
    @Column(name = "order_no")
    @ExcelProperty("需求分类号")
    private String orderNo;
    /**
     * 产品型号
     */
    @Column(name = "product_model")
    @ExcelProperty("产品型号")
    private String productModel;
    /**
     * 订单个数
     */
    @Column(name = "order_num")
    @ExcelProperty("订单个数")
    private Integer orderNum;
    /**
     * 订单齐套个数
     */
    @Column(name = "complete_num")
    @ExcelProperty("订单齐套个数")
    private Integer completeNum;
    /**
     * 齐套率
     */
    @Column(name = "complete_percent")
    @ExcelProperty("齐套率")
    private String completePercent;
    /**
     * 租户代码
     */
    @Column(name = "tenant_code")
    private String tenantCode;


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getCompleteNum() {
        return completeNum;
    }

    public void setCompleteNum(Integer completeNum) {
        this.completeNum = completeNum;
    }

    public String getCompletePercent() {
        return completePercent;
    }

    public void setCompletePercent(String completePercent) {
        this.completePercent = completePercent;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

    /**
     * 映射出明细表
     */
    @OneToMany(targetEntity = ApsOrderDetailComplete.class)
    @JoinColumn(name = "parent_id",insertable = false,updatable = false)
    private List<ApsOrderDetailComplete> orderPlanDetails;


}
