package com.donlim.aps.dto.upload;

import java.math.BigDecimal;

/**
 * @ClassName EndQtyDTO
 * @Description 上月期末数导入实体
 * @Author p09835
 * @Date 2022/7/16 8:57
 **/
public class EndQtyDTO {

    /**
     * 料号
     */
    private String materialCode;

    /**
     * 上月期末数
     */
    private BigDecimal endQty;

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public BigDecimal getEndQty() {
        return endQty;
    }

    public void setEndQty(BigDecimal endQty) {
        this.endQty = endQty;
    }
}
