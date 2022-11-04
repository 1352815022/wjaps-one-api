package com.donlim.aps.api;

import com.changhong.sei.core.api.BaseTreeApi;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.Search;
import com.donlim.aps.dto.ApsOrganizeDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 * 组织机构(ApsOrganize)API
 *
 * @author sei
 * @since 2022-04-25 11:27:43
 * @FeignClient(name = "apsOrganize")
 */
@Valid
@FeignClient(name = "wjaps-one-api", path = ApsOrganizeApi.PATH)
public interface ApsOrganizeApi extends BaseTreeApi<ApsOrganizeDto> {
    String PATH = "apsOrganize";

    /**
     * 获取组织结构树
     * @return 组织结构树
     */
    @GetMapping(path = "findOrgTree")
    @ApiOperation(value = "获取组织机构树",notes = "获取组织机构树")
    ResultData<ApsOrganizeDto> findTree();

    /**
     * 获取组织结构树（不包含冻结）
     * @return
     */
    @GetMapping(path = "findOrgTreeWithoutFrozen")
    @ApiOperation(value = "获取组织机构树（不包含冻结）",notes = "获取组织机构树（不包含冻结）")
    ResultData<List<ApsOrganizeDto>> findOrgTreeWithoutFrozen();

    /**
     * 获取所有生产线（不包含冻结）
     * @return
     */
    @GetMapping(path = "getLinesWithoutFrozen")
    @ApiOperation(value = "获取所有生产线（不包含冻结）",notes = "获取所有生生产线（不包含冻结）")
    ResultData<List<ApsOrganizeDto>> getLines();

    /**
     * 根据过滤条件获取组织
     * @param search
     * @return
     */
    @PostMapping(path = "findByFilter")
    @ApiOperation(value = "根据过滤条件获取组织",notes = "根据过滤条件获取组织")
    ResultData<List<ApsOrganizeDto>> findByFilter(@RequestBody Search search);
}
