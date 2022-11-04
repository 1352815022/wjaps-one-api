package com.donlim.aps.entity;

import com.changhong.sei.core.entity.BaseAuditableEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * (McasProcess)实体类
 *
 * @author sei
 * @since 2022-06-15 14:04:10
 */
@Entity
@Table(name = "mcas_process")
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
public class McasProcess extends BaseAuditableEntity implements Serializable {
    private static final long serialVersionUID = -60234224364085191L;
    /**
     * 公司编码
     */
    @Column(name = "company_code")
    private String companyCode;
    /**
     * 公司名称
     */
    @Column(name = "company_name")
    private String companyName;

    @Column(name = "material_code")
    private  String materialCode;
    /**
     * 线别编码
     */
    @Column(name = "line_code")
    private String lineCode;
    /**
     * 线别名称
     */
    @Column(name = "line_name")
    private String lineName;
    /**
     * 料名
     */
    @Column(name = "item_name")
    private String itemName;
    /**
     * 工序
     */
    @Column(name = "process")
    private String process;
    /**
     * 0为第一工序，1为最后工序
     */
    @Column(name = "type")
    private String type;
    /**
     * 顺序
     */
    @Column(name = "sort_no")
    private Integer sortNo;
    /**
     * 租户代码
     */
    @Column(name = "tenant_code")
    private String tenantCode;

    /**
     * U9组织编码
     */
    @Column(name = "org_id")
    private long orgId;

    /**
     * U9组织编码
     */
    @Column(name = "mcas_id")
    private long mcasId;
}
