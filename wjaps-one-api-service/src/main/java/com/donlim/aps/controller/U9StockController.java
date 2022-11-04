package com.donlim.aps.controller;

import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.api.U9StockApi;
import com.donlim.aps.dto.U9StockDto;
import com.donlim.aps.dto.U9StockExport;
import com.donlim.aps.entity.U9Stock;
import com.donlim.aps.service.U9StockService;
import io.swagger.annotations.Api;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (U9Stock)控制类
 *
 * @author sei
 * @since 2022-05-09 16:20:09
 */
@RestController
@Api(value = "U9StockApi", tags = "库存服务")
@RequestMapping(path = U9StockApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class U9StockController extends BaseEntityController<U9Stock, U9StockDto> implements U9StockApi {
    /**
     * 服务对象
     */
    @Autowired
    private U9StockService service;

    @Override
    public BaseEntityService<U9Stock> getService() {
        return service;
    }

    @Override
    public ResultData<PageResult<U9StockDto>> findByPage(Search search) {
        return convertToDtoPageResult(service.findByPage(search));
    }

    @Override
    public ResultData<List<U9StockExport>> export(Search search) {
        List <U9StockExport> result;
        List<U9Stock> list = service.findByFilters(search);
        if (CollectionUtils.isNotEmpty(list)){
            result = list.stream().map(item -> dtoModelMapper.map(item, U9StockExport.class))
                    .sorted(Comparator.comparing(U9StockExport::getWarehouseCode)).collect(Collectors.toList());
        } else{
            result = new ArrayList<>();
        }
        return ResultData.success(result);
    }


}
