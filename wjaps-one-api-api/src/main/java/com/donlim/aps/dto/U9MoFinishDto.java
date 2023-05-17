package com.donlim.aps.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * U9数据扩展字段(U9MoFinish)DTO类
 *
 * @author sei
 * @since 2023-05-17 09:14:17
 */
@ApiModel(description = "U9数据扩展字段DTO")
public class U9MoFinishDto extends BaseEntityDto {
    private static final long serialVersionUID = -50742054436341458L;
    /**
     * 工单号
     */
    @ApiModelProperty(value = "工单号")
    private String orderNo;
    /**
     * 已完成数量
     */
    @ApiModelProperty(value = "已完成数量")
    private Double finishQty;
    /**
     * 完工日期
     */
    @ApiModelProperty(value = "完工日期")
    private Date finishDate;


    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Double getFinishQty() {
        return finishQty;
    }

    public void setFinishQty(Double finishQty) {
        this.finishQty = finishQty;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

}
