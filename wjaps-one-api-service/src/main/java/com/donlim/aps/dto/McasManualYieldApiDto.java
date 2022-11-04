package com.donlim.aps.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @Description:
 * @Author: chenzhiquan
 * @Date: 2022/6/15.
 */
@NoArgsConstructor
@Data
public class McasManualYieldApiDto {

    /**
     * table
     */
    private List<TableDTO> table;

    /**
     * TableDTO
     */
    @NoArgsConstructor
    @Data
    public static class TableDTO {
        /**
         * 提报日期
         */
        private LocalDate savedate;
        /**
         * 生产订单号
         */
        private String csocode;
        /**
         * 料号
         */
        @JsonProperty("cInvCode")
        private String cInvCode;
        /**
         * 品名
         */
        @JsonProperty("cInvName")
        private String cInvName;
        /**
         * 产量
         */
        private BigDecimal packqty;
        /**
         * 线别名称
         */
        private String organizename;
        /**
         * 线别编码
         */
        private String organizeid;
    }
}
