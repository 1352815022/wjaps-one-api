package com.donlim.aps.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.changhong.sei.core.context.ContextUtil;
import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.log.LogUtil;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.api.ApsPurchasePlanApi;
import com.donlim.aps.dto.ApsPurchasePlanAndDetailsDto;
import com.donlim.aps.dto.ApsPurchasePlanDto;
import com.donlim.aps.dto.ColsAndSearch;
import com.donlim.aps.dto.ColumnDto;
import com.donlim.aps.entity.ApsPurchasePlan;
import com.donlim.aps.service.ApsPurchasePlanDetailService;
import com.donlim.aps.service.ApsPurchasePlanService;
import com.donlim.aps.util.ColumnUtils;
import com.donlim.aps.util.ResultEnum;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.List;

/**
 * 采购计划表(ApsPurchasePlan)控制类
 *
 * @author sei
 * @since 2022-05-20 09:16:30
 */
@RestController
@Api(value = "ApsPurchasePlanApi", tags = "采购计划表服务")
@RequestMapping(path = ApsPurchasePlanApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class ApsPurchasePlanController extends BaseEntityController<ApsPurchasePlan, ApsPurchasePlanDto> implements ApsPurchasePlanApi {
    /**
     * 采购计划表服务对象
     */
    @Autowired
    private ApsPurchasePlanService service;

    /**
     * 采购计划明细服务
     */
    @Autowired
    private ApsPurchasePlanDetailService purchasePlanDetailService;

    @Override
    public BaseEntityService<ApsPurchasePlan> getService() {
        return service;
    }

    @Override
    public ResultData<PageResult> findPlanByPage(ColsAndSearch search) {
        PageResult planByPage = service.findPlanByPage(search, search.getCols());
        return ResultData.success(planByPage);
    }

    @Override
    public ResultData<String> initPurchasePlan() {
        LogUtil.bizLog("后台任务由【{}】执行完成！", ContextUtil.getSessionUser());
        //service.copyScmOrder();
        return ResultData.success("执行成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultData<String> savePlan(ApsPurchasePlanAndDetailsDto dto) {
        if (StringUtils.isEmpty(dto.getId())){
            return ResultData.fail(ResultEnum.NOT_FIND_PLAN_ID.getMsg());
        }
        ApsPurchasePlan purchasePlan = entityModelMapper.map(dto, ApsPurchasePlan.class);
        BigDecimal planned = purchasePlanDetailService.savePlan(purchasePlan.getId(), dto.getDetails());
        purchasePlan.setAwaitQty(purchasePlan.getPlanQty().subtract(dto.getSumArrivalQty()).subtract(planned));
        service.save(purchasePlan);
        return ResultData.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultData<String> savePlans(List<ApsPurchasePlanAndDetailsDto> dtos) {
        for (ApsPurchasePlanAndDetailsDto dto : dtos) {
            if (StringUtils.isEmpty(dto.getId())){
                return ResultData.fail(ResultEnum.NOT_FIND_PLAN_ID.getMsg());
            }
            ApsPurchasePlan purchasePlan = entityModelMapper.map(dto, ApsPurchasePlan.class);
            BigDecimal planned = purchasePlanDetailService.savePlan(purchasePlan.getId(), dto.getDetails());
            purchasePlan.setAwaitQty(purchasePlan.getPlanQty().subtract(dto.getSumArrivalQty()).subtract(planned));
            service.save(purchasePlan);
        }
        return ResultData.success();
    }
    @Override
    public ResultData<List<ColumnDto>> getColumn(Integer cols) {
        return ResultData.success(ColumnUtils.getColumnsByDate(cols, LocalDate.now().plusDays(-15), true));
    }
    @Override
    public void export(ColsAndSearch search, HttpServletResponse response) throws IOException {

        try {
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode("委外计划", "utf-8").replace("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            List<List<Object>> exportData = service.findByFiltersForExport(search, search.getCols());
            List<List<String>> exportHeader = ColumnUtils.easyExcelHeaderWithDynamicColsGenerator(new String[]{
                    "需求分类号", "订单号", "采购单行","料号", "料名", "规格", "型号","单位","供应商编码", "供应商名称", "采购员","SCM开始送货日期", "SCM最后送货日期", "开始排产日期","最后排产日期","送货数量", "已送货数", "欠数", "备注"
            }, search.getCols());
            EasyExcel.write(response.getOutputStream()).head(exportHeader).autoCloseStream(false).sheet("sheet1").doWrite(exportData);
        }catch (Exception e){
            response.reset();
            response.setCharacterEncoding("utf-8");
            response.getWriter().println(JSON.toJSONString(ResultData.fail("导出Excel失败,"+e.getMessage())));
        }

    }
}
