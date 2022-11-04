package com.donlim.aps.dto;

import com.changhong.sei.annotation.Remark;

/**
 * 订单类型
 */
public enum ApsOrderType {
    /**
     * 内排
     */
    @Remark("内排")
    INNER,
    /**
     * 委外
     */
    @Remark("委外")
    OUTER,
}
