package com.donlim.aps.connector;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.donlim.aps.dto.ScmDeliveryApiDto;
import com.donlim.aps.dto.ScmDeliveryDetailApiDto;
import com.donlim.aps.entity.ScmXbDelivery;
import com.donlim.aps.entity.ScmXbDeliveryPlan;
import com.donlim.aps.util.DateUtils;
import com.donlim.aps.webservice.scm.DONLIMSCMQUERYSYNC;
import com.donlim.aps.webservice.scm.DONLIMSCMQUERYSYNC_Service;
import com.donlim.aps.webservice.scm.SvcHdrType;
import com.donlim.aps.webservice.scm.SvcHdrsTypes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:对接SCM
 * @Author: chenzhiquan
 * @Date: 2022/5/17.
 */
public class ScmConnector {

    private static final SvcHdrType svcHdr = new SvcHdrType();
    private static DONLIMSCMQUERYSYNC_Service service = new DONLIMSCMQUERYSYNC_Service();
    private static DONLIMSCMQUERYSYNC sync = service.getDONLIMSCMQUERYSYNCSOAP();

    /**
     * 获取一级单位送货计划(销售)
     * @param supplier 供应商名称
     * @param date 送货开始日期
     * @return
     * @throws Exception
     */
    public static List<ScmXbDelivery> getDeliveryData(String supplier, LocalDate date) throws Exception {
        LocalDate searchDate=date.plusDays(-15);
        svcHdr.setSOURCEID("WJAPS");
        svcHdr.setDESTINATIONID("SCM");
        svcHdr.setTYPE("SELECT");
        svcHdr.setNO("489-02");
        svcHdr.setIPADDRESS("192.168.117.8");
        svcHdr.setBO("");
        svcHdr.setBodyJson("{supplier:\""+supplier+"\",begDate:\"" + DateUtils.LocalDateToString(searchDate) + "\"}");
        List<ScmXbDelivery> list = new ArrayList<>();
        long beginTime = System.currentTimeMillis();
        SvcHdrsTypes svcHdrsTypes  =sync.donlimSCMQUERYSYNC(svcHdr);
        if ("Y".equals(svcHdrsTypes.getRCODE())) {
            JSONObject result = JSONObject.parseObject(svcHdrsTypes.getResultJson());
            ScmDeliveryApiDto scmDeliveryDataDto = JSON.toJavaObject(result, ScmDeliveryApiDto.class);
                if (scmDeliveryDataDto.getTable().size() > 0) {
                    for (ScmDeliveryApiDto.TableDTO scmData : scmDeliveryDataDto.getTable()) {
                        ScmXbDelivery scmXbDelivery= new ScmXbDelivery();
                        scmXbDelivery.setPoLineId(scmData.getPOLineID());
                        scmXbDelivery.setDeliveryStartDate(scmData.getBegDate());
                        scmXbDelivery.setDeliveryEndDate(scmData.getEndDate());
                        scmXbDelivery.setPo(scmData.getPurchaseOrder());
                        scmXbDelivery.setOrderNo(scmData.getOrderno());

                        if ("0BS20".toUpperCase().equals(supplier)){
                            //五金二
                            scmXbDelivery.setOrgId(1002104064649730L);
                        }else if ("0wj10".toUpperCase().equals(supplier)){
                            //五金一
                            scmXbDelivery.setOrgId(1002706284169876L);
                        }
                        scmXbDelivery.setMaterialCode(scmData.getItemCode());
                        scmXbDelivery.setMaterialName(scmData.getItemName());
                        scmXbDelivery.setSpec(scmData.getSpecs());
                        scmXbDelivery.setUnit(scmData.getUnitName());
                        scmXbDelivery.setSupplierCode(scmData.getSupplierCode());
                        scmXbDelivery.setSupplierName(scmData.getSupplierShortName());
                        scmXbDelivery.setOweQty(scmData.getNodeliveryqty());
                        scmXbDelivery.setDeliveryQty(scmData.getDeliveryQty());
                        scmXbDelivery.setDayQty(scmData.getDeliveryqty1());
                        scmXbDelivery.setSumArrivalQty(scmData.getTotalArriveQtyCU());
                        scmXbDelivery.setBuyer(scmData.getPurchaseorderman());
                        scmXbDelivery.setCompanyName(scmData.getCompanyname());
                        scmXbDelivery.setProductModel(scmData.getProductModel());
                        scmXbDelivery.setType("1");
                        list.add(scmXbDelivery);
                    }
                }
        }
        return list;
    }
    /**
     * 获取一级单位送货计划明细(销售)
     * @param supplier 供应商名称
     * @param date 送货开始日期
     * @return
     * @throws Exception
     */
    public static List<ScmXbDeliveryPlan> getDeliveryDetailData(String supplier, LocalDate date) throws Exception {
        LocalDate searchDate=date.plusDays(-15);
        svcHdr.setSOURCEID("WJAPS");
        svcHdr.setDESTINATIONID("SCM");
        svcHdr.setTYPE("SELECT");
        svcHdr.setNO("489-04");
        svcHdr.setIPADDRESS("192.168.117.8");
        svcHdr.setBO("");
        svcHdr.setBodyJson("{supplier:\""+supplier+"\",begDate:\"" + DateUtils.LocalDateToString(searchDate)  + "\"}");
        List<ScmXbDeliveryPlan> list = new ArrayList<>();
        long beginTime = System.currentTimeMillis();
        SvcHdrsTypes svcHdrsTypes  =sync.donlimSCMQUERYSYNC(svcHdr);
        if ("Y".equals(svcHdrsTypes.getRCODE())) {
            JSONObject result = JSONObject.parseObject(svcHdrsTypes.getResultJson());
            ScmDeliveryDetailApiDto scmDeliveryDataDto = JSON.toJavaObject(result, ScmDeliveryDetailApiDto.class);
            if (scmDeliveryDataDto.getTable().size() > 0) {
                for (ScmDeliveryDetailApiDto.TableDTO scmData : scmDeliveryDataDto.getTable()) {
                    ScmXbDeliveryPlan scmXbDeliveryPlan= new ScmXbDeliveryPlan();
                    scmXbDeliveryPlan.setDeliveryDate(scmData.getDeliveryDate());
                    scmXbDeliveryPlan.setQty(scmData.getDeliveryQty());
                    scmXbDeliveryPlan.setPurchaseOrder(scmData.getPurchaseOrder());
                    scmXbDeliveryPlan.setMaterialCode(scmData.getItemCode());
                    scmXbDeliveryPlan.setPoLineId(scmData.getPOLineID());
                    list.add(scmXbDeliveryPlan);
                }
            }
        }
        return list;
    }

    /**
     * 获取供应商送货计划（采购）
     * @param company 采购单位
     * @param date 送货开始日期
     * @return
     * @throws Exception
     */
    public static List<ScmXbDelivery> getPurchaseData(String company,LocalDate date) throws Exception {
        svcHdr.setSOURCEID("WJAPS");
        svcHdr.setDESTINATIONID("SCM");
        svcHdr.setTYPE("SELECT");
        svcHdr.setNO("489-03");
        svcHdr.setIPADDRESS("192.168.117.8");
        svcHdr.setBO("");
        svcHdr.setBodyJson("{Companyname:\""+company+"\",begDate:\"" + DateUtils.LocalDateToString(date)  + "\"}");
        List<ScmXbDelivery> list = new ArrayList<>();
        long beginTime = System.currentTimeMillis();
        SvcHdrsTypes svcHdrsTypes  =sync.donlimSCMQUERYSYNC(svcHdr);
        if ("Y".equals(svcHdrsTypes.getRCODE())) {
            JSONObject result = JSONObject.parseObject(svcHdrsTypes.getResultJson());
            ScmDeliveryApiDto scmDeliveryDataDto = JSON.toJavaObject(result, ScmDeliveryApiDto.class);
            if (scmDeliveryDataDto.getTable().size() > 0) {
                for (ScmDeliveryApiDto.TableDTO scmData : scmDeliveryDataDto.getTable()) {
                    ScmXbDelivery scmXbDelivery= new ScmXbDelivery();
                    scmXbDelivery.setPoLineId(scmData.getPOLineID());
                    scmXbDelivery.setPoLineNo(scmData.getDocLineno()+"");
                    scmXbDelivery.setDeliveryStartDate(scmData.getBegDate());
                    scmXbDelivery.setDeliveryEndDate(scmData.getEndDate());
                    scmXbDelivery.setPo(scmData.getPurchaseOrder());
                    scmXbDelivery.setOrderNo(scmData.getOrderno());
                    scmXbDelivery.setOrgId(scmData.getU9org());
                    scmXbDelivery.setMaterialCode(scmData.getItemCode());
                    scmXbDelivery.setMaterialName(scmData.getItemName());
                    scmXbDelivery.setSpec(scmData.getSpecs());
                    scmXbDelivery.setUnit(scmData.getUnitName());
                    scmXbDelivery.setSupplierCode(scmData.getSupplierCode());
                    scmXbDelivery.setSupplierName(scmData.getSupplierShortName());
                    scmXbDelivery.setOweQty(scmData.getNodeliveryqty());
                    scmXbDelivery.setDeliveryQty(scmData.getDeliveryQty());
                    scmXbDelivery.setDayQty(scmData.getDeliveryqty1());
                    scmXbDelivery.setSumArrivalQty(scmData.getTotalArriveQtyCU());
                    scmXbDelivery.setBuyer(scmData.getPurchaseorderman());
                    scmXbDelivery.setCompanyName(scmData.getCompanyname());
                    scmXbDelivery.setProductModel(scmData.getProductModel());
                    scmXbDelivery.setType("0");
                    list.add(scmXbDelivery);
                }
            }
        }
        return list;
    }

    /**
     * 获取供应商送货计划明细（采购）
     * @param company 采购单位
     * @param date 送货开始日期
     * @return
     * @throws Exception
     */
    public static List<ScmXbDeliveryPlan> getPurchaseDetailData(String company, LocalDate date) throws Exception {
        svcHdr.setSOURCEID("WJAPS");
        svcHdr.setDESTINATIONID("SCM");
        svcHdr.setTYPE("SELECT");
        svcHdr.setNO("489-05");
        svcHdr.setIPADDRESS("192.168.117.8");
        svcHdr.setBO("");
        svcHdr.setBodyJson("{Companyname:\""+company+"\",begDate:\"" + DateUtils.LocalDateToString(date) + "\"}");
        List<ScmXbDeliveryPlan> list = new ArrayList<>();
        long beginTime = System.currentTimeMillis();
        SvcHdrsTypes svcHdrsTypes  =sync.donlimSCMQUERYSYNC(svcHdr);
        if ("Y".equals(svcHdrsTypes.getRCODE())) {
            JSONObject result = JSONObject.parseObject(svcHdrsTypes.getResultJson());
            ScmDeliveryDetailApiDto scmDeliveryDataDto = JSON.toJavaObject(result, ScmDeliveryDetailApiDto.class);
            if (scmDeliveryDataDto.getTable().size() > 0) {
                for (ScmDeliveryDetailApiDto.TableDTO scmData : scmDeliveryDataDto.getTable()) {
                    ScmXbDeliveryPlan scmXbDeliveryPlan= new ScmXbDeliveryPlan();
                    scmXbDeliveryPlan.setDeliveryDate(scmData.getDeliveryDate());
                    scmXbDeliveryPlan.setQty(scmData.getDeliveryQty());
                    scmXbDeliveryPlan.setPurchaseOrder(scmData.getPurchaseOrder());
                    scmXbDeliveryPlan.setOrderNo(scmData.getOrderNo());
                    scmXbDeliveryPlan.setPoLineId(scmData.getPOLineID());
                    scmXbDeliveryPlan.setMaterialCode(scmData.getItemCode());
                    list.add(scmXbDeliveryPlan);
                }
            }
        }
        return list;
    }
}
