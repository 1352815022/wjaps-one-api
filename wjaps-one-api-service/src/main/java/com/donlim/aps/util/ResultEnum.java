package com.donlim.aps.util;

/**
 * 错误提示枚举类
 * @author p09835
 */
public enum ResultEnum {

    /**
     * 排班日历重复
     */
    WORKING_TIMES_DUPLICATE("排班范围中已存在排班记录，请检查！"),

    PLAN_QTY_ERROR("总排产数量不能大于生产数量"),

    NOT_FOUND_MATERIAL("没找到对应的物料，物料Id:"),

    NOT_FIND_PLAN_ID("保存计划失败，保存数据不合法"),

    NO_DEFINE_CAPACITY("下达失败，请先配置料品产能"),

    ;


    private String msg;

    ResultEnum(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
