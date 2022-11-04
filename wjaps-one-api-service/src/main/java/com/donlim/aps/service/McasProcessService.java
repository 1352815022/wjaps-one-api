package com.donlim.aps.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.aps.dao.McasProcessDao;
import com.donlim.aps.dao.U9MaterialDao;
import com.donlim.aps.entity.McasProcess;
import com.donlim.aps.entity.U9Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * (McasProcess)业务逻辑实现类
 *
 * @author sei
 * @since 2022-06-15 14:04:12
 */
@Service
public class McasProcessService extends BaseEntityService<McasProcess> {
    @Autowired
    private McasProcessDao dao;
    @Autowired
    private U9MaterialDao u9MaterialDao;

    @Override
    protected BaseEntityDao<McasProcess> getDao() {
        return dao;
    }

    public List<McasProcess> findMcasProcessesByItemNameIn(List<String> itemList) {
        return dao.findMcasProcessesByItemNameIn(itemList);
    }

    /**
     * 初始化工序表的首尾工序首工序为0，尾工序为1
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateProcessTask() {
        try {
            List<U9Material> u9MaterialList = u9MaterialDao.findByProductModelIsNotNull();
            List<McasProcess> list = findAll();
            List<McasProcess> saveList = new ArrayList<>();
            List<McasProcess> searchList = new ArrayList<>();
            for (McasProcess process : list) {
                long count = searchList.stream().filter(x -> x.getCompanyCode().equals(process.getCompanyCode()))
                        .filter(y -> y.getLineCode().equals(process.getLineCode()))
                        .filter(i -> i.getItemName().equals(process.getItemName())).count();
                if (count > 0) {
                    continue;
                }
                //找到同一个线别同一个产品所有工序
                List<McasProcess> processList = list.stream()
                        .filter(x -> x.getCompanyCode().equals(process.getCompanyCode()))
                        .filter(y -> y.getLineCode().equals(process.getLineCode()))
                        .filter(i -> i.getItemName().equals(process.getItemName()))
                        .collect(Collectors.toList());
                Comparator<McasProcess> comparator = Comparator.comparing(McasProcess::getSortNo);
                processList.sort(comparator);
                if (processList.size() > 0) {
                    //searchList.add(process);
                    Optional<U9Material> u9Material = u9MaterialList.stream().filter(a -> a.getProductName().equals(process.getItemName())).findFirst();
                    String materialCode="";
                    if(u9Material.isPresent()){
                        materialCode= u9Material.get().getCode();
                    }
                    //取出第一道工序和最后一道工序
                    McasProcess firstProcess = processList.get(0);
                    firstProcess.setType("0");
                    firstProcess.setMaterialCode(materialCode);
                    McasProcess lastProcess = processList.get(processList.size() - 1);
                    lastProcess.setType("1");
                    lastProcess.setMaterialCode(materialCode);
                    saveList.add(lastProcess);
                    saveList.add(firstProcess);
                }
            }
            save(saveList);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

}
