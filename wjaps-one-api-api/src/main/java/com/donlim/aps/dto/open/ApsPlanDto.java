package com.donlim.aps.dto.open;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Description:
 * @Author: chenzhiquan
 * @Date: 2022/9/17.
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ApsPlanDto {

    private List<ApsPlanDetailDto> apsPlanDetailDtoList;
}
