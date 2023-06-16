package com.donlim.aps.controller;


import com.changhong.sei.core.test.BaseUnitTest;

import com.donlim.aps.dao.ApsOrderPlanDetailDao;
import com.donlim.aps.dao.U9ProduceOrderDao;
import com.donlim.aps.dto.open.ApsPlanDetailDto;
import com.donlim.aps.dto.open.ApsPlanDto;
import com.donlim.aps.entity.ApsOrderPlanDetail;
import com.donlim.aps.entity.U9ProduceOrder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.changhong.sei.basic.api.*;

/**
 * 实现功能: Hello 单元测试
 */
public class HelloControllerTest extends BaseUnitTest {

    @Autowired
    private ApsOrderPlanDetailDao apsOrderPlanDetailDao;
    @Autowired
    private U9ProduceOrderDao u9ProduceOrderDao;
    @Test
    public void sayHello() {
        LocalDate start = LocalDate.parse("2023-06-16" , DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate end = start.plusDays(15);
        ApsPlanDto apsPlanDto=new ApsPlanDto();
        List<ApsPlanDetailDto> allByPlanDate = apsOrderPlanDetailDao.findAllByPlanDate(start,end);

        allByPlanDate.forEach(a->{
            System.out.println(a.getDocNo());
        });



    }
}
