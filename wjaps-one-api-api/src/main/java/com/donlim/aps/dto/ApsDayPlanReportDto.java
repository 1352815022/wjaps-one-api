package com.donlim.aps.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @Description:生产日报输出类
 * @Author: chenzhiquan
 * @Date: 2022/10/7.
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ApsDayPlanReportDto {
    private  String orderNo;
    private String materialCode;
    private String materialName;
    private String materialSpec;
    private String workGroupId;
    private String workLineId;
    private BigDecimal planQty;
    private LocalDate startDate;
    private LocalDate endDate;

}
