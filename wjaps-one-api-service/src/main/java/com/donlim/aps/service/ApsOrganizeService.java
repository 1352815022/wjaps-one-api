package com.donlim.aps.service;

import com.changhong.sei.core.context.ContextUtil;
import com.changhong.sei.core.dao.BaseTreeDao;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.dto.serach.SearchFilter;
import com.changhong.sei.core.log.LogUtil;
import com.changhong.sei.core.service.BaseTreeService;
import com.changhong.sei.core.service.bo.OperateResultWithData;
import com.changhong.sei.serial.sdk.SerialService;
import com.donlim.aps.dao.ApsOrganizeDao;
import com.donlim.aps.entity.ApsOrganize;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;


/**
 * 组织机构(ApsOrganize)业务逻辑实现类
 *
 * @author sei
 * @since 2022-04-25 11:27:41
 */
@Service
public class ApsOrganizeService extends BaseTreeService<ApsOrganize> {
    @Autowired
    private ApsOrganizeDao dao;

    @Autowired(required = false)
    private SerialService serialService;

    @Override
    protected BaseTreeDao<ApsOrganize> getDao() {
        return dao;
    }

    /**
     * 获取组织机构树
     * @return
     */
    public ApsOrganize findTree(){
        String tenantCode = ContextUtil.getTenantCode();
        List<ApsOrganize> rootNodes = getAllRootNode();
        Optional<ApsOrganize> first = rootNodes.stream().sorted(Comparator.comparing(ApsOrganize::getCode)).findFirst();
        if (first.isPresent()){
            ApsOrganize apsOrganize = first.get();
            return getTree(apsOrganize.getId());
        }
        return null;
    }

    @Override
    @Transactional
    public OperateResultWithData<ApsOrganize> save(ApsOrganize entity) {
        if (StringUtils.isBlank(entity.getCode())){
            String number = serialService.getNumber(entity.getClass());
            LogUtil.debug(number);
            entity.setCode(number);
        }
        return super.save(entity);
    }

    /**
     * 获取组织架构树（不包含冻结）
     * @return
     */
    public List<ApsOrganize> findOrgTreeWithoutFrozen(){
        List<ApsOrganize> list = dao.findAllUnfrozen();
        return buildTree(list);
    }

    /**
     * 获取生产线（不包含冻结）
     * @return
     */
    public List<ApsOrganize> getLinesWithoutFrozen(){
        Search search = new Search();
        search.addFilter(new SearchFilter("category","Line"))
                .addFilter(new SearchFilter("frozen",false));
        List<ApsOrganize> lines = dao.findByFilters(search);
        return lines;
    }
}
