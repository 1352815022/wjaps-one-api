package com.donlim.aps.controller;


import com.changhong.sei.core.test.BaseUnitTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * 实现功能: Hello 单元测试
 */
public class HelloControllerTest extends BaseUnitTest {

    @Test
    public void sayHello() {
        List<String> list1 = new ArrayList<>();
        list1.add("1111");
        list1.add("2222");
        list1.add("3333");

        List<String> list2 = new ArrayList<>();
        list2.add("3333");
        list2.add("4444");
        List<String> reduce1 = list1.stream().filter(item -> !list2.contains(item)).collect(Collectors.toList());
        System.out.println("---得到差集 reduce1 (list1 - list2)---");


    }
}
