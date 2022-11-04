package com.donlim.aps.vo;

import com.donlim.aps.entity.ApsOweMaterialSummary;
import com.donlim.aps.entity.ApsOweMaterialSummaryDetail;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName ApsOweMaterailVO
 * @Description 生成欠料前实体
 * @Author p09835
 * @Date 2022/6/15 15:20
 **/
public class ApsOweMaterialVO implements Serializable {

    private static final long serialVersionUID = 8401937868450340354L;

    private ApsOweMaterialSummary summary;

    private List<ApsOweMaterialSummaryDetail> summaryDetails;

    private List<ApsOweMaterialPlanVO> plan;

    public ApsOweMaterialSummary getSummary() {
        return summary;
    }

    public void setSummary(ApsOweMaterialSummary summary) {
        this.summary = summary;
    }

    public List<ApsOweMaterialSummaryDetail> getSummaryDetails() {
        return summaryDetails;
    }

    public void setSummaryDetails(List<ApsOweMaterialSummaryDetail> summaryDetails) {
        this.summaryDetails = summaryDetails;
    }

    public List<ApsOweMaterialPlanVO> getPlan() {
        return plan;
    }

    public void setPlan(List<ApsOweMaterialPlanVO> plan) {
        this.plan = plan;
    }

}
