package com.donlim.aps.dto;

import com.donlim.aps.entity.U9Purchase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @Description:计算子母料用量及前置期类
 * @Author: chenzhiquan
 * @Date: 2022/10/24.
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class CalcBomDto {
    private U9Purchase u9Purchase;
    private String docNo;
    private String materialId;
    private LocalDate deliveryStartDate;
    private LocalDate deliveryEndDate;
    private String materialCode;

    private String materialName;

    private String materialSpec;
    /**
     * 提前期
     */
    private LocalDate planDate;
    /**
     * 用量
     */
    private BigDecimal qty;
}
