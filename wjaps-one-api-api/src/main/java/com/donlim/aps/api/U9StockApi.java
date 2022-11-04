package com.donlim.aps.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.changhong.sei.core.api.FindByPageApi;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.Search;
import com.donlim.aps.dto.U9StockDto;
import com.donlim.aps.dto.U9StockExport;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

/**
 * (U9Stock)API
 *
 * @author sei
 * @since 2022-05-09 16:20:11
 * @FeignClient(name = "u9Stock")
 */
@Valid
@FeignClient(name = "wjaps-one-api", path = U9StockApi.PATH)
public interface U9StockApi extends BaseEntityApi<U9StockDto> , FindByPageApi<U9StockDto> {
    String PATH = "u9Stock";

    /**
     * 库存信息导出
     * @param search
     * @return
     */
    @PostMapping("/export")
    ResultData<List<U9StockExport>> export(Search search);

}
