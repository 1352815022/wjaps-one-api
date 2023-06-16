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

    public ApsPlanDetailDto() {
    }
    public ApsPlanDetailDto(String id, BigDecimal planQty, LocalDate planDate, String docNo, String workGroup, String line, String moId, Integer status) {
        this.id = id;
        this.planQty = planQty;
        this.docNo = docNo;
        this.workGroup = workGroup;
        this.line = line;
        this.moId = moId;
        this.status = status;
        this.planDate = planDate;
    }

}
