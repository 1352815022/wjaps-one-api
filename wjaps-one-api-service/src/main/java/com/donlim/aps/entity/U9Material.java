package com.donlim.aps.entity;

import com.changhong.sei.core.entity.BaseAuditableEntity;
import com.changhong.sei.core.entity.IFrozen;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 料品表(U9Material)实体类
 *
 * @author sei
 * @since 2022-05-13 11:11:59
 */
@Entity
@Table(name = "u9_material")
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = false)
public class U9Material extends BaseAuditableEntity implements Serializable , IFrozen {
    private static final long serialVersionUID = 215585626559178927L;

    /**
     * 料号
     */
    @Column(name = "code")
    private String code;
    /**
     * 料品名称
     */
    @Column(name = "name")
    private String name;
    /**
     * 规格
     */
    @Column(name = "spec")
    private String spec;
    /**
     * 生产部门
     */
    @Column(name = "dept_code")
    private String deptCode;
    /**
     * 组织id
     */
    @Column(name = "org_id")
    private Long orgId;
    /**
     * 型号
     */
    @Column(name = "product_model")
    private String productModel;
    /**
     * 品名
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * 单位
     */
    @Column(name = "unit")
    private String unit;
    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;
    /**
     * 料品类型(9,采购件，4委外件，10制造件)
     */
    @Column(name = "type")
    private String type;

    /**
     * 损耗
     */
    @Column(name = "scrap")
    private BigDecimal scrap;

    /**
     * 固定提前期
     */
    @Column(name = "fixed_lt")
    private BigDecimal  fixedLt;

    /**
     * 产能（件/天）
     */
    @Column(name = "capacity")
    private BigDecimal  capacity;
    /**
     * 上月期末数量
     */
    @Column(name = "end_qty")
    private BigDecimal endQty;
    /**
     * 粉末型号
     */
    @Column(name = "powder_model")
    private String powderModel;

    /**
     * 喷粉面积
     */
    @Column(name = "powder_area")
    private BigDecimal powderArea;
    /**
     * 清洗面积
     */
    @Column(name = "wash_area")
    private BigDecimal washArea;
    /**
     * 是否冻结
     */
    @Column(name = "frozen")
    private Boolean frozen;

    /**
     * 租户代码
     */
    @Column(name = "tenant_code")
    private String tenantCode;

    @Override
    public Boolean getFrozen() {
        return frozen;
    }

    @Override
    public void setFrozen(Boolean frozen) {
        this.frozen = frozen;
    }



}
