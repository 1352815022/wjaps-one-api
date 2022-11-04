package com.donlim.aps.entity.cust;

import com.changhong.sei.util.DateUtils;
import com.donlim.aps.dto.OrderStatusType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

/**
 * @ClassName OrderChangeCountVO
 * @Description scm变更记录实体
 * @Author p09835
 * @Date 2022/7/14 17:24
 **/
@Entity
public class OrderChangeCountVO implements Serializable {


    private static final long serialVersionUID = -1214210500895514170L;
    @Id
    private String id;
    /**
     * 车间
     */
    private String apsOrderWorkGroupName;
    /**
     * 班组
     */
    private String apsOrderWorkLineName;
    /**
     * 需求分类号
     */
    private String orderNo;
    /**
     * 采购单号
     */
    private String po;
    /**
     * 料号
     */
    private String materialCode;
    /**
     * 料名
     */
    private String materialName;
    /**
     * 规格
     */
    private String materialSpec;
    /**
     * 现送货数
     */
    private BigDecimal deliveryQty;
    /**
     * 旧送货数
     */
    private BigDecimal deliveryOldQty;
    /**
     * 原交期
     */
    private LocalDate deliveryStartDate;
    /**
     * 现交期
     */
    private LocalDate deliveryOldStartDate;
    /**
     * 排产状态
     */
    @Enumerated(EnumType.STRING)
    private OrderStatusType apsOrderStatus;
    /**
     * 客户
     */
    private String companyName;
    /**
     * 是否变更日期
     */
    private Boolean changeDateFlag;
    /**
     * 是否变更数量
     */
    private Boolean changeQtyFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApsOrderWorkGroupName() {
        return apsOrderWorkGroupName;
    }

    public void setApsOrderWorkGroupName(String apsOrderWorkGroupName) {
        this.apsOrderWorkGroupName = apsOrderWorkGroupName;
    }

    public String getApsOrderWorkLineName() {
        return apsOrderWorkLineName;
    }

    public void setApsOrderWorkLineName(String apsOrderWorkLineName) {
        this.apsOrderWorkLineName = apsOrderWorkLineName;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPo() {
        return po;
    }

    public void setPo(String po) {
        this.po = po;
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

    public BigDecimal getDeliveryQty() {
        return deliveryQty;
    }

    public void setDeliveryQty(BigDecimal deliveryQty) {
        this.deliveryQty = deliveryQty;
    }

    public BigDecimal getDeliveryOldQty() {
        return deliveryOldQty;
    }

    public void setDeliveryOldQty(BigDecimal deliveryOldQty) {
        this.deliveryOldQty = deliveryOldQty;
    }

    public LocalDate getDeliveryStartDate() {
        return deliveryStartDate;
    }

    public void setDeliveryStartDate(Date deliveryStartDate) {
        this.deliveryStartDate = DateUtils.date2LocalDate(deliveryStartDate);
    }

    public LocalDate getDeliveryOldStartDate() {
        return deliveryOldStartDate;
    }

    public void setDeliveryOldStartDate(Date deliveryOldStartDate) {
        this.deliveryOldStartDate = DateUtils.date2LocalDate(deliveryOldStartDate);
    }

    public OrderStatusType getApsOrderStatus() {
        return apsOrderStatus;
    }

    public void setApsOrderStatus(String apsOrderStatus) {
        this.apsOrderStatus = OrderStatusType.valueOf(apsOrderStatus);
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Boolean getChangeDateFlag() {
        return changeDateFlag;
    }

    public void setChangeDateFlag(Boolean changeDateFlag) {
        this.changeDateFlag = changeDateFlag;
    }

    public Boolean getChangeQtyFlag() {
        return changeQtyFlag;
    }

    public void setChangeQtyFlag(Boolean changeQtyFlag) {
        this.changeQtyFlag = changeQtyFlag;
    }
}
