package com.donlim.aps.util;

import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;

/**
 * @ClassName NumberUtils
 * @Description 数值处理工具类
 * @Author p09835
 * @Date 2022/5/26 8:53
 **/
public class NumberUtils {

    public static Long getLongValue(String str){
        if (StringUtils.isEmpty(str)){
            return null;
        }
        return Long.valueOf(str);
    }

    public static BigDecimal getBigDecimalValue(BigDecimal bigDecimal){
        if (bigDecimal == null){
            return BigDecimal.ZERO;
        }
        return bigDecimal;
    }

    public static BigDecimal getBigDecimalValueAndDefault(BigDecimal bigDecimal,BigDecimal defaultValue){
        if (bigDecimal == null){
            return defaultValue;
        }
        return bigDecimal;
    }

    public static BigDecimal subtractBigDecimal(BigDecimal b1 , BigDecimal b2){
        if (b1 == null){
            b1 = BigDecimal.ZERO;
        }
        if (b2 == null){
            b2 = BigDecimal.ZERO;
        }
        return b1.subtract(b2);
    }


    public static BigDecimal addBigDecimal(BigDecimal b1 , BigDecimal b2){
        if (b1 == null){
            b1 = BigDecimal.ZERO;
        }
        if (b2 == null){
            b2 = BigDecimal.ZERO;
        }
        return b1.add(b2);
    }

}
