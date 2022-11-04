package com.donlim.aps.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.connector.ScmConnector;
import com.donlim.aps.dao.ScmXbDeliveryDao;
import com.donlim.aps.dao.ScmXbDeliveryPlanDao;
import com.donlim.aps.entity.ScmXbDelivery;
import com.donlim.aps.entity.ScmXbDeliveryPlan;
import com.donlim.aps.util.CompanyEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * scm送货明细(ScmXbDeliveryPlan)业务逻辑实现类
 *
 * @author sei
 * @since 2022-06-20 11:15:03
 */
@Service
public class ScmXbDeliveryPlanService extends BaseEntityService<ScmXbDeliveryPlan> {
    @Autowired
    private ScmXbDeliveryPlanDao dao;
    @Autowired
    private ScmXbDeliveryDao scmXbDeliveryDao;

    @Override
    protected BaseEntityDao<ScmXbDeliveryPlan> getDao() {
        return dao;
    }

    /**
     * 保存送货计划明细
     *
     * @param companyEnum 供应商名称
     * @param date        送货开始日期
     */
   // @Transactional(rollbackFor = Exception.class)
    public void updateDeliveryDetail(CompanyEnum companyEnum, LocalDate date, List<String>parentIds) throws Exception {
        //先清除要更新的计划，再重新添加
        dao.deleteByParentIdIn(parentIds);
        // 自产
        List<ScmXbDeliveryPlan> deliveryDetailData = ScmConnector.getDeliveryDetailData(companyEnum.getCode(), date);

        // 委外
       // List<ScmXbDeliveryPlan> purchaseDetailData = ScmConnector.getPurchaseDetailData(companyEnum.getName(), date);
      //  deliveryDetailData.addAll(purchaseDetailData);
        Map<Long, List<ScmXbDeliveryPlan>> collect = deliveryDetailData.stream().filter(e -> e.getPoLineId() != null).collect(Collectors.groupingBy(ScmXbDeliveryPlan::getPoLineId));
        List<ScmXbDeliveryPlan> list = new ArrayList<>();
        for (Map.Entry<Long, List<ScmXbDeliveryPlan>> entry : collect.entrySet()) {
            ScmXbDelivery parent = scmXbDeliveryDao.findTopByPoLineId(entry.getKey());
            if (parent == null) {
                continue;
            }
            List<ScmXbDeliveryPlan> value = entry.getValue();
            for (ScmXbDeliveryPlan plan : value) {
                plan.setParentId(parent.getId());
                list.add(plan);
            }
        }
        this.save(list);
    }
}
