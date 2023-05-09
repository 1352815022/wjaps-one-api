package com.donlim.aps.dto.pull;

import com.donlim.aps.entity.ApsOrganize;
import com.donlim.aps.entity.U9Material;
import lombok.*;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description:修改完工数量
 * @Author: lijinjie
 * @Date: 2023/4/017.
 */
@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ModifyFinishQtyParamDto {
    /**
     * 产品型号
     */
   private String orderId ;

    /**
     * 产品型号
     */
    private BigDecimal qty ;
}
