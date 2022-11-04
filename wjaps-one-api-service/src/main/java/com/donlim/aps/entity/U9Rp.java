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
 * 请购单(U9Rp)实体类
 *
 * @author sei
 * @since 2022-06-14 14:26:35
 */
@Entity
@Table(name = "u9_rp")
@DynamicInsert
@DynamicUpdate
public class U9Rp extends BaseAuditableEntity implements Serializable {
    private static final long serialVersionUID = -13653583767573335L;
    /**
     * 单号
     */
    @Column(name = "doc_no")
    private String docNo;
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
     * 核准数量
     */
    @Column(name = "approve_qty")
    private BigDecimal approveQty;
    /**
     * 已实收数量
     */
    @Column(name = "total_recieved_qty")
    private BigDecimal totalRecievedQty;
    /**
     * 需求数
     */
    @Column(name = "req_qty_pu")
    private BigDecimal reqQtyPu;
    /**
     * 组织id
     */
    @Column(name = "org_id")
    private Long orgId;
    /**
     * 状态 已核准  2 核准中  1  超额关闭  5  自然关闭  3  开立  0  短缺关闭  4
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

    public BigDecimal getApproveQty() {
        return approveQty;
    }

    public void setApproveQty(BigDecimal approveQty) {
        this.approveQty = approveQty;
    }

    public BigDecimal getTotalRecievedQty() {
        return totalRecievedQty;
    }

    public void setTotalRecievedQty(BigDecimal totalRecievedQty) {
        this.totalRecievedQty = totalRecievedQty;
    }

    public BigDecimal getReqQtyPu() {
        return reqQtyPu;
    }

    public void setReqQtyPu(BigDecimal reqQtyPu) {
        this.reqQtyPu = reqQtyPu;
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
