package com.donlim.aps.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.dao.U9BomDao;
import com.donlim.aps.dao.U9MaterialDao;
import com.donlim.aps.dto.U9MaterialDto;
import com.donlim.aps.dto.upload.EndQtyDTO;
import com.donlim.aps.dto.upload.PowerDTO;
import com.donlim.aps.entity.U9Bom;
import com.donlim.aps.entity.U9Material;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * 料品表(U9Material)业务逻辑实现类
 *
 * @author sei
 * @since 2022-05-09 13:45:01
 */
@Service
public class U9MaterialService extends BaseEntityService<U9Material> {
    @Autowired
    private U9MaterialDao dao;
    @Autowired
    private U9BomDao bomDao;

    @Override
    protected BaseEntityDao<U9Material> getDao() {
        return dao;
    }



    /**
     * 获取物料信息
     * @param codes 料号
     * @return
     */
    public List<U9Material>findByCodeIn(List<String>codes){
        return dao.findByCodeIn(codes);
    }
    /**
     * tip:租户模式，只能查找属于租户的料品
     * 计算料品损耗
     */
    public void calculateScrap(){
        List<U9Material> all = this.findAll();
        for (U9Material material : all){
            List<U9Bom> bom = bomDao.findByMasterId(material.getId());
            if (bom.size() > 1 ){
                material.setScrap(new BigDecimal(0));
                material.setRemark("无法计算损耗，存在多行Bom");
            }else if (bom.size() == 0){
                material.setScrap(new BigDecimal(0));
                material.setRemark("无法计算损耗，没找到bom");
            }else{
                material.setScrap(bom.get(0).getScrap());
            }
        }
        this.save(all);
        dao.flush();
    }
    public List<U9Material>findByProductModelIsNotNull(){
        return dao.findByProductModelIsNotNull();
    }


    /**
     * 保存上月期末数
     *
     * @param endQtyDTOList
     * @throws IOException
     */
    @Transactional(rollbackFor = Exception.class)
    public void uploadEndQty(List<EndQtyDTO> endQtyDTOList) throws IOException {
        List<String> codeList = endQtyDTOList.stream().map(EndQtyDTO::getMaterialCode).collect(Collectors.toList());
        List<U9Material> byCodeInList = dao.findByCodeIn(codeList);
        for (U9Material u9Material :byCodeInList){
            Optional<EndQtyDTO> dto = endQtyDTOList.stream().filter(e -> e.getMaterialCode().equals(u9Material.getCode())).findFirst();
            if(dto.isPresent()){
                u9Material.setEndQty(dto.get().getEndQty());
            }
        }
        save(byCodeInList);

    }
    /**
     * 保存上月期末数
     *
     * @param u9MaterialDtoList
     * @throws IOException
     */
    @Transactional(rollbackFor = Exception.class)
    public void uploadMaterialType(List<U9MaterialDto> u9MaterialDtoList) throws IOException {
        List<String> codeList = u9MaterialDtoList.stream().map(U9MaterialDto::getCode).collect(Collectors.toList());
        List<U9Material> byCodeInList = dao.findByCodeIn(codeList);
        for (U9Material u9Material :byCodeInList){
            u9Material.setCalc(false);
        }
        save(byCodeInList);

    }

    /**
     * 保存清洗/喷粉配置
     *
     * @param powerDTOList
     * @throws IOException
     */
    @Transactional(rollbackFor = Exception.class)
    public void uploadPower(List<PowerDTO> powerDTOList) throws IOException {
        powerDTOList=powerDTOList.stream().filter(a->StringUtils.isNotBlank(a.getMaterialCode())).collect(Collectors.toList());
        List<String> codeList = powerDTOList.stream().map(PowerDTO::getMaterialCode).distinct().collect(Collectors.toList());
        List<U9Material> byCodeInList = dao.findByCodeIn(codeList);
        //记录料的最大工序
        HashMap<String,Integer>materialSortMaxNo = new HashMap<>();
        for (U9Material u9Material :byCodeInList){
            int sortNo=0;
            List<PowerDTO> powerDTOS = powerDTOList.stream().filter(e -> e.getMaterialCode().equals(u9Material.getCode())).collect(Collectors.toList());
            for(PowerDTO powerDTO:powerDTOS){
                BigDecimal capacity=BigDecimal.ZERO;
                if(StringUtils.isNumeric(powerDTO.getSortNo())&& StringUtils.isNotBlank(powerDTO.getCapacity())&& parseInterger(powerDTO.getCapacity())){
                    sortNo=Integer.parseInt(powerDTO.getSortNo());
                    capacity=new BigDecimal(Math.round(Math.ceil(Double.parseDouble(powerDTO.getCapacity()))));
                    if (materialSortMaxNo.containsKey(powerDTO.getMaterialCode())) {
                        if (sortNo > materialSortMaxNo.get(powerDTO.getMaterialCode())) {
                            u9Material.setCapacity(capacity);
                            u9Material.setPowderArea(powerDTO.getPowderArea());
                            u9Material.setPowderModel(powerDTO.getPowderModel());
                            u9Material.setWashArea(powerDTO.getWashArea());
                            materialSortMaxNo.put(u9Material.getCode(),sortNo);
                        }
                    } else {
                        u9Material.setCapacity(capacity);
                        u9Material.setPowderArea(powerDTO.getPowderArea());
                        u9Material.setPowderModel(powerDTO.getPowderModel());
                        u9Material.setWashArea(powerDTO.getWashArea());
                        materialSortMaxNo.put(u9Material.getCode(), sortNo);
                    }
                }
            }
        }
        save(byCodeInList);
    }

    private  static boolean parseInterger(String str){
            try {
                Double.parseDouble(str);
                return true;
            }catch (Exception e){
                return false;
            }
    }

}
