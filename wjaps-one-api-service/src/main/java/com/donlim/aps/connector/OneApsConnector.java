package com.donlim.aps.connector;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.donlim.aps.dto.ApsPlanApiDto;
import com.donlim.aps.entity.OneApsPlanData;
import com.donlim.aps.util.DateUtils;
import com.donlim.aps.webservice.aps.DONLIMAPSQUERYSYNC;
import com.donlim.aps.webservice.aps.DONLIMAPSQUERYSYNC_Service;
import com.donlim.aps.webservice.aps.SvcHdrType;
import com.donlim.aps.webservice.aps.SvcHdrsType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: chenzhiquan
 * @Date: 2022/7/27.
 */
public class OneApsConnector {

    private static final SvcHdrType svcHdr = new SvcHdrType();
    private static DONLIMAPSQUERYSYNC_Service service = new DONLIMAPSQUERYSYNC_Service();
    private static DONLIMAPSQUERYSYNC sync = service.getDONLIMAPSQUERYSYNCSOAP();
   // public static final Holder<String> user = new Holder<>();

    /**
     * 下载APS数据
     * @param date
     * @return
     * @throws Exception
     */
    public static List<OneApsPlanData> getOneApsData(LocalDate date) throws Exception {
        svcHdr.setSOURCEID("WJAPS");
        svcHdr.setDESTINATIONID("SCM");
        svcHdr.setTYPE("SELECT");
        svcHdr.setNO("102-08");
        svcHdr.setIPADDRESS("192.168.117.8");
        svcHdr.setBO("");
        svcHdr.setBodyJson("{WorkingDay:\"" + DateUtils.LocalDateToString(date) + "\"}");
        List<OneApsPlanData> list = new ArrayList<>();
        long beginTime = System.currentTimeMillis();
        SvcHdrsType svcHdrsType = sync.donlimAPSQUERYSYNC(svcHdr);
        if ("S".equals(svcHdrsType.getRCODE())) {
            JSONObject result = JSONObject.parseObject(svcHdrsType.getResultJson());
            ApsPlanApiDto apsPlanApiDto = JSON.toJavaObject(result, ApsPlanApiDto.class);
            if (apsPlanApiDto.getTable().size() > 0) {
                for (ApsPlanApiDto.TableDTO planData : apsPlanApiDto.getTable()) {
                    OneApsPlanData oneApsPlanData=new OneApsPlanData();
                    oneApsPlanData.setDate(planData.getWorkingDay());
                    oneApsPlanData.setQty(planData.getWorkingQty());
                    oneApsPlanData.setOrderNo(planData.getOrderNo());
                    list.add(oneApsPlanData);
                }
            }
        }
        return list;
    }
}
