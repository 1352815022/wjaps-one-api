package com.donlim.aps.util;

import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.dto.serach.SearchFilter;
import com.donlim.aps.dto.ColumnDto;
import com.donlim.aps.vo.SearchPeriod;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * @ClassName ColumnUtils
 * @Description 动态table行获取工具类
 * @Author p09835
 * @Date 2022/4/27 14:14
 **/
public class ColumnUtils {

    /**
     * 获取以日期为header的动态行
     * @param size  日期跨度
     * @param start  开始时间
     * @return
     */
    public static List<ColumnDto> getColumnsByDate(long size, LocalDate start, boolean editFlag){
        ArrayList<ColumnDto> result = new ArrayList<>();
        for (int i = 0 ; i < size ; i ++){
            LocalDate tmp = start.plusDays(i);
            ColumnDto column = new ColumnDto();
            column.setWidth(150);
            column.setTitle(tmp.getMonthValue()+"/"+tmp.getDayOfMonth() +tmp.getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.CHINA));
            column.setDataIndex(tmp.toString());
            if (editFlag){
                column.setWidth(150);
                if (i == 0) {
                    column.setElem(ColumnDto.FormElem.INPUT_NUMBER_RIGHT);
                }else if( i == size -1){
                    column.setElem(ColumnDto.FormElem.INPUT_NUMBER_LEFT);
                }else{
                    column.setElem(ColumnDto.FormElem.INPUT_NUMBER_ALL);
                }
            }

            result.add(column);
        }
        return result;
    }

    /**
     * 返回动态列
     * @param searchPeriod
     * @return
     */
    public static List<ColumnDto> getColumnsByDate (SearchPeriod searchPeriod){
        return getColumnsByDate(searchPeriod.getDays(),searchPeriod.getStartDate(),false);
    }

    /**
     * 计算Search中日期间隔
     * @param search
     * @return 默认返回当前日期+7天
     */
    public static SearchPeriod calcSearchPeriodDate(Search search,String field){
        List<SearchFilter> filters = search.getFilters();
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = null;
        for (SearchFilter filter : filters) {
            if (field.equals(filter.getFieldName())){
                if (SearchFilter.Operator.GE.equals(filter.getOperator())){
                    startDate = com.changhong.sei.util.DateUtils.date2LocalDate((Date)filter.getValue());
                }else if (SearchFilter.Operator.LE.equals(filter.getOperator())){
                    endDate = com.changhong.sei.util.DateUtils.date2LocalDate((Date)filter.getValue());
                }
            }
        }
        int diff ;
        if (null != startDate && null != endDate){
            Period between = Period.between(startDate, endDate);
            diff = between.getDays() + 1;
        }else{
            diff = 7;
        }
        return new SearchPeriod(startDate,diff);
    }

    /**
     * 生成easyExcel字段标题
     * @param titles
     * @param cols
     * @return
     */
    public static List<List<String>> easyExcelHeaderWithDynamicColsGenerator(String[] titles,Integer cols){
        List<ColumnDto> dynamicCol = ColumnUtils.getColumnsByDate(cols, LocalDate.now(),false);
        List<List<String>> headers = new ArrayList<>();
        for (String title : titles) {
            List<String> header = new ArrayList<>();
            header.add(title);
            headers.add(header);
        }
        List<List<String>> collect = dynamicCol.stream().map(e -> {
            List<String> header = new ArrayList<>();
            header.add(e.getTitle());
            return header;
        }).collect(Collectors.toList());
        headers.addAll(collect);
        return headers;
    }



}
