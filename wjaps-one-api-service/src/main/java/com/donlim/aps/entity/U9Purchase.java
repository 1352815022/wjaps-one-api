package com.donlim.aps.entity;

import com.changhong.sei.core.entity.BaseAuditableEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

/**
 * 采购/委外(U9Purchase)实体类
 *
 * @author sei
 * @since 2022-07-14 16:49:44
 */
@Entity
@Table(name = "u9_purchase")
@DynamicInsert
@DynamicUpdate
@Data
public class U9Purchase extends BaseAuditableEntity implements Serializable {
    private static final long serialVersionUID = -81644150618532675L;
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
     * 需求数量
     */
    @Column(name = "req_qty")
    private BigDecimal reqQty;
    /**
     * 实收数量
     */
    @Column(name = "receive_qty")
    private BigDecimal receiveQty;
    /**
     * 供应商名称
     */
    @Column(name = "supplier_name")
    private String supplierName;
    /**
     * 送货时间
     */
    @Column(name = "delivery_date")
    private LocalDate deliveryDate;
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
    /**
     * 租户代码
     */
    @Column(name = "tenant_code")
    private String tenantCode;




}
