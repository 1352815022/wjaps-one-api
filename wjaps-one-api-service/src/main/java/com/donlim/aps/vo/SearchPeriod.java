package com.donlim.aps.vo;

import java.time.LocalDate;

/**
 * @ClassName SearchPeriod
 * @Description 计算search日期间隔返回实体
 * @Author p09835
 * @Date 2022/6/16 9:27
 **/
public class SearchPeriod {

    /**
     * 开始日期
     */
    private LocalDate startDate;

    /**
     * 间隔日期
     */
    private Integer days ;

    public SearchPeriod(LocalDate startDate, Integer days) {
        this.startDate = startDate;
        this.days = days;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }
}
