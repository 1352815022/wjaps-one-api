package com.donlim.aps.dao;

import com.donlim.aps.entity.U9Bom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 料品表bom(U9Bom)数据库访问类
 *
 * @author sei
 * @since 2022-05-12 14:58:02
 */
@Repository
public interface U9BomDao extends JpaRepository<U9Bom,Long> {

    /**
     * 根据masterId获取物料bom组成
     * @param id
     * @return List<U9Bom>
     */
    List<U9Bom> findByMasterId(String id);


    /**
     * 根据masterId找出子件
     * @param ids masterIds
     * @return
     */
    @Query(nativeQuery = true, value = "select * from u9_bom b where b.master_id in (" +
            "select t.master_id from u9_bom t where t.master_id in (:ids) group by t.master_id having count(1) > 1) ")
    List<U9Bom> getComponentByMasterIds(@Param("ids")List<Long> ids);

    /**
     * 根据masterId找出子件
     * @param ids masterIds
     * @return
     */
    List<U9Bom> findByMasterIdIn(List<String> ids);





}
