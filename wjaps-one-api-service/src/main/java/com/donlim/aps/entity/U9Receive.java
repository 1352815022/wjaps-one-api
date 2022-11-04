package com.donlim.aps.entity;

import com.changhong.sei.core.entity.BaseAuditableEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * (U9Receive)实体类
 *
 * @author sei
 * @since 2022-06-14 14:26:36
 */
@Entity
@Table(name = "u9_receive")
@DynamicInsert
@DynamicUpdate
public class U9Receive extends BaseAuditableEntity implements Serializable {
    private static final long serialVersionUID = 517331586301680199L;
    /**
     * 单号
     */
    @Column(name = "doc_no")
    private String docNo;
    /**
     * 采购单号
     */
    @Column(name = "src_doc_no")
    private String srcDocNo;
    /**
     * 料id
     */
    @Column(name = "material_id")
    private Long materialId;
    /**
     * 品名
     */
    @Column(name = "material_name")
    private String materialName;
    /**
     * 料号
     */
    @Column(name = "material_code")
    private String materialCode;
    /**
     * 已实收数量
     */
    @Column(name = "arrive_qty")
    private BigDecimal arriveQty;
    /**
     * 组织id
     */
    @Column(name = "org_id")
    private Long orgId;
    /**
     * 状态   开立0，审核中3，入库确认4，业务关闭5，检验完成2
     */
    @Column(name = "status")
    private String status;

    @Column(name = "demand_code")
    private String demandCode;


    public String getDocNo() {
        return docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    public String getSrcDocNo() {
        return srcDocNo;
    }

    public void setSrcDocNo(String srcDocNo) {
        this.srcDocNo = srcDocNo;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public BigDecimal getArriveQty() {
        return arriveQty;
    }

    public void setArriveQty(BigDecimal arriveQty) {
        this.arriveQty = arriveQty;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDemandCode() {
        return demandCode;
    }

    public void setDemandCode(String demandCode) {
        this.demandCode = demandCode;
    }


}
