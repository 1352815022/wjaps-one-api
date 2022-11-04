package com.donlim.aps.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * @Description:一级APS排产数据
 * @Author: chenzhiquan
 * @Date: 2022/7/22.
 */
@NoArgsConstructor
@Data
public class ApsPlanApiDto {
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
         * manualSchedulingID
         */
        private Integer manualSchedulingID;
        /**
         * 需求分类号
         */
        @JsonProperty("OrderNo")
        private String orderNo;
        /**
         * productCapacity
         */
        private Object productCapacity;
        /**
         * schedulingQty
         */
        private Integer schedulingQty;
        /**
         * sortLineCode
         */
        private String sortLineCode;
        /**
         * 开始时间
         */
        @JsonProperty("StartDate")
        private String startDate;
        /**
         * 结束日期
         */
        @JsonProperty("EndDate")
        private String endDate;
        /**
         * 生产日期
         */
        @JsonProperty("WorkingDay")
        private LocalDate workingDay;
        /**
         * 生产数量
         */
        @JsonProperty("WorkingQty")
        private Integer workingQty;
        /**
         * remark
         */
        private String remark;
        /**
         * qtyStatusName1
         */
        private Object qtyStatusName1;
        /**
         * qtyStatusName2
         */
        private String qtyStatusName2;
        /**
         * qtyStatusName3
         */
        private Object qtyStatusName3;
        /**
         * isLocked
         */
        private Boolean isLocked;
        /**
         * pCProductCapacity
         */
        private Integer pCProductCapacity;
        /**
         * 总数
         */
        private Integer orderNum;
        /**
         * createMan
         */
        private String createMan;
        /**
         * createDate
         */
        private String createDate;
        /**
         * modifyMan
         */
        private String modifyMan;
        /**
         * modifyDate
         */
        private String modifyDate;
        /**
         * 一级单位名称
         */
        private String companyName;
        /**
         * lineMan
         */
        private String lineMan;
        /**
         * overseasOrderNo
         */
        private String overseasOrderNo;
        /**
         * productModel
         */
        private String productModel;
        /**
         * overseasQty
         */
        private Integer overseasQty;
        /**
         * productionQty
         */
        private Object productionQty;
        /**
         * productionQtyDate
         */
        private Object productionQtyDate;
        /**
         * oweQty
         */
        private Integer oweQty;
        /**
         * firstLocked
         */
        private Object firstLocked;
        /**
         * secondLocked
         */
        private Object secondLocked;
        /**
         * seller
         */
        private String seller;
        /**
         * safeEnum
         */
        private String safeEnum;
        /**
         * color
         */
        private String color;
        /**
         * sylg
         */
        private Object sylg;
        /**
         * saleTo
         */
        private String saleTo;
        /**
         * cDate
         */
        private String cDate;
        /**
         * companyCode
         */
        private String companyCode;
        /**
         * qty
         */
        private Integer qty;
        /**
         * oldSortLineCode
         */
        private String oldSortLineCode;
        /**
         * orderType
         */
        private Integer orderType;
        /**
         * productName
         */
        private String productName;
        /**
         * lineCode
         */
        private String lineCode;
        /**
         * shopCode
         */
        private String shopCode;
        /**
         * isPCConfirm
         */
        private Integer isPCConfirm;
        /**
         * status
         */
        private Integer status;
        /**
         * firstStartDate
         */
        private String firstStartDate;
        /**
         * customerCode
         */
        private String customerCode;
        /**
         * customerModel
         */
        private String customerModel;
        /**
         * sellerCode
         */
        private String sellerCode;
        /**
         * floor
         */
        private Object floor;
        /**
         * packqty
         */
        private Object packqty;
        /**
         * companySort
         */
        private String companySort;
        /**
         * u9Org
         */
        private Long u9Org;
        /**
         * bOMCreateDate
         */
        private String bOMCreateDate;
    }
}
