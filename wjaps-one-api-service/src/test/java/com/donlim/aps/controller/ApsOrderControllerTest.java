package com.donlim.aps.controller;

import com.changhong.sei.core.dto.ResultData;
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
import java.util.List;
import java.util.stream.Collectors;

/**
 * 实现功能: Hello 单元测试
 */
@Slf4j
public class ApsOrderControllerTest extends BaseUnitTest {
    @Autowired
    private ApsOrderController apsOrderController;
    @Autowired
    private ApsOrderService apsOrderService;
    @Test
    public void initApsOrder() {

        apsOrderService.findOrderStatistics();
     /*   long l1 = System.currentTimeMillis();
        ResultData<String> result = apsOrderController.initApsOrder();
        long l2 = System.currentTimeMillis();*/
    }
}
