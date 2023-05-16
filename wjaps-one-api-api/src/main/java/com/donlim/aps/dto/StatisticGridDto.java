package com.donlim.aps.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description:网络控件（看板）
 * @Author: chenzhiquan
 * @Date: 2023/5/9.
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class StatisticGridDto {
    /**
     * 标题
     */
    private String title;
    /**
     * 颜色
     */
    private String color;
    /**
     * 链接
     */
    private String linkedUrl;
    /**
     * 图标类别
     */
    private String iconType;
    /**
     * 数值
     */
    private String value;

    private Integer precision;

    public StatisticGridDto(){

    }

    public StatisticGridDto(String title,String value){
        this.title=title;
        this.value=value;
        this.linkedUrl="/";
        this.precision=0;

    }
}
