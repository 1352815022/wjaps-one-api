package com.donlim.aps.dao;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.donlim.aps.entity.U9MoFinish;
import com.donlim.aps.entity.U9MoPickList;
import com.donlim.aps.entity.cust.OrderAndScm;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * U9数据扩展字段(U9MoFinish)数据库访问类
 *
 * @author sei
 * @since 2023-04-08 11:27:29
 */
@Repository
public interface U9MoFinishDao extends BaseEntityDao<U9MoFinish>  {


    /**
     * 统计U9订单完工数，日期维度
     * @return
     */
    @Query(value = "select sum(umf.finish_qty) as finishQty ,umf.order_no as orderNo ,aoe.id as id   from u9_mo_finish umf \n" +
            "left join aps_order_ext aoe on aoe.order_no  = umf.order_no  \n" +
            "group by umf.order_no \n" , nativeQuery = true)
    List<Map<String,Object>> countU9FinishQtyQuery();

    /**
     * 取出完工单
     * @param date
     * @return
     */
    List<U9MoFinish>findByFinishDateBetween(LocalDate start,LocalDate end);


    Integer countByFinishDateBetween(LocalDate start,LocalDate end);

}
