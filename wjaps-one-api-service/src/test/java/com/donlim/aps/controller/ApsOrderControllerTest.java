package com.donlim.aps.controller;

import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.log.LogUtil;
import com.changhong.sei.core.test.BaseUnitTest;
import com.changhong.sei.core.util.JsonUtils;
import com.donlim.aps.connector.ScmConnector;
import com.donlim.aps.entity.ScmXbDelivery;
import com.donlim.aps.service.ApsOrderService;
import com.donlim.aps.service.ScmXbDeliveryService;
import com.donlim.aps.util.CompanyEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 实现功能: Hello 单元测试
 */
public class ApsOrderControllerTest extends BaseUnitTest {

    @Test
    public void initApsOrder() {

        List<String> list1 = new ArrayList<>();
        list1.add("1111");
        list1.add("2222");
        list1.add("3333");

        List<String> list2 = new ArrayList<>();
        list2.add("3333");
        list2.add("4444");
        List<String> reduce1 = list1.stream().filter(item -> !list2.contains(item)).collect(Collectors.toList());
        System.out.println("---得到差集 reduce1 (list1 - list2)---");
        reduce1.parallelStream().forEach(System.out::println);
     /*   long l1 = System.currentTimeMillis();
        ResultData<String> result = apsOrderController.initApsOrder();
        long l2 = System.currentTimeMillis();*/
    }
}
