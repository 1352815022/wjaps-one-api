package com.donlim.aps.entity.cust;

import com.donlim.aps.entity.ApsOrder;
import com.donlim.aps.entity.U9Material;
import com.donlim.aps.entity.U9ProduceOrder;
import lombok.Data;

/**
 * @ClassName OrderAndU9
 * @Description 订单 与 u9 订单关联表
 * @Author p09835
 * @Date 2022/5/30 16:24
 **/
@Data
public class OrderAndU9 {

    private ApsOrder apsOrder;

    private U9ProduceOrder u9ProduceOrder;

    private U9Material u9Material;

    public OrderAndU9(ApsOrder apsOrder, U9ProduceOrder u9ProduceOrder) {
        this.apsOrder = apsOrder;
        this.u9ProduceOrder = u9ProduceOrder;
    }

    public OrderAndU9(ApsOrder apsOrder, U9ProduceOrder u9ProduceOrder, U9Material u9Material) {
        this.apsOrder = apsOrder;
        this.u9ProduceOrder = u9ProduceOrder;
        this.u9Material = u9Material;
    }


}
