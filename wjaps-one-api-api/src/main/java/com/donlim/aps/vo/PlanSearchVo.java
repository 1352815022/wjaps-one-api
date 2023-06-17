package com.donlim.aps.vo;

import lombok.Data;

import java.time.LocalDate;

/**
 * 生产计划查询VO
 */
@Data
public class PlanSearchVo {

    private LocalDate startDate;
    private LocalDate endDate;
    private String orderNo;

    private String materialName;

    private String materialCode;

    private String materialSpec;

    private String workGroupId;

    private String lineId;

}
