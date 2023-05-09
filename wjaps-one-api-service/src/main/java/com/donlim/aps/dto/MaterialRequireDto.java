package com.donlim.aps.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @Description:物料需求类
 * @Author: chenzhiquan
 * @Date: 2022/10/10.
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class MaterialRequireDto {
    /**
     * 料号
     */
    private String materialCode;
    /**
     * 名称
     */
    private String materialName;
    /**
     * 规格
     */
    private String materialSpec;
    /**
     * 需求分类号
     */
    private String orderNo;
    /**
     * 需求数量
     */
    private BigDecimal requireQty;
    /**
     * 需求日期
     */
    private LocalDate requireDate;
    /**
     * 销售单号
     */
    private String Po;
}


