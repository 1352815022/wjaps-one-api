package com.donlim.aps.connector;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.donlim.aps.dto.McasManualYieldApiDto;
import com.donlim.aps.dto.McasProcessApiDto;
import com.donlim.aps.dto.McasYieldApiDto;
import com.donlim.aps.entity.McasProcess;
import com.donlim.aps.entity.McasYield;
import com.donlim.aps.entity.ScmXbDelivery;
import com.donlim.aps.util.CompanyEnum;
import com.donlim.aps.util.DateUtils;
import com.donlim.aps.webservice.mcas.DONLIMMCASQUERYSYNC;
import com.donlim.aps.webservice.mcas.DONLIMMCASQUERYSYNC_Service;
import com.donlim.aps.webservice.mcas.SvcHdrType;
import com.donlim.aps.webservice.mcas.SvcHdrsTypes;

import javax.xml.ws.Holder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:对接mcas
 * @Author: chenzhiquan
 * @Date: 2022/6/14.
 */
public class McasConnector {
    private static final SvcHdrType svcHdr = new SvcHdrType();
    public static final Holder<SvcHdrsTypes> svcHdrs = new Holder<>();
    private static DONLIMMCASQUERYSYNC_Service service = new DONLIMMCASQUERYSYNC_Service();
    private static DONLIMMCASQUERYSYNC sync = service.getDONLIMMCASQUERYSYNCSOAP();
    public static final Holder<String> user = new Holder<>();
    /**
     * 获取mcas冲压线产量
     * @param company 公司
     * @param date 报工日期
     * @return
     * @throws Exception
     */
    public static List<McasYield> getCYYieldData(String company, LocalDate date) throws Exception {
        List<McasYield> mcasYieldList = new ArrayList<>();
        svcHdr.setSOURCEID("WJAPS");
        svcHdr.setDESTINATIONID("SCM");
        svcHdr.setTYPE("SELECT");
        svcHdr.setNO("584");
        svcHdr.setIPADDRESS("192.168.117.8");
        svcHdr.setBO("获取产量");
        svcHdr.setBodyJson("{CompanyCode:\""+company+"\",PlanDate:\"" + DateUtils.LocalDateToString(date) + "\"}");
        List<ScmXbDelivery> list = new ArrayList<>();
        long beginTime = System.currentTimeMillis();
       sync.donlimMCASQUERYSYNC(svcHdr,user,svcHdrs);
        if ("S".equals(svcHdrs.value.getRCODE())) {
            JSONObject result = JSONObject.parseObject(svcHdrs.value.getResultJson());
            McasYieldApiDto mcasYieldDataDto = JSON.toJavaObject(result, McasYieldApiDto.class);
            if (mcasYieldDataDto.getTable().size() > 0) {
                for (McasYieldApiDto.TableDTO yeild : mcasYieldDataDto.getTable()) {
                    McasYield mcasYield=new McasYield();
                    mcasYield.setCompanyCode(yeild.getCompanyCode());
                    mcasYield.setCompanyName(yeild.getOrganizename());
                    mcasYield.setLineName(yeild.getOrganizename1());
                    mcasYield.setEmployeeCode(yeild.getCode());
                    mcasYield.setEmployeeName(yeild.getName());
                    mcasYield.setDate(yeild.getMcasDate());
                    if ("01064721".toUpperCase().equals(company)){
                        mcasYield.setOrgId(1002104064649730L); //五金二
                    }else if ("01064721".toUpperCase().equals(company)){
                        mcasYield.setOrgId(1002706284169876L);  //五金一
                    }
                    mcasYield.setMaterialCode(yeild.getCinvcode());
                    mcasYield.setMaterialName(yeild.getCinvname());
                    mcasYield.setProcess(yeild.getProcess());
                    mcasYield.setMo(yeild.getCsocode());
                    mcasYield.setQty(yeild.getScanQty());
                    mcasYield.setMcasID(yeild.getMcasID());
                    mcasYieldList.add(mcasYield);

                }
            }
        }
        return mcasYieldList;
    }


    /**
     * 获取mcas手工报产量（清冼、喷粉）
     * @param company 公司
     * @param date 报工日期
     * @return
     * @throws Exception
     */
    public static List<McasYield> getManualYieldData(String company, LocalDate date) throws Exception {
        List<McasYield> mcasYieldList = new ArrayList<>();
        svcHdr.setSOURCEID("WJAPS");
        svcHdr.setDESTINATIONID("SCM");
        svcHdr.setTYPE("SELECT");
        svcHdr.setNO("586");
        svcHdr.setIPADDRESS("192.168.117.8");
        svcHdr.setBO("获取产量");
        svcHdr.setBodyJson("{organizeid:\""+company+"\",savedate:\"" + DateUtils.LocalDateToString(date) + "\"}");
        List<ScmXbDelivery> list = new ArrayList<>();
        long beginTime = System.currentTimeMillis();
        sync.donlimMCASQUERYSYNC(svcHdr,user,svcHdrs);
        if ("S".equals(svcHdrs.value.getRCODE())) {
            JSONObject result = JSONObject.parseObject(svcHdrs.value.getResultJson());
            McasManualYieldApiDto mcasYieldDataDto = JSON.toJavaObject(result, McasManualYieldApiDto.class);
            if (mcasYieldDataDto.getTable().size() > 0) {
                for (McasManualYieldApiDto.TableDTO yeild : mcasYieldDataDto.getTable()) {
                    McasYield mcasYield=new McasYield();
                    mcasYield.setCompanyCode(company);

                    mcasYield.setEmployeeCode("");
                    mcasYield.setEmployeeName("");
                    mcasYield.setDate(yeild.getSavedate());
                    mcasYield.setLineName(yeild.getOrganizename());
                    if ("01064721".toUpperCase().equals(company)){
                        mcasYield.setOrgId(1002104064649730L); //五金二
                        mcasYield.setCompanyName("五金件二公司");
                    }else if ("01064721".toUpperCase().equals(company)){
                        mcasYield.setOrgId(1002706284169876L);  //五金一
                        mcasYield.setCompanyName("五金件一公司");
                    }
                    mcasYield.setMaterialCode(yeild.getCInvCode());
                    mcasYield.setMaterialName(yeild.getCInvName());
                    mcasYield.setProcess("");
                    mcasYield.setMo(yeild.getCsocode());
                    mcasYield.setQty(yeild.getPackqty());
                    mcasYieldList.add(mcasYield);
                }
            }
        }
        return mcasYieldList;
    }

    /**
     * 获取mcas工序
     * @param company 公司
     * @param date
     * @return
     * @throws Exception
     */
    public static List<McasProcess> getMcasProcess(String company, String date) throws Exception {
        List<McasProcess> mcasProcessList = new ArrayList<>();
        svcHdr.setSOURCEID("WJAPS");
        svcHdr.setDESTINATIONID("SCM");
        svcHdr.setTYPE("SELECT");
        svcHdr.setNO("585");
        svcHdr.setIPADDRESS("192.168.117.8");
        svcHdr.setBO("获取工序");
        svcHdr.setBodyJson("{organizeid:\""+company+"\",CreateDate:\"" + date + "\"}");
        long beginTime = System.currentTimeMillis();
        sync.donlimMCASQUERYSYNC(svcHdr,user,svcHdrs);
        if ("S".equals(svcHdrs.value.getRCODE())) {
            JSONObject result = JSONObject.parseObject(svcHdrs.value.getResultJson());
            McasProcessApiDto mcasYieldDataDto = JSON.toJavaObject(result, McasProcessApiDto.class);
            if (mcasYieldDataDto.getTable().size() > 0) {
                for (McasProcessApiDto.TableDTO process : mcasYieldDataDto.getTable()) {
                    McasProcess mcasProcess=new McasProcess();
                    mcasProcess.setCompanyCode(company);
                    mcasProcess.setItemName(process.getCinvName());
                    mcasProcess.setType("");
                    mcasProcess.setMcasId(process.getId());
                    mcasProcess.setProcess(process.getProcess());
                    mcasProcess.setSortNo(process.getSortNo());
                    mcasProcess.setLineCode(process.getOrganizeid1());
                    mcasProcess.setLineName(process.getOrganizename());
                    mcasProcess.setCreatedDate(process.getCreateDate());
                    if (CompanyEnum.WJ2_MCAS.getCode().toUpperCase().equals(company)){
                        mcasProcess.setOrgId(1002104064649730L);
                        mcasProcess.setCompanyName(CompanyEnum.WJ2_MCAS.getName());
                    }else if (CompanyEnum.WJ1_MCAS.getCode().toUpperCase().equals(company)){
                        mcasProcess.setOrgId(1002706284169876L);
                        mcasProcess.setCompanyName(CompanyEnum.WJ1_MCAS.getName());
                    }

                    mcasProcessList.add(mcasProcess);
                }
            }
        }
        return mcasProcessList;
    }
}
