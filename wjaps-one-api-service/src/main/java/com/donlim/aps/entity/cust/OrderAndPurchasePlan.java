package com.donlim.aps.entity.cust;

import com.donlim.aps.entity.ApsOrder;
import com.donlim.aps.entity.ApsPurchasePlan;

import java.io.Serializable;

/**
 * @ClassName OrderAndPurchasePlan
 * @Description 工单与采购计划实体类
 * @Author p09835
 * @Date 2022/6/13 15:00
 **/
public class OrderAndPurchasePlan implements Serializable {

    private static final long serialVersionUID = -7662231014817053956L;
    private ApsOrder apsOrder;

    private ApsPurchasePlan apsPurchasePlan;

    public OrderAndPurchasePlan(ApsOrder apsOrder, ApsPurchasePlan apsPurchasePlan) {
        this.apsOrder = apsOrder;
        this.apsPurchasePlan = apsPurchasePlan;
    }

    public ApsOrder getApsOrder() {
        return apsOrder;
    }

    public void setApsOrder(ApsOrder apsOrder) {
        this.apsOrder = apsOrder;
    }

    public ApsPurchasePlan getApsPurchasePlan() {
        return apsPurchasePlan;
    }

    public void setApsPurchasePlan(ApsPurchasePlan apsPurchasePlan) {
        this.apsPurchasePlan = apsPurchasePlan;
    }
}
