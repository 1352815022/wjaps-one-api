package com.donlim.aps.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.dao.ApsOrderPlanDao;
import com.donlim.aps.dao.ApsOrderPlanSonDao;
import com.donlim.aps.dao.ApsOrderPlanSonDetailDao;
import com.donlim.aps.dto.ApsOrderPlanAndDetailsDto;
import com.donlim.aps.entity.ApsOrderPlanSon;
import com.donlim.aps.entity.ApsOrderPlanSonDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 生产计划明细表(子件)(ApsOrderPlanSonDetail)业务逻辑实现类
 *
 * @author sei
 * @since 2022-05-28 10:20:30
 */
@Service
public class ApsOrderPlanSonDetailService extends BaseEntityService<ApsOrderPlanSonDetail> {
    @Autowired
    private ApsOrderPlanSonDetailDao sonDetailDao;
    @Autowired
    private ApsOrderPlanSonDao sonDao;
    @Autowired
    private ApsOrderPlanDao planDao;

    @Override
    protected BaseEntityDao<ApsOrderPlanSonDetail> getDao() {
        return sonDetailDao;
    }

    /**
     * 保存子件生产计划
     * 1.判断是否有子件
     * 2.计算排产百分比 x < 1 则按百分比排产，x = 1 则排产剩余数量
     * 3.刷新子件待排数量
     * @param detailsDto 父生产计划dto
     * @return
     */
    public void saveDetail(ApsOrderPlanAndDetailsDto detailsDto) {
        List<ApsOrderPlanSon> sonPlans = sonDao.findByPlanId(detailsDto.getId());
        if (sonPlans.size() == 0){
            return ;
        }
        List<ApsOrderPlanSonDetail> sonDetailList = new ArrayList<>();
        Map<LocalDate, BigDecimal> sonPlanProportion = calcPlanProportion(detailsDto)
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey((LocalDate::compareTo)))
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(e1,e2)->e1,LinkedHashMap::new));
        for (ApsOrderPlanSon sonPlan : sonPlans) {
            BigDecimal planned = new BigDecimal(0);
            for (Map.Entry<LocalDate, BigDecimal> entry : sonPlanProportion.entrySet()) {
                LocalDate planDate = entry.getKey();
                ApsOrderPlanSonDetail sonDetail = sonDetailDao.findTopByPlanIdAndPlanDate(sonPlan.getId(), planDate);
                BigDecimal planQty;
                if (entry.getValue().compareTo(BigDecimal.ONE) < 0){
                    planQty = sonPlan.getPlanQty().multiply(entry.getValue()).setScale(0, BigDecimal.ROUND_DOWN);
                }else{
                    planQty = sonPlan.getPlanQty().subtract(planned);
                }

                if (sonDetail == null){
                    if (entry.getValue().compareTo(BigDecimal.ZERO) >0){
                        sonDetail = new ApsOrderPlanSonDetail();
                        sonDetail.setPlanId(sonPlan.getId());
                        sonDetail.setPlanQty(planQty);
                        sonDetail.setPlanDate(planDate);
                        sonDetailList.add(sonDetail);
                    }
                }else{
                    sonDetail.setPlanQty(planQty);
                    sonDetailList.add(sonDetail);
                }
                planned = planned.add(planQty);
            }
            sonPlan.setAwaitQty(sonPlan.getPlanQty().subtract(planned));
        }
        this.save(sonDetailList);
        sonDao.save(sonPlans);


    }

    /**
     * 计算子件排产数量百分比
     * 数据库已排数量 与 dto传入数量，按照dto数量覆盖已排数量，刷新awaitQty
     * awaitQty = 0 则返回1，否则返回百分比
     * @param detailsDto 母件生产计划
     * @return 排产数量百分比
     */
    private Map<LocalDate,BigDecimal> calcPlanProportion(ApsOrderPlanAndDetailsDto detailsDto) {

        Map<String, BigDecimal> detailsMap = detailsDto.getDetails();
        Map<LocalDate,BigDecimal> planProportion = new HashMap<>();
        BigDecimal planned = new BigDecimal(0);

        for (Map.Entry<String, BigDecimal> entry : detailsMap.entrySet()) {
            LocalDate planDate = LocalDate.parse(entry.getKey());

            if (entry.getValue().compareTo(BigDecimal.ZERO) == 0){
                planProportion.put(planDate,BigDecimal.ZERO);
                continue;
            }
            planned = planned.add(entry.getValue());
            if (detailsDto.getPlanQty().compareTo(planned) <= 0){
                planProportion.put(planDate,BigDecimal.ONE);
            }else{
                BigDecimal proportion = entry.getValue().divide(detailsDto.getPlanQty(), 4, BigDecimal.ROUND_HALF_UP);
                planProportion.put(planDate,proportion);
            }
        }
        return planProportion;
    }




}
