package com.donlim.aps.dto;

import com.changhong.sei.annotation.Remark;

/**
 * 排产订单状态枚举
 * @author yangjiateng
 */
public enum OrderStatusType {

    @Remark(value = "未下达")
    NoRelease,
    @Remark(value = "部分下达")
    Release_Part,
    @Remark(value = "已下达")
    Released,
    @Remark(value = "生产中")
    Producing,
    @Remark(value = "已完成")
    Completed,
    @Remark(value = "暂停")
    Stop,
    @Remark(value = "正常")
    Normal,

}
