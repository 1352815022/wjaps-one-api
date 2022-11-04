package com.donlim.aps.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @Description:冲压车间报工报表
 * @Author: chenzhiquan
 * @Date: 2022/6/23.
 */
@ApiModel(description = "冲压车间报工报表DTO")
@Data
@EqualsAndHashCode(callSuper = false)
public class StampingReportDto extends BaseEntityDto {

    /**
     * 报工日期
     */
    private LocalDate productionDate;
    /**
     * 生产车间
     */
    private String workGroupName;

    /**
     * 生产线
     */
    private String lineName;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 物料编码
     */
    private String materialCode;
    /**
     * 料品名
     */
    private String materialName;
    /**
     * 料规格
     */
    private String materialSpec;
    /**
     * 订单数
     */
    private BigDecimal orderQty;

    /**
     * 完成数量
     */
    private BigDecimal hasQty;
    /**
     * 欠数
     */
    private BigDecimal oweQty;
    /**
     * 已排数
     */
    private BigDecimal planQty;

    /**
     * SCM交期
     */
    private LocalDate scmDeliveryDate;
    /**
     *首工序
     */
    private BigDecimal firstProcessQty;
    /**
     *尾工序
     */
    private BigDecimal lastProcessQty;
    /**
     * 产能
     */
    private  BigDecimal capacity;
    /**
     *U9累计入库数量
     */
    private BigDecimal inStockQty;
    /**
     * 上月期末数量
     */
    private BigDecimal lastMonthQty;

}
