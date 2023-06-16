package com.donlim.aps.controller;

import com.changhong.sei.core.dto.ResultData;
import com.donlim.aps.api.ApsOpenApi;
import com.donlim.aps.dao.ApsOrderPlanDetailDao;
import com.donlim.aps.dao.U9ProduceOrderDao;
import com.donlim.aps.dto.open.ApsPlanDetailDto;
import com.donlim.aps.dto.open.ApsPlanDto;
import com.donlim.aps.entity.ApsOrderPlanDetail;
import com.donlim.aps.entity.U9ProduceOrder;
import io.swagger.annotations.Api;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:对外接口
 * @Author: chenzhiquan
 * @Date: 2022/9/17.
 */
@RestController
@Api(value = "ApsOpenApi", tags = "对外接口")
@RequestMapping(path = ApsOpenApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class ApsOpenController implements ApsOpenApi {

    @Autowired
    private ApsOrderPlanDetailDao apsOrderPlanDetailDao;
    @Autowired
    private U9ProduceOrderDao u9ProduceOrderDao;

    @Override
    public ResultData<ApsPlanDto> getPlanByDate(String date) {
        //取7天内的排产数量
        LocalDate start = LocalDate.parse(date , DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate end = start.plusDays(15);
        ApsPlanDto apsPlanDto=new ApsPlanDto();
        List<ApsPlanDetailDto> allByPlanDate = apsOrderPlanDetailDao.findAllByPlanDate(start,end);

        apsPlanDto.setApsPlanDetailDtoList(allByPlanDate);
        return ResultData.success(apsPlanDto);
    }
    
}
