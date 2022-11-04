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
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * (McasYield)实体类
 *
 * @author sei
 * @since 2022-06-14 10:26:06
 */
@Entity
@Table(name = "mcas_yield")
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
public class McasYield extends BaseAuditableEntity implements Serializable {
    private static final long serialVersionUID = -71176646421555662L;
    @Column(name = "mcas_id")
    private Integer mcasID;
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
    /**
     * 线名称
     */
    @Column(name = "line_name")
    private String lineName;
    /**
     * 报工日期
     */
    @Column(name = "date")
    private LocalDate date;
    /**
     * 工号
     */
    @Column(name = "employee_code")
    private String employeeCode;
    /**
     * 姓名
     */
    @Column(name = "employee_name")
    private String employeeName;
    /**
     * 工单号
     */
    @Column(name = "mo")
    private String mo;
    /**
     * 料号
     */
    @Column(name = "material_code")
    private String materialCode;
    /**
     * 品名
     */
    @Column(name = "material_name")
    private String materialName;
    /**
     * 工序
     */
    @Column(name = "process")
    private String process;
    /**
     * 产量
     */
    @Column(name = "qty")
    private BigDecimal qty;

    /**
     * U9组织编码
     */
    @Column(name = "org_id")
    private long orgId;

}
