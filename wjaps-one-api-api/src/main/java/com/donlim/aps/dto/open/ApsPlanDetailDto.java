package com.donlim.aps.dto.open;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.tomcat.jni.Local;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @Description:
 * @Author: chenzhiquan
 * @Date: 2022/10/11.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ApsPlanDetailDto {
    private String id;
    private BigDecimal planQty;
    private String docNo;
    private String workGroup;
    private String line;
    private String moId;
    private Integer status;
    private LocalDate planDate;

}
