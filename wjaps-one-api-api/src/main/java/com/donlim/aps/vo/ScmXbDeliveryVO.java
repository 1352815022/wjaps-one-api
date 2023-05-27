package com.donlim.aps.vo;

import lombok.Data;

import java.time.LocalDate;

/**
 * SCM送货单查询VO
 */
@Data
public class ScmXbDeliveryVO {
    /**
     * 开始时间
     */
    private LocalDate startDate;
    /**
     * 结束时间
     */
    private LocalDate endDate;
    /**
     * 单号
     */
    private String orderNo;
    /**
     * 料名
     */
    private String materialName;
    /**
     * 料号
     */
    private String materialCode;

}
