package com.donlim.aps.util;

import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.dto.serach.SearchFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:排除指定的查询字段
 * @Author: chenzhiquan
 * @Date: 2022/8/13.
 */

public class SearchUtil {
    /**
     * 排除查询条件
     * @param search
     * @param fieldList
     * @return
     */
    public  static Search excludeField(Search search, List<String> fieldList) {
        List<SearchFilter> filters = search.getFilters();

        List<SearchFilter> removeFilter = new ArrayList<>();
        for (String fieldName : fieldList) {
            for (SearchFilter filter : filters) {
                if (filter.getFieldName().equals(fieldName)) {
                    removeFilter.add(filter);
                }
            }
        }
        filters.removeAll(removeFilter);
        search.setFilters(filters);
        return  search;
    }
}
