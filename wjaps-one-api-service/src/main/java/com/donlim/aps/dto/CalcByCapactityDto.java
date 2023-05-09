package com.donlim.aps.dto;

import com.donlim.aps.entity.ApsOrderPlanDetail;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @Description:产能排产返回类
 * @Author: chenzhiquan
 * @Date: 2022/9/2.
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class CalcByCapactityDto {
    /**
     *排产明细
     */
    private List<ApsOrderPlanDetail> apsOrderPlanDetailList;
    /**
     *原有的排产明细
     */
    private List<ApsOrderPlanDetail>oldPlanList;
    /**
     * 标准产能
     */
    private BigDecimal standardQty;
    /**
     *
     */
    private LocalDate startDate;
}
