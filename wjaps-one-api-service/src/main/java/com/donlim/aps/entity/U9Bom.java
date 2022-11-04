package com.donlim.aps.entity;

import com.changhong.sei.core.entity.ITenant;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 料品表bom(U9Bom)实体类
 *
 * @author sei
 * @since 2022-05-12 14:58:01
 */
@Entity
@Table(name = "u9_bom")
@DynamicInsert
@DynamicUpdate
@Data
public class U9Bom implements Serializable , ITenant {
    private static final long serialVersionUID = 620847436904330246L;

    @Id
    @Column(name = "material_id")
    private Long materialId;
    /**
     * 料品
     */
    @ManyToOne
    @JoinColumn(name = "material_id",insertable = false,updatable = false)
    private U9Material material;
    /**
     * 母件id
     */
    @Column(name = "master_id")
    private String masterId;
    /**
     * 组织id
     */
    @Column(name = "org_id")
    private Long orgId;
    /**
     * 用量
     */
    @Column(name = "qty")
    private BigDecimal qty;
    /**
     * 母料用量
     */
    @Column(name = "master_qty")
    private BigDecimal masterQty;
    /**
     * 损耗
     */
    @Column(name = "scrap")
    private BigDecimal scrap;
    /**
     * 不良率
     */
    @Column(name = "bad_rate")
    private Double badRate;
    /**
     * 工序
     */
    @Column(name = "process_group_info_id")
    private String processGroupInfoId;
    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;
    /**
     * 启用状态
     */
    @Column(name = "frozen")
    private Boolean frozen;
    /**
     * 租户代码
     */
    @Column(name = "tenant_code")
    private String tenantCode;



}
