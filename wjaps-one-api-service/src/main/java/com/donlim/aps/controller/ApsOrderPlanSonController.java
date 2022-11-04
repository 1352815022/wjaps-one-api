package com.donlim.aps.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.api.ApsOrderPlanSonApi;
import com.donlim.aps.dto.ApsOrderPlanSonDto;
import com.donlim.aps.dto.ColsAndSearch;
import com.donlim.aps.entity.ApsOrderPlanSon;
import com.donlim.aps.service.ApsOrderPlanSonService;
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
 * 生产计划表(子件)(ApsOrderPlanSon)控制类
 *
 * @author sei
 * @since 2022-05-28 10:19:20
 */
@RestController
@Api(value = "ApsOrderPlanSonApi", tags = "生产计划表(子件)服务")
@RequestMapping(path = ApsOrderPlanSonApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class ApsOrderPlanSonController extends BaseEntityController<ApsOrderPlanSon, ApsOrderPlanSonDto> implements ApsOrderPlanSonApi {
    /**
     * 生产计划表(子件)服务对象
     */
    @Autowired
    private ApsOrderPlanSonService service;

    @Override
    public BaseEntityService<ApsOrderPlanSon> getService() {
        return service;
    }

    /**
     * 子件分页查询
     * @param search 查询条件
     * @return
     */
    @Override
    public ResultData<PageResult> findPlanByPage(ColsAndSearch search) {
        return ResultData.success(service.findPlanByPage(search,search.getCols()));
    }

    /**
     * 子件生产计划导出excel
     * @param search
     * @param response
     * @throws IOException
     */
    @Override
    public void export(ColsAndSearch search, HttpServletResponse response) throws IOException {
        try {
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode("子件生产计划","utf-8").replace("\\+","%20");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            List<List<String>> exportHeader = service.exportHeader(search.getCols());
            List<List<Object>> data = service.findByFilter(search, search.getCols());
            EasyExcel.write(response.getOutputStream()).head(exportHeader).autoCloseStream(false).sheet("sheet1").doWrite(data);
        } catch (IOException e) {
            response.reset();
            response.setCharacterEncoding("utf-8");
            response.getWriter().println(JSON.toJSONString(ResultData.fail("导出excel失败，"+e.getMessage())));
        }
    }
}
