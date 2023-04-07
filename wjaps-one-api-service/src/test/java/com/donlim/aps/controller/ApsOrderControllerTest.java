package com.donlim.aps.controller;

import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.test.BaseUnitTest;
import com.changhong.sei.core.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 实现功能: Hello 单元测试
 */
@Slf4j
public class ApsOrderControllerTest extends BaseUnitTest {
    @Autowired
    private ApsOrderController apsOrderController;

    @Test
    public void initApsOrder() {
        long l1 = System.currentTimeMillis();
        ResultData<String> result = apsOrderController.initApsOrder();
        long l2 = System.currentTimeMillis();
    }
}