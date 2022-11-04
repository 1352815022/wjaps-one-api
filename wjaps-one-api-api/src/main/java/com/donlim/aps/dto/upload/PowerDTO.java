package com.donlim.aps.dto.upload;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName PowerDTO
 * @Description 喷粉、清洗配置导入实体
 * @Author p09835
 * @Date 2022/7/16 9:40
 **/
@Data
public class PowerDTO {

    /**
     * 料号
     */
    private String materialCode;

    /**
     * 产能
     */
    private String capacity;
    /**
     * 喷粉型号
     */
    private String powderModel;

    /**
     * 清洗面积
     */
    private BigDecimal washArea;

    /**
     * 喷粉面积
     */
    private BigDecimal powderArea;
    /**
     *
     */
    private String sortNo;
}
