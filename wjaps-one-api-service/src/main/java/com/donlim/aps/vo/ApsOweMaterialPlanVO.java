package com.donlim.aps.vo;

import com.donlim.aps.entity.ApsOweMaterialPlan;
import com.donlim.aps.entity.ApsOweMaterialPlanDetail;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName ApsOweMaterialPlanVO
 * @Description 欠料跟踪计划明细实体类
 * @Author p09835
 * @Date 2022/6/15 15:27
 **/
public class ApsOweMaterialPlanVO implements Serializable {

    private static final long serialVersionUID = 7292731163272705206L;

    private ApsOweMaterialPlan plan;

    private List<ApsOweMaterialPlanDetail> planDetails;

    public ApsOweMaterialPlan getPlan() {
        return plan;
    }

    public void setPlan(ApsOweMaterialPlan plan) {
        this.plan = plan;
    }

    public List<ApsOweMaterialPlanDetail> getPlanDetails() {
        return planDetails;
    }

    public void setPlanDetails(List<ApsOweMaterialPlanDetail> planDetails) {
        this.planDetails = planDetails;
    }
}
