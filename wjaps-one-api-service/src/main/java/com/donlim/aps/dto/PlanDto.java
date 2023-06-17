package com.donlim.aps.dto;

import com.donlim.aps.entity.ApsOrder;
import com.donlim.aps.entity.ApsOrderPlan;
import com.donlim.aps.entity.ApsOrderPlanDetail;
import lombok.Data;

@Data
public class PlanDto {
    private ApsOrderPlan apsOrderPlan;
    private ApsOrderPlanDetail apsOrderPlanDetail;
    private ApsOrder apsOrder;

    //构造方法
    public PlanDto() {
    }
    public PlanDto(ApsOrderPlan apsOrderPlan, ApsOrderPlanDetail apsOrderPlanDetail, ApsOrder apsOrder) {
        this.apsOrderPlan = apsOrderPlan;
        this.apsOrderPlanDetail = apsOrderPlanDetail;
        this.apsOrder = apsOrder;
    }

    public PlanDto(ApsOrderPlan apsOrderPlan, ApsOrderPlanDetail apsOrderPlanDetail) {
        this.apsOrderPlan = apsOrderPlan;
        this.apsOrderPlanDetail = apsOrderPlanDetail;
    }

}
