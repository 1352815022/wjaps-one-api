package com.donlim.aps.entity.cust;

import com.donlim.aps.entity.U9MoPickList;
import com.donlim.aps.entity.U9ProduceOrder;

import java.io.Serializable;

/**
 * @ClassName U9ProduceOrderAndMoPick
 * @Description U9工单与备料表关联实体
 * @Author p09835
 * @Date 2022/6/15 9:35
 **/
public class U9ProduceOrderAndMoPick  implements Serializable {

    private static final long serialVersionUID = 2498595923940839535L;
    private U9ProduceOrder u9ProduceOrder;

    private U9MoPickList u9MoPickList;

    public U9ProduceOrderAndMoPick(U9ProduceOrder u9ProduceOrder, U9MoPickList u9MoPickList) {
        this.u9ProduceOrder = u9ProduceOrder;
        this.u9MoPickList = u9MoPickList;
    }

    public U9ProduceOrder getU9ProduceOrder() {
        return u9ProduceOrder;
    }

    public void setU9ProduceOrder(U9ProduceOrder u9ProduceOrder) {
        this.u9ProduceOrder = u9ProduceOrder;
    }

    public U9MoPickList getU9MoPickList() {
        return u9MoPickList;
    }

    public void setU9MoPickList(U9MoPickList u9MoPickList) {
        this.u9MoPickList = u9MoPickList;
    }
}
