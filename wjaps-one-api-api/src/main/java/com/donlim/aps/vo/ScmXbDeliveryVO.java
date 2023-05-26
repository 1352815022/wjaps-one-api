package com.donlim.aps.vo;

import lombok.Data;

import java.time.LocalDate;

/**
 * SCM送货单查询VO
 */
@Data
public class ScmXbDeliveryVO {
    private String startDate;
    private String endDate;
    private String orderNo;
}
