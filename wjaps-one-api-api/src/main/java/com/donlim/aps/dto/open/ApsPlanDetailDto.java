package com.donlim.aps.dto.open;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * @Description:
 * @Author: chenzhiquan
 * @Date: 2022/10/11.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ApsPlanDetailDto {

    private BigDecimal planQty;
    private String docNo;
    private String workGroup;
    private String line;
    private String moId;
    private Integer status;

}
