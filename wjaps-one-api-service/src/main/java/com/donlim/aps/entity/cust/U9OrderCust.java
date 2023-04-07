package com.donlim.aps.entity.cust;

import com.donlim.aps.entity.ApsOrderExt;
import com.donlim.aps.entity.ScmXbDelivery;
import com.donlim.aps.entity.U9Material;
import com.donlim.aps.entity.U9ProduceOrder;

import java.io.Serializable;

/**
 * @ClassName U9OrderCust
 * @Description TODO
 * @Author p09835
 * @Date 2022/5/19 14:41
 **/
public class U9OrderCust implements Serializable {
    private static final long serialVersionUID = -6992212738122169728L;

    private U9ProduceOrder u9ProduceOrder;

    private ScmXbDelivery scmXbDelivery;

    private U9Material u9Material;

    private ApsOrderExt apsOrderExt;

    public U9OrderCust(U9ProduceOrder u9ProduceOrder, ScmXbDelivery scmXbDelivery, U9Material u9Material) {
        this.u9ProduceOrder = u9ProduceOrder;
        this.scmXbDelivery = scmXbDelivery;
        this.u9Material = u9Material;
    }

    public U9OrderCust(U9ProduceOrder u9ProduceOrder, ScmXbDelivery scmXbDelivery, U9Material u9Material,ApsOrderExt apsOrderExt) {
        this.u9ProduceOrder = u9ProduceOrder;
        this.scmXbDelivery = scmXbDelivery;
        this.u9Material = u9Material;
        this.apsOrderExt = apsOrderExt;
    }

    public U9ProduceOrder getU9ProduceOrder() {
        return u9ProduceOrder;
    }

    public void setU9ProduceOrder(U9ProduceOrder u9ProduceOrder) {
        this.u9ProduceOrder = u9ProduceOrder;
    }

    public ScmXbDelivery getScmXbDelivery() {
        return scmXbDelivery;
    }

    public void setScmXbDelivery(ScmXbDelivery scmXbDelivery) {
        this.scmXbDelivery = scmXbDelivery;
    }

    public U9Material getU9Material() {
        return u9Material;
    }

    public void setU9Material(U9Material u9Material) {
        this.u9Material = u9Material;
    }

    public ApsOrderExt getApsOrderExt() {
        return apsOrderExt;
    }

    public void setApsOrderExt(ApsOrderExt apsOrderExt) {
        this.apsOrderExt = apsOrderExt;
    }
}
