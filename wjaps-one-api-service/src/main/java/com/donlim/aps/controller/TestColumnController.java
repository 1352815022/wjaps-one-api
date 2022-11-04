package com.donlim.aps.controller;

import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.donlim.aps.api.TestColumnApi;
import com.donlim.aps.dto.ColumnDto;
import com.donlim.aps.util.ColumnUtils;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

/**
 * @ClassName TestColumnController
 * @Description TODO
 * @Author p09835
 * @Date 2022/4/26 17:01
 **/

@RestController
@Api(value = "testColumnApi",tags = "动态table列服务")
@RequestMapping(path = TestColumnApi.PATH,produces = MediaType.APPLICATION_JSON_VALUE)
public class TestColumnController implements TestColumnApi {
    @Override
    public ResultData<List<ColumnDto>> getColumn() {

        return ResultData.success(ColumnUtils.getColumnsByDate(30, LocalDate.now(),true));
    }

    @Override
    public ResultData<PageResult> getData(){
        //模拟30天动态字段
        LocalDate now = LocalDate.now();
        List<Map<String, Object>> list = new ArrayList<>();

        Random random = new Random();
        for (int k = 0 ; k <20 ; k ++){
            Map<String,Object> map = new HashMap<>();
            map.put("code","line"+k);
            map.put("name","测试数据"+k);
            for (int i=0 ; i< 30 ; i ++ ){
                LocalDate tmp = now.plusDays(i);
                if ( i % (k%2 +2) == 0) {
                    map.put(tmp.toString(), random.nextInt(9999));
                }

            }
            list.add(map);
        }
        PageResult pageResult = new PageResult();
        pageResult.setPage(1);
        pageResult.setRecords(15);
        pageResult.setRows(list);
        pageResult.setTotal(15);
        pageResult.setTotalAmount(new BigDecimal(15));
        return ResultData.success(pageResult);

    }
}
