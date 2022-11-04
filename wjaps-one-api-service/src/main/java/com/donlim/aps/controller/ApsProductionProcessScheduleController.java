package com.donlim.aps.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.log.LogUtil;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.api.ApsProductionProcessScheduleApi;
import com.donlim.aps.dto.ApsProductionProcessScheduleDto;
import com.donlim.aps.dto.StampingReportDto;
import com.donlim.aps.dto.WashWorkshopScheduleDto;
import com.donlim.aps.entity.ApsProductionProcessSchedule;
import com.donlim.aps.service.ApsProductionProcessScheduleService;
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
 * 生产工序报工表
 * (ApsProductionProcessSchedule)控制类
 *
 * @author sei
 * @since 2022-06-20 11:34:57
 */
@RestController
@Api(value = "ApsProductionProcessScheduleApi", tags = "生产工序报工表服务")
@RequestMapping(path = ApsProductionProcessScheduleApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class ApsProductionProcessScheduleController extends BaseEntityController<ApsProductionProcessSchedule, ApsProductionProcessScheduleDto> implements ApsProductionProcessScheduleApi {
    /**
     * 生产工序报工表
     * 服务对象
     */
    @Autowired
    private ApsProductionProcessScheduleService service;

    @Override
    public BaseEntityService<ApsProductionProcessSchedule> getService() {
        return service;
    }


    /**
     * 查询生产进度表（按料）
     * @param search
     * @return
     */
    @Override
    public ResultData<List<ApsProductionProcessScheduleDto>> findByMaterial(Search search) {
        return ResultData.success(convertToDtos(service.findByMaterialList(search)));
    }
    /**
     * 查询生产进度表（按单）
     * @param search
     * @return
     */
    @Override
    public ResultData<PageResult<ApsProductionProcessScheduleDto>> findByOrder(Search search) {

        return convertToDtoPageResult(service.findByPage(search));
    }

    /**
     * 冲压车间报工报表
     * @param search
     * @return
     */
    @Override
    public ResultData<List<StampingReportDto>> findByOrderReport(Search search) {
        return ResultData.success(service.getStampingReport(search));
    }


    /**
     * 按工单汇总导出工序报工
     * @param search
     */
    @Override
    public void exportByOrder(Search search ,HttpServletResponse response) throws IOException {
        List<ApsProductionProcessSchedule> list = service.findByFilters(search);
        response.setContentType("application/octet-stream;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        String fileName = null;
        try {
            fileName = URLEncoder.encode("MCAS按单工序报工","utf-8").replace("\\+","$20");
            response.setHeader("Content-disposition","attachment;filename="+fileName+".xlsx");
            EasyExcel.write(response.getOutputStream(),ApsProductionProcessSchedule.class)
                    .autoCloseStream(false)
                    .sheet("sheet1")
                    .doWrite(list);
        } catch (IOException e) {
            LogUtil.warn("导出Excel失败",e);
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().println(JSON.toJSONString(ResultData.fail("导出excel失败，"+e.getMessage())));
            e.printStackTrace();
        }
    }

    @Override
    public ResultData<List<WashWorkshopScheduleDto>> findWashByOrder(Search search) {
        List<WashWorkshopScheduleDto> washWorkshopScheduleDtos = service.washWorkshopReport(search);
        return ResultData.success(washWorkshopScheduleDtos);
    }
}
