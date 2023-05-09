package com.donlim.aps.controller;

import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.test.BaseUnitTest;
import com.changhong.sei.core.util.JsonUtils;
import com.donlim.aps.connector.ScmConnector;
import com.donlim.aps.controller.HelloController;
import com.donlim.aps.entity.ApsOrder;
import com.donlim.aps.entity.ScmXbDelivery;
import com.donlim.aps.service.ApsOrderPlanService;
import com.donlim.aps.service.ApsOrderService;
import com.donlim.aps.service.ScmXbDeliveryService;
import com.donlim.aps.util.CompanyEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * 实现功能: Hello 单元测试
 */
public class HelloControllerTest extends BaseUnitTest {
    @Autowired
    private HelloController controller;
    @Autowired
    private ScmXbDeliveryService service;
    @Autowired
    private ApsOrderPlanService apsOrderPlanService;
    @Autowired
    private ApsOrderService apsOrderService;
    @Test
    public void sayHello() {

        apsOrderService.findOrderStatistics();
       /* try {
            List<ScmXbDelivery> list = ScmConnector.getDeliveryData(CompanyEnum.WJ1_SCM.getCode(), LocalDate.now());
            List<ScmXbDelivery> j900BE123000083 = list.stream().filter(o -> o.getOrderNo().equals("J900BE123000083")).filter(m -> m.getMaterialCode().equals("52000033613")).collect(Collectors.toList());
            System.out.println(j900BE123000083.get(0).getOweQty());
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        //apsOrderPlanService.updatePlanFormU9();


    }
}
