package com.donlim.aps.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.dto.serach.SearchFilter;
import com.changhong.sei.core.log.LogUtil;
import com.changhong.sei.core.service.BaseEntityService;
import com.changhong.sei.core.utils.TransactionUtil;
import com.donlim.aps.connector.McasConnector;
import com.donlim.aps.dao.McasYieldDao;
import com.donlim.aps.entity.McasYield;
import com.donlim.aps.util.CompanyEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
           // List<McasYield> yieldData = McasConnector.getCYYieldData(CompanyEnum.WJ1_MCAS.getCode(), date);
          //  List<McasYield>manualYieldData=McasConnector.getManualYieldData(CompanyEnum.WJ1_MCAS.getCode(), date);
         //   dao.deleteByDateEquals(date);
        //    yieldData.addAll(manualYieldData);
        //    dao.save(yieldData);
            //重新计算进度表
           // apsProductionProcessScheduleService.calcProductionProcessSchedule(date);
         //   if(LocalDateTime.now().getHour()==7) {
          //      apsOrderPlanService.updatePlanFormU9();
          //  }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 更新产量信息并更新产量报表2.0
     */
    public void updateYeildTask_V2(){

        //更新时间为当天8点前，获取昨天的
        LocalDate date=LocalDate.now();
        if(LocalDateTime.now().getHour() < 8){
            date=LocalDate.now().plusDays(-1);
        }
        List<McasYield> yieldData = null;
        List<McasYield> manualYieldData = null;
        try {
            yieldData = McasConnector.getCYYieldData(CompanyEnum.WJ1_MCAS.getCode(), date);
            manualYieldData = McasConnector.getManualYieldData(CompanyEnum.WJ1_MCAS.getCode(), date);
            yieldData.addAll(manualYieldData);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.bizLog("数据获取失败了，{}",e.getMessage());
        }
        //开启编程式事务
        TransactionStatus saveStatus = TransactionUtil.beginNewTransaction();
        try{
            dao.deleteByDateEquals(date);
            dao.save(yieldData);
            //重新计算进度表
            apsProductionProcessScheduleService.calcProductionProcessSchedule(date);
           /* if(LocalDateTime.now().getHour() == 7) {
                apsOrderPlanService.updatePlanFromMcas();
            }*/
            TransactionUtil.commit(saveStatus);
        }catch (Exception e){
            TransactionUtil.rollback(saveStatus);
            LogUtil.bizLog("updateYeildTask_V2事务回滚了，{}",e.getMessage());
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
