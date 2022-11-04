package com.donlim.aps.dto.open;

import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author: chenzhiquan
 * @Date: 2022/9/17.
 */
@Data
public class ApsPlanDto {
    //private String docNo;
    private List<String>orderList;

    private List<ApsPlanDetailDto> apsPlanDetailDtoList;
}
