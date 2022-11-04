package com.donlim.aps.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @ClassName U9StockExport
 * @Description U9库存导出实体
 * @Author p09835
 * @Date 2022/5/10 9:13
 **/
@ApiModel(description = "U9库存导出实体")
public class U9StockExport implements Serializable {

    private static final long serialVersionUID = -7614613953298807091L;

    /**
     * 物料编码
     */
    @ApiModelProperty(value = "物料编码")
    private String materialCode;
    /**
     * 物料名称
     */
    @ApiModelProperty(value = "物料名称")
    private String materialName;
    /**
     * 物料规格
     */
    @ApiModelProperty(value = "物料规格")
    private String materialDesc;
    /**
     * 库存可用量
     */
    @ApiModelProperty(value = "库存可用量")
    private Integer storeQty;
    /**
     * 预留数量
     */
    @ApiModelProperty(value = "预留数量")
    private Integer reserveQty;
    /**
     * 实际库存
     */
    @ApiModelProperty(value = "实际库存")
    private Integer actualQty;
    /**
     * 单位
     */
    @ApiModelProperty(value = "单位")
    private String unit;
    /**
     * 仓库编码
     */
    @ApiModelProperty(value = "仓库编码")
    private String warehouseCode;
    /**
     * 仓库名称
     */
    @ApiModelProperty(value = "仓库名称")
    private String warehouseName;

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialDesc() {
        return materialDesc;
    }

    public void setMaterialDesc(String materialDesc) {
        this.materialDesc = materialDesc;
    }

    public Integer getStoreQty() {
        return storeQty;
    }

    public void setStoreQty(Integer storeQty) {
        this.storeQty = storeQty;
    }

    public Integer getReserveQty() {
        return reserveQty;
    }

    public void setReserveQty(Integer reserveQty) {
        this.reserveQty = reserveQty;
    }

    public Integer getActualQty() {
        return actualQty;
    }

    public void setActualQty(Integer actualQty) {
        this.actualQty = actualQty;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }
}
