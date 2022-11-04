package com.donlim.aps.dto.open;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description:
 * @Author: chenzhiquan
 * @Date: 2022/10/11.
 */
@Data
public class ApsPlanDetailDto {

        private BigDecimal planQty;
        private String materialCode;

}
