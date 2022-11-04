package com.donlim.aps.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.log.LogUtil;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.dao.U9StockDao;
import com.donlim.aps.entity.U9Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * (U9Stock)业务逻辑实现类
 *
 * @author sei
 * @since 2022-05-09 16:20:09
 */
@Service
public class U9StockService extends BaseEntityService<U9Stock> {
    @Autowired
    private U9StockDao dao;

    @Override
    protected BaseEntityDao<U9Stock> getDao() {
        return dao;
    }

    /**
     * 根据料品id分类汇总库存
     * @param materialIds 料品id List
     * @return Map<Long,BigDecimal> 料品id，库存数
     * @throws Exception 出于性能考虑，in查询条件不能超过200个元素
     */
    public Map<Long,BigDecimal> sumStockGroupByMaterialId(List<Long> materialIds) throws Exception {
        if (materialIds.size() > 200){
            LogUtil.warn("Query U9Stock error .SQL syntax `in` not allowed to exceed 200 elements.");
            throw new Exception("SQL syntax `in` not allowed to exceed 200 elements");
        }
        List<U9Stock> stocks = dao.findByMaterialIdIn(materialIds);
        Map<Long, BigDecimal> collect = stocks.stream().collect(
                Collectors.groupingBy(U9Stock::getMaterialId, Collectors.collectingAndThen(Collectors.toList(),
                        o -> o.stream().map(U9Stock::getStoreQty).reduce(BigDecimal.ZERO, BigDecimal::add)
                        )
                )
        );

        return collect;
    }

}
