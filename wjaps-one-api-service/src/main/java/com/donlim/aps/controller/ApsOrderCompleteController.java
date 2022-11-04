package com.donlim.aps.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.changhong.sei.core.context.ContextUtil;
import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.log.LogUtil;
import com.changhong.sei.core.service.BaseEntityService;
import com.changhong.sei.core.utils.ResultDataUtil;
import com.donlim.aps.api.ApsOrderCompleteApi;
import com.donlim.aps.dto.ApsOrderCompleteDto;
import com.donlim.aps.entity.ApsOrderComplete;
import com.donlim.aps.service.ApsOrderCompleteService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * 订单齐套(ApsOrderComplete)控制类
 *
 * @author sei
 * @since 2022-07-13 13:59:13
 */
@RestController
@Api(value = "ApsOrderCompleteApi", tags = "订单齐套服务")
@RequestMapping(path = ApsOrderCompleteApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class ApsOrderCompleteController extends BaseEntityController<ApsOrderComplete, ApsOrderCompleteDto> implements ApsOrderCompleteApi {
    /**
     * 订单齐套服务对象
     */
    @Autowired
    private ApsOrderCompleteService service;

    @Override
    public BaseEntityService<ApsOrderComplete> getService() {
        return service;
    }

    @Override
    public ResultData<PageResult<ApsOrderCompleteDto>> findByPage(Search search) {
        return convertToDtoPageResult(service.findByPage(search));
    }

    @Override
    public void export(Search search, HttpServletResponse response) throws IOException {
        try{
            List<ApsOrderComplete> list = service.findByFilters(search);
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setCharacterEncoding("utf-8");
            String fileNmae = URLEncoder.encode("齐套分析导出-需求分类", "utf-8").replace("\\+", "%20");
            response.setHeader("Content-disposition","attachment;filename="+fileNmae+".xlsx");
            EasyExcel.write(response.getOutputStream(),ApsOrderComplete.class).autoCloseStream(false).sheet("sheet1").doWrite(list);
        }catch (IOException e){
            LogUtil.warn("导出Excel失败",e);
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().println(JSON.toJSONString(ResultData.fail("导出Excel失败，"+e.getMessage())));
            e.printStackTrace();
        }
    }

    @Override
    public ResultData orderCompleteTask(Map<String, String> params) {
        LogUtil.bizLog("后台任务由【{}】执行完成！", ContextUtil.getSessionUser());
        service.orderCompleteTask();
        return ResultDataUtil.success("执行成功");
    }
}
