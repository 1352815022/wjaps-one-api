package com.donlim.aps.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.dto.serach.SearchFilter;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.connector.McasConnector;
import com.donlim.aps.dao.McasYieldDao;
import com.donlim.aps.entity.McasYield;
import com.donlim.aps.util.CompanyEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * (McasYield)业务逻辑实现类
 *
 * @author sei
 * @since 2022-06-14 10:26:06
 */
@Service
public class McasYieldService extends BaseEntityService<McasYield> {
    @Autowired
    private McasYieldDao dao;
    @Autowired
    private ApsProductionProcessScheduleService apsProductionProcessScheduleService;
    @Autowired
    private ApsOrderPlanService apsOrderPlanService;
    @Override
    protected BaseEntityDao<McasYield> getDao() {
        return dao;
    }
    List<McasYield>findMcasYieldsByDate(LocalDate date){
        return  dao.findMcasYieldsByDate(date);
    }
    /**
     * 更新产量信息并更新产量报表
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateYeildTask(){
        try {
            //更新时间为当天8点前，获取昨天的
            LocalDate date=LocalDate.now();
            if(LocalDateTime.now().getHour()<8){
                date=LocalDate.now().plusDays(-1);
            }
            List<McasYield> yieldData = McasConnector.getCYYieldData(CompanyEnum.WJ1_MCAS.getCode(), date);
            List<McasYield>manualYieldData=McasConnector.getManualYieldData(CompanyEnum.WJ1_MCAS.getCode(), date);
            dao.deleteByDateEquals(date);
            yieldData.addAll(manualYieldData);
            dao.save(yieldData);
            //重新计算进度表
            apsProductionProcessScheduleService.calcProductionProcessSchedule(date);
            if(LocalDateTime.now().getHour()==7) {
                apsOrderPlanService.updatePlanFromMcas();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取喷粉/清洗数据
     * @param search
     * @return
     */
    public List<McasYield> findByWashAndPowder(Search search){
        Search wash = new Search();
        List<SearchFilter> washFilters = new ArrayList<>(search.getFilters());
        washFilters.add(new SearchFilter("lineName","清洗", SearchFilter.Operator.LK));
        wash.setFilters(washFilters);
        Search powder = new Search();
        List<SearchFilter> powderFilters = new ArrayList<>(search.getFilters());
        powderFilters.add(new SearchFilter("lineName","喷粉", SearchFilter.Operator.LK));
        powder.setFilters(powderFilters);
        List<McasYield> washList = this.findByFilters(wash);
        List<McasYield> powderList = this.findByFilters(powder);
        washList.addAll(powderList);

        return washList;
    }
}
