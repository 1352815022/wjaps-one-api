package com.donlim.aps.convertdto;

import com.donlim.aps.dto.ScmXbDeliveryDto;
import com.donlim.aps.entity.ScmXbDelivery;
import org.springframework.beans.BeanUtils;

public class ConvertToDto {

    public static ScmXbDeliveryDto scmXbDeliveryToDto(ScmXbDelivery scmXbDelivery){
        ScmXbDeliveryDto scmXbDeliveryDto=new ScmXbDeliveryDto();
        BeanUtils.copyProperties(scmXbDelivery,scmXbDeliveryDto);
        return scmXbDeliveryDto;

    }
}
