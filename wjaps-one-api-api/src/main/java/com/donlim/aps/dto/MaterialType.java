package com.donlim.aps.dto;

import com.changhong.sei.annotation.Remark;
import org.springframework.util.StringUtils;

public enum MaterialType {

    /** 制造件*/
    @Remark("制造件")
    Production("制造件"),
    /** 采购件*/
    @Remark("采购件")
    Purchase("采购件"),
    /** 委外件*/
    @Remark("委外件")
    OutSource("委外件"),
    ;

    MaterialType(String name) {
        this.name = name;
    }

    /**
     * 转换 可读性更高的字符串
     * @param str
     * @return
     */
    public static MaterialType transformType(String str){
        if (StringUtils.isEmpty(str)){
            return null;
        }
        switch (str){
            case "10":
                return Production;
            case "9":
                return Purchase;
            case "4":
                return OutSource;
            default:
                return null;
        }
    }


    private String name;

    public String getName() {
        return name;
    }
}
