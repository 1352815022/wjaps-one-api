package com.donlim.aps.util;

/**
 * @Description:获取SCM编码
 * @Author: chenzhiquan
 * @Date: 2022/7/7.
 */
public enum CompanyEnum {
    /**
     * 五金二SCM编码
     */
    WJ2_SCM("0BS20","五金件二公司"),
    /**
     * 五金一SCM编码
     */
    WJ1_SCM("0wj10","五金件一公司"),
    /**
     * 五金二MCAS编码
     */
    WJ2_MCAS("01064721","五金件二公司"),
    /**
     *五金一MCAS编码
     */
    WJ1_MCAS("01067903","五金件一公司");
    private String code;
    private String name;

    CompanyEnum(String code,String name) {
        this.code = code;
        this.name=name;
    }

    public String getCode() {
        return code;
    }
    public String getName(){
        return name;
    }
}
