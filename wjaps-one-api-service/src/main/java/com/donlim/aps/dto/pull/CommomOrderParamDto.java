package com.donlim.aps.dto.pull;

import com.donlim.aps.entity.ApsOrganize;
import com.donlim.aps.entity.U9Material;
import com.donlim.aps.entity.cust.U9OrderCust;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.List;

/**
 * @Description:拉去订单公共参数
 * @Author: lijinjie
 * @Date: 2023/4/04.
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommomOrderParamDto {
    /**
     * 产品型号
     */
   private List<U9Material> u9MaterialList ;

    /**
     * 产品型号
     */
    private List<ApsOrganize> apsOrganizes ;
}
