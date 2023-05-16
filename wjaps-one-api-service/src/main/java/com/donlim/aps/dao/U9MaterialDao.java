package com.donlim.aps.dao;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.donlim.aps.entity.U9Material;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 料品表(U9Material)数据库访问类
 *
 * @author sei
 * @since 2022-05-09 13:45:01
 */
@Repository
public interface U9MaterialDao extends BaseEntityDao<U9Material> {

    List<U9Material>findByCodeIn(List<String>codes);

    Optional<U9Material>findByCode(String code);
    List<U9Material>findByProductModelIsNotNull();
    List<U9Material>findByCalcIsFalse();


}
