package com.donlim.aps.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.changhong.sei.core.api.FindByPageApi;
import com.changhong.sei.core.dto.ResultData;
import com.donlim.aps.dto.U9MaterialDto;
import com.donlim.aps.dto.upload.EndQtyDTO;
import com.donlim.aps.dto.upload.PowerDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * 料品表(U9Material)API
 *
 * @author sei
 * @since 2022-05-09 13:45:03
 * @FeignClient(name = "u9Material")
 */
@Valid
@FeignClient(name = "wjaps-one-api", path = U9MaterialApi.PATH)
public interface U9MaterialApi extends BaseEntityApi<U9MaterialDto> , FindByPageApi<U9MaterialDto> {
    String PATH = "u9Material";

    /**
     * 导入上月期末数
     * @param list
     * @return
     * @throws IOException
     */
    @PostMapping(path = "uploadEndQty")
    @ApiOperation(value = "导入上月期末数",notes = "导入上月期末数")
    ResultData<String> uploadEndQty(@RequestBody List<EndQtyDTO> list) throws IOException;

    /**
     * 导入清洗/喷粉配置
     * @param list
     * @return
     * @throws IOException
     */
    @PostMapping(path = "uploadPower")
    @ApiOperation(value = "导入清洗/喷粉配置",notes = "导入清洗/喷粉配置")
    ResultData<String> uploadPower(@RequestBody List<PowerDTO> list) throws IOException;

    /**
     * 导入计算
     * @param list
     * @return
     * @throws IOException
     */
    @PostMapping(path = "uploadMaterialType")
    @ApiOperation(value = "导入不计算的料号",notes = "导入不计算的料号")
    ResultData<String> uploadMaterialType(@RequestBody List<U9MaterialDto> list) throws IOException;
}
