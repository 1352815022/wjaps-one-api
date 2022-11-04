package com.donlim.aps.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DateUtils
 * @Description TODO
 * @Author p09835
 * @Date 2022/5/23 9:08
 **/
public class DateUtils extends com.changhong.sei.util.DateUtils {

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    public static final String DEFAULT_DATETIME_FORMAT = "YYYY-MM-dd hh:mm:ss";


    public static String LocalDateTimeToString(LocalDateTime localDateTime,String formatter){
        if (localDateTime == null){
            return null;
        }
        return localDateTime.format(DateTimeFormatter.ofPattern(formatter));
    }

    public static String LocalDateToString(LocalDate localDate){
        if (localDate == null){
            return null;
        }
        return localDate.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT));
    }


    /**
     * 从大到小
     */
    public static  List<LocalDate>  getDescDateList(LocalDate startDate,LocalDate endDate) {
        List<LocalDate> result = new ArrayList<>();
        if(endDate.compareTo(startDate) < 0 ){
            return  result;
        }
        while (true){
            result.add(endDate);
            if(endDate.compareTo(startDate) <= 0){
                break;
            }
            endDate= endDate.plusDays(-1);
        }
        return result;
    }

    /**
     * 从小到大
     */
    public static List<LocalDate> getAscDateList(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> result = new ArrayList<>();
        if(endDate.compareTo(startDate) < 0 ){
            return  result;
        }
        while (true){
            result.add(startDate);
            if(startDate.compareTo(endDate) >= 0){
                break;
            }
            startDate = startDate.plusDays(1);
        }
        return result;
    }

}
