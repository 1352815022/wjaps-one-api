package com.donlim.aps.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @Description:mcas报产数据
 * @Author: chenzhiquan
 * @Date: 2022/6/14.
 */
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class McasYieldApiDto {

    /**
     * table
     */
    private List<TableDTO> table;

    /**
     * TableDTO
     */
    @NoArgsConstructor
    @Data
    @EqualsAndHashCode(callSuper=false)
    public static class TableDTO {
        /**
         * mcasID
         */
        @JsonProperty("McasID")
        private Integer mcasID;
        /**
         * companyCode
         */
        @JsonProperty("CompanyCode")
        private String companyCode;
        /**
         * organizename
         */
        private String organizename;
        /**
         * organizename1
         */

        private String organizename1;
        /**
         * mcasDate
         */
        @JsonProperty("McasDate")
        private LocalDate mcasDate;
        /**
         * code
         */
        @JsonProperty("Code")
        private String code;
        /**
         * name
         */
        @JsonProperty("Name")
        private String name;
        /**
         * csocode
         */
        @JsonProperty("Csocode")
        private String csocode;
        /**
         * cinvcode
         */
        @JsonProperty("Cinvcode")
        private String cinvcode;
        /**
         * cinvname
         */
        @JsonProperty("Cinvname")
        private String cinvname;
        /**
         * process
         */
        @JsonProperty("Process")
        private String process;
        /**
         * scanQty
         */
        @JsonProperty("ScanQty")
        private BigDecimal scanQty;
        /**
         * lineGroupName
         */
        @JsonProperty("LineGroupName")
        private String lineGroupName;
    }
}
