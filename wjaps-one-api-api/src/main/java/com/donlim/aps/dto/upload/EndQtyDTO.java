package com.donlim.aps.dto.upload;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName EndQtyDTO
 * @Description 上月期末数导入实体
 * @Author p09835
 * @Date 2022/7/16 8:57
 **/
@Data
public class EndQtyDTO {

    /**
     * 料号
     */
    private String materialCode;

    /**
     * 上月期末数
     */
    private BigDecimal endQty;

    private Boolean calc;
}


