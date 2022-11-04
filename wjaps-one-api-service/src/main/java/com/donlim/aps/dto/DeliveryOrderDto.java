package com.donlim.aps.dto;

import lombok.Data;

import java.math.BigInteger;

/**
 * @Description:
 * @Author: chenzhiquan
 * @Date: 2022/7/13.
 */
@Data
public class DeliveryOrderDto {

    private BigInteger num;
    /**
     *
     */
    private String orderNo;
    /**
     * 产品型号
     */
    private String productModel;
}
