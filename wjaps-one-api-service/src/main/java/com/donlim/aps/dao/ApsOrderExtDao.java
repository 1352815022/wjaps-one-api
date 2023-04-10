package com.donlim.aps.dao;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.donlim.aps.entity.ApsOrderExt;
import com.donlim.aps.entity.U9MoFinish;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * U9数据扩展字段(U9MoFinish)数据库访问类
 *
 * @author sei
 * @since 2023-04-08 11:27:29
 */
@Repository
public interface ApsOrderExtDao extends BaseEntityDao<ApsOrderExt>  {




}