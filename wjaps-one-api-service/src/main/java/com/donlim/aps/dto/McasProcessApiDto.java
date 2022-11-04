package com.donlim.aps.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: chenzhiquan
 * @Date: 2022/6/16.
 */
@NoArgsConstructor
@Data
public class McasProcessApiDto {

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
         * id
         */
        @JsonProperty("ID")
        private long id;
        /**
         * 公司名称
         */
        @JsonProperty("companyName")
        private String companyName;
        /**
         * 公司编码
         */
        private String organizeid;
        /**
         * 拉线名称
         */
        private String organizename;
        /**
         * 品名
         */
        @JsonProperty("CinvName")
        private String cinvName;
        /**
         * itemName
         */
        @JsonProperty("ItemName")
        private String itemName;
        /**
         * 工序排序
         */
        @JsonProperty("SortNo")
        private Integer sortNo;
        /**
         * 工序名
         */
        @JsonProperty("Process")
        private String process;
        /**
         * 拉线编码
         */
        @JsonProperty("Organizeid1")
        private String organizeid1;
        /**
         * 创建时间
         */
        @JsonProperty("CreateDate")
        private Date createDate;
    }
}
