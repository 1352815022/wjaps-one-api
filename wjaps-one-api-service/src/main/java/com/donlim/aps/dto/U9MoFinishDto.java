package com.donlim.aps.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import com.changhong.sei.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

/**
 * U9数据扩展字段(U9MoFinish)DTO类
 *
 * @author sei
 * @since 2023-04-08 11:27:31
 */
@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(description = "U9数据扩展字段DTO")
public class U9MoFinishDto extends BaseEntityDto {
    private static final long serialVersionUID = 729699186384623251L;
    /**
     * 工单号
     */
    @ApiModelProperty(value = "工单号")
    private String orderNo;
    /**
     * 工单号
     */
    @ApiModelProperty(value = "料号")
    private String materialCode;
    /**
     * 工单号
     */
    @ApiModelProperty(value = "名称")
    private String materialName;
    /**
     * 已完成数量
     */
    @ApiModelProperty(value = "已完成数量")
    private BigDecimal finishQty;
    /**
     * 完工日期
     */
    @ApiModelProperty(value = "完工日期")
    @JsonFormat(pattern = DateUtils.DEFAULT_DATE_FORMAT)
    private LocalDate finishDate;





}
