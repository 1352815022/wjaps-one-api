package com.donlim.aps.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.changhong.sei.core.entity.BaseAuditableEntity;
import com.changhong.sei.core.entity.ITenant;
import com.donlim.aps.util.converter.LocalDateConverter;
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
 * 生产工序报工表
 * (ApsProductionProcessSchedule)实体类
 *
 * @author sei
 * @since 2022-06-20 11:34:56
 */
@Entity
@Table(name = "aps_production_process_schedule")
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@ExcelIgnoreUnannotated
public class ApsProductionProcessSchedule extends BaseAuditableEntity implements Serializable , ITenant {
    private static final long serialVersionUID = 791477301829745838L;
    /**
     * 生产单号
     */
    @Column(name = "product_order")
    @ExcelProperty("工单号")
    private String productOrder;
    /**
     * 料品编码
     */
    @Column(name = "material_code")
    @ExcelProperty("料号")
    private String materialCode;
    /**
     * 品名
     */
    @Column(name = "material_name")
    @ExcelProperty("料名")
    private String materialName;
    /**
     * 料品规格
     */
    @Column(name = "material_spec")
    @ExcelProperty("规格")
    private String materialSpec;

    @Column(name = "production_date")
    @ExcelProperty(value = "报工日期",converter = LocalDateConverter.class)
    private LocalDate productionDate;
    /**
     * 第一道工序报工数
     */
    @Column(name = "process1")
    @ExcelProperty("工序1")
    private BigDecimal process1;

    @Column(name = "process2")
    @ExcelProperty("工序2")
    private BigDecimal process2;

    @Column(name = "process3")
    @ExcelProperty("工序3")
    private BigDecimal process3;

    @Column(name = "process4")
    @ExcelProperty("工序4")
    private BigDecimal process4;

    @Column(name = "process5")
    @ExcelProperty("工序5")
    private BigDecimal process5;

    @Column(name = "process6")
    @ExcelProperty("工序6")
    private BigDecimal process6;

    @Column(name = "process7")
    @ExcelProperty("工序7")
    private BigDecimal process7;

    @Column(name = "process8")
    @ExcelProperty("工序8")
    private BigDecimal process8;

    @Column(name = "process9")
    @ExcelProperty("工序9")
    private BigDecimal process9;

    @Column(name = "process10")
    @ExcelProperty("工序10")
    private BigDecimal process10;

    @Column(name = "process11")
    @ExcelProperty("工序11")
    private BigDecimal process11;

    @Column(name = "process12")
    @ExcelProperty("工序12")
    private BigDecimal process12;

    @Column(name = "process13")
    @ExcelProperty("工序13")
    private BigDecimal process13;

    @Column(name = "process14")
    @ExcelProperty("工序14")
    private BigDecimal process14;

    @Column(name = "process15")
    @ExcelProperty("工序15")
    private BigDecimal process15;

    @Column(name = "process16")
    @ExcelProperty("工序6")
    private BigDecimal process16;

    @Column(name = "process17")
    @ExcelProperty("工序17")
    private BigDecimal process17;

    @Column(name = "process18")
    @ExcelProperty("工序18")
    private BigDecimal process18;

    @Column(name = "process19")
    @ExcelProperty("工序19")
    private BigDecimal process19;

    @Column(name = "process20")
    @ExcelProperty("工序20")
    private BigDecimal process20;

    @Column(name = "process21")
    @ExcelProperty("工序21")
    private BigDecimal process21;

    @Column(name = "process22")
    @ExcelProperty("工序22")
    private BigDecimal process22;

    @Column(name = "process23")
    @ExcelProperty("工序23")
    private BigDecimal process23;

    @Column(name = "process24")
    @ExcelProperty("工序24")
    private BigDecimal process24;

    @Column(name = "process25")
    @ExcelProperty("工序25")
    private BigDecimal process25;

    @Column(name = "process26")
    @ExcelProperty("工序26")
    private BigDecimal process26;

    @Column(name = "process27")
    @ExcelProperty("工序27")
    private BigDecimal process27;

    @Column(name = "process28")
    @ExcelProperty("工序28")
    private BigDecimal process28;

    @Column(name = "process29")
    @ExcelProperty("工序29")
    private BigDecimal process29;

    @Column(name = "process30")
    @ExcelProperty("工序30")
    private BigDecimal process30;

    @Column(name = "process_last")
    @ExcelIgnore
    private BigDecimal processLast;
    /**
     * 租户代码
     */
    @Column(name = "tenant_code")
    @ExcelIgnore
    private String tenantCode;

    @Override
    public String getTenantCode() {
        return tenantCode;
    }

    @Override
    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }
}
