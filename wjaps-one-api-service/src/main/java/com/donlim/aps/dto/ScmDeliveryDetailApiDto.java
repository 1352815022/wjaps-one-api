package com.donlim.aps.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @Description:SCM送货计划明细
 * @Author: chenzhiquan
 * @Date: 2022/6/21.
 */
@NoArgsConstructor
@Data
public class ScmDeliveryDetailApiDto {

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
        private Integer id;
        /**
         * deliveryQty
         */
        @JsonProperty("DeliveryQty")
        private BigDecimal DeliveryQty;
        /**
         * sumqty
         */
        private Double sumqty;
        /**
         * unsumqty
         */
        private Double unsumqty;
        /**
         * rnm
         */
        private Integer rnm;
        /**
         * workingDay
         */
        private String workingDay;
        /**
         * startDate
         */
        private String startDate;
        /**
         * workingQty
         */
        private Integer workingQty;
        /**
         * 送货日期
         */
        @JsonProperty("DeliveryDate")
        private LocalDate deliveryDate;
        /**
         * deliveryQty2
         */
        private Double deliveryQty2;
        /**
         * 采购单号
         */
        @JsonProperty("PurchaseOrder")
        private String purchaseOrder;
        /**
         * 物料ID
         */
        @JsonProperty("ItemID")
        private String itemID;
        /**
         * orderNo
         */
        @JsonProperty("OrderNo")
        private String orderNo;
        /**
         * supplierCode
         */
        private String supplierCode;
        /**
         * supplierShortName
         */
        private String supplierShortName;
        /**
         * 物料编码
         */
        @JsonProperty("ItemCode")
        private String itemCode;
        /**
         * itemName
         */
        private String itemName;
        /**
         * companyName
         */
        @JsonProperty("CompanyName")
        private String companyName;
        /**
         * companyCode
         */
        private String companyCode;
        /**
         * productModel
         */
        private String productModel;
        /**
         * specs
         */
        private String specs;
        /**
         * unitName
         */
        private String unitName;
        /**
         * qty
         */
        private Integer qty;
        /**
         * purchaseOrderMan
         */
        private String purchaseOrderMan;
        /**
         * docLineno
         */
        private Integer docLineno;
        /**
         * reqQtyTU
         */
        private Double reqQtyTU;
        /**
         * remark
         */
        private String remark;
        /**
         * createdOn
         */
        private String createdOn;
        /**
         * status
         */
        private Integer status;
        /**
         * purchaseorderDeliveryDate
         */
        private String purchaseorderDeliveryDate;
        /**
         * purchaseOrderOrgName
         */
        private String purchaseOrderOrgName;
        /**
         * purchaseOrderManCode
         */
        private String purchaseOrderManCode;
        /**
         * pOLineID
         */
        @JsonProperty("POLineID")
        private Long pOLineID;
        /**
         * u9org
         */
        private Long u9org;
        /**
         * plandeliquantity
         */
        private Double plandeliquantity;
        /**
         * nodeliveryqty
         */
        private Double nodeliveryqty;
        /**
         * sourcebillno
         */
        private String sourcebillno;
        /**
         * orderdate
         */
        private String orderdate;
    }
}
