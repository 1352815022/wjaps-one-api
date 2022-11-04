package com.donlim.aps.entity.cust;

import com.donlim.aps.entity.ApsOrder;
import com.donlim.aps.entity.ScmXbDelivery;
import com.donlim.aps.entity.U9Material;
import com.donlim.aps.entity.U9ProduceOrder;
import lombok.Data;

/**
 * @ClassName OrderAndScm
 * @Description 内部待排与scm送货单与U9订单关联实体
 * @Author p09835
 * @Date 2022/5/26 10:22
 **/
@Data
public class OrderAndScm {

    private ApsOrder apsOrder;

    private ScmXbDelivery scmXbDelivery;

    private U9ProduceOrder u9ProduceOrder;

    private U9Material u9Material;

    public OrderAndScm(ApsOrder apsOrder, ScmXbDelivery scmXbDelivery, U9ProduceOrder u9ProduceOrder, U9Material u9Material) {
        this.apsOrder = apsOrder;
        this.scmXbDelivery = scmXbDelivery;
        this.u9ProduceOrder = u9ProduceOrder;
        this.u9Material = u9Material;
    }

    public OrderAndScm(ApsOrder apsOrder, ScmXbDelivery scmXbDelivery, U9ProduceOrder u9ProduceOrder) {
        this.apsOrder = apsOrder;
        this.scmXbDelivery = scmXbDelivery;
        this.u9ProduceOrder = u9ProduceOrder;
    }

    public OrderAndScm(ApsOrder apsOrder, ScmXbDelivery scmXbDelivery) {
        this.apsOrder = apsOrder;
        this.scmXbDelivery = scmXbDelivery;
    }

    public OrderAndScm(ApsOrder apsOrder, ScmXbDelivery scmXbDelivery, U9Material u9Material) {
        this.apsOrder = apsOrder;
        this.scmXbDelivery = scmXbDelivery;
        this.u9Material = u9Material;
    }
}
