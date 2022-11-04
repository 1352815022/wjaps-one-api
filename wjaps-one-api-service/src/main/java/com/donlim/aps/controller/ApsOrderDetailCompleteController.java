package com.donlim.aps.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.log.LogUtil;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.api.ApsOrderDetailCompleteApi;
import com.donlim.aps.dto.ApsOrderDetailCompleteDto;
import com.donlim.aps.entity.ApsOrderDetailComplete;
import com.donlim.aps.service.ApsOrderDetailCompleteService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * 订单齐套(ApsOrderDetailComplete)控制类
 *
 * @author sei
 * @since 2022-07-13 14:09:47
 */
@RestController
@Api(value = "ApsOrderDetailCompleteApi", tags = "订单齐套服务")
@RequestMapping(path = ApsOrderDetailCompleteApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class ApsOrderDetailCompleteController extends BaseEntityController<ApsOrderDetailComplete, ApsOrderDetailCompleteDto> implements ApsOrderDetailCompleteApi {
    /**
     * 订单齐套服务对象
     */
    @Autowired
    private ApsOrderDetailCompleteService service;

    @Override
    public BaseEntityService<ApsOrderDetailComplete> getService() {
        return service;
    }

    @Override
    public ResultData<PageResult<ApsOrderDetailCompleteDto>> findByPage(Search search) {
        return convertToDtoPageResult(service.findByPage(search));
    }

    @Override
    public void export(Search search, HttpServletResponse response) throws IOException {
        try{
            List<ApsOrderDetailComplete> list = service.findByFilters(search);
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setCharacterEncoding("utf-8");
            String fileNmae = URLEncoder.encode("齐套分析导出-工单", "utf-8").replace("\\+", "%20");
            response.setHeader("Content-disposition","attachment;filename="+fileNmae+".xlsx");
            EasyExcel.write(response.getOutputStream(), ApsOrderDetailComplete.class).autoCloseStream(false).sheet("sheet1").doWrite(list);
        }catch (IOException e){
            LogUtil.warn("导出Excel失败",e);
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().println(JSON.toJSONString(ResultData.fail("导出Excel失败，"+e.getMessage())));
            e.printStackTrace();
        }
    }
}
