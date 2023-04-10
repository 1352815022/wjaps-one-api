package com.donlim.aps.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * U9数据扩展字段(U9MoFinish)DTO类
 *
 * @author sei
 * @since 2023-04-08 11:27:31
 */
@ApiModel(description = "U9数据扩展字段DTO")
@Data
public class U9MoFinishDto extends BaseEntityDto {
    private static final long serialVersionUID = 729699186384623251L;
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

    /**
     * 物料编码
     */
    private String materialCode;




}
