package com.donlim.aps.dto;

import com.changhong.sei.core.dto.serach.Search;

/**
 * @ClassName ColsAndSearch
 * @Description 动态列数 与 search DTO
 * @Author p09835
 * @Date 2022/5/16 14:35
 **/
public class ColsAndSearch extends Search{

    private Integer cols;


    public Integer getCols() {
        return cols;
    }

    public void setCols(Integer cols) {
        this.cols = cols;
    }

}
