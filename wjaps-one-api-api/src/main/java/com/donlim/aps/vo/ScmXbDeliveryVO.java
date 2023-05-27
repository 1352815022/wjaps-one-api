package com.donlim.aps.vo;

import lombok.Data;

import java.time.LocalDate;

/**
 * SCM送货单查询VO
 */
@Data
public class ScmXbDeliveryVO {
    private LocalDate startDate;
    private LocalDate endDate;
    private String orderNo;
    private String materialName;
    private String materialCode;

}
