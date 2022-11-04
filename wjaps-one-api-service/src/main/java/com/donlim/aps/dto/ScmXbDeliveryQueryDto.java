package com.donlim.aps.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * @Description：
 * @Author: chenzhiquan
 * @Date: 2022/10/12.
 */
@Data
public class ScmXbDeliveryQueryDto {
    private String orderNo;
    /**
     * 料号
     */
    private String materialCode;
    /**
     * 料品名称
     */
    private String materialName;
    /**
     * 料品规格
     */
    private String materialSpec;

    private LocalDate startDate;
    private LocalDate endDate;
    private String supplierCode;

}
