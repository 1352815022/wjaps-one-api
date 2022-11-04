package com.donlim.aps.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @Description:SCM送货计划
 * @Author: chenzhiquan
 * @Date: 2022/5/17.
 */
@NoArgsConstructor
@Data
public class ScmDeliveryApiDto {
    /**
     * table
     */
    @JsonProperty("Table")
    private List<TableDTO> table;



    /**
     * TableDTO
     */
    @NoArgsConstructor
    @Data
    public static class TableDTO {
        /**
         * pOLineID
         */
        @JsonProperty("POLineID")
        private Long pOLineID;
        /**
         * u9org
         */
        @JsonProperty("u9org")
        private Long u9org;
        /**
         * purchaseOrderManCode
         */
        @JsonProperty("purchaseOrderManCode")
        private String purchaseOrderManCode;
        /**
         * purchaseorderman
         */
        @JsonProperty("purchaseorderman")
        private String purchaseorderman;
        /**
         * supplierCode
         */
        @JsonProperty("Supplier_Code")
        private String supplierCode;
        /**
         * supplierShortName
         */
        @JsonProperty("SupplierShortName")
        private String supplierShortName;
        /**
         * purchaseOrder
         */
        @JsonProperty("PurchaseOrder")
        private String purchaseOrder;
        /**
         * docLineno
         */
        @JsonProperty("DocLineno")
        private Integer docLineno;
        /**
         * companyname
         */
        @JsonProperty("Companyname")
        private String companyname;
        /**
         * orderno
         */
        @JsonProperty("orderno")
        private String orderno;
        /**
         * productModel
         */
        @JsonProperty("ProductModel")
        private String productModel;
        /**
         * itemCode
         */
        @JsonProperty("ItemCode")
        private String itemCode;
        /**
         * itemName
         */
        @JsonProperty("ItemName")
        private String itemName;
        /**
         * specs
         */
        @JsonProperty("SPECS")
        private String specs;
        /**
         * unitName
         */
        @JsonProperty("UnitName")
        private String unitName;
        /**
         * deliveryqty1
         */
        @JsonProperty("Deliveryqty1")
        private BigDecimal deliveryqty1;
        /**
         * deliveryQty
         */
        @JsonProperty("DeliveryQty")
        private BigDecimal deliveryQty;
        /**
         * begDate
         */
        @JsonProperty("BegDate")
        private LocalDate begDate;
        /**
         * endDate
         */
        @JsonProperty("EndDate")
        private LocalDate endDate;
        /**
         * gyslb
         */
        @JsonProperty("gyslb")
        private Integer gyslb;
        /**
         * totalArriveQtyCU
         */
        @JsonProperty("TotalArriveQtyCU")
        private BigDecimal totalArriveQtyCU;
        /**
         * audittime
         */
        @JsonProperty("audittime")
        private String audittime;
        /**
         * nodeliveryqty
         */
        @JsonProperty("nodeliveryqty")
        private BigDecimal nodeliveryqty;



    }
}
