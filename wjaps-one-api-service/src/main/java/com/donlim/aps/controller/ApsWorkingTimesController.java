package com.donlim.aps.controller;

import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.api.ApsWorkingTimesApi;
import com.donlim.aps.dto.ApsWorkingTimesDto;
import com.donlim.aps.dto.MultiAddWorkingTimesDto;
import com.donlim.aps.entity.ApsWorkingTimes;
import com.donlim.aps.service.ApsWorkingTimesService;
import com.donlim.aps.util.ResultEnum;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 * 上班日历配置表(ApsWorkingTimes)控制类
 *
 * @author sei
 * @since 2022-05-04 10:15:31
 */
@RestController
@Api(value = "ApsWorkingTimesApi", tags = "上班日历配置表服务")
@RequestMapping(path = ApsWorkingTimesApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class ApsWorkingTimesController extends BaseEntityController<ApsWorkingTimes, ApsWorkingTimesDto> implements ApsWorkingTimesApi {
    /**
     * 上班日历配置表服务对象
     */
    @Autowired
    private ApsWorkingTimesService service;

    @Override
    public BaseEntityService<ApsWorkingTimes> getService() {
        return service;
    }

    @Override
    public ResultData<PageResult<ApsWorkingTimesDto>> findByPage(Search search) {
        return convertToDtoPageResult(service.findByPage(search));
    }

    @Override
    public ResultData<String> multiAdd(MultiAddWorkingTimesDto dto) {
        Period period = Period.between(dto.getEffectiveFrom(), dto.getEffectiveTo());
        int days = period.getDays();
        List<ApsWorkingTimes> list = new ArrayList<>();
        for (int i = 0 ; i <= days ; i++){
            ApsWorkingTimes entity = dtoModelMapper.map(dto, ApsWorkingTimes.class);
            entity.setWorkDate(dto.getEffectiveFrom().plusDays(i));
            list.add(entity);
        }
        try {
            service.save(list);
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            return ResultData.fail(ResultEnum.WORKING_TIMES_DUPLICATE.getMsg());
        }

        return ResultData.success();
    }
}
