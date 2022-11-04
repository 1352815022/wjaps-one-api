package com.donlim.aps.entity;

import com.changhong.sei.core.entity.BaseAuditableEntity;
import com.changhong.sei.core.entity.ITenant;
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
 * scm送货明细(ScmXbDeliveryPlan)实体类
 *
 * @author sei
 * @since 2022-06-20 11:15:02
 */
@Entity
@Table(name = "scm_xb_delivery_plan")
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
public class ScmXbDeliveryPlan extends BaseAuditableEntity implements Serializable, ITenant {
    private static final long serialVersionUID = -16256037151896686L;
    /**
     * 上级id
     */
    @Column(name = "parent_id")
    private String parentId;
    /**
     * 送货数量
     */
    @Column(name = "qty")
    private BigDecimal qty;
    /**
     * 送货日期
     */
    @Column(name = "delivery_date")
    private LocalDate deliveryDate;
    /**
     * 租户代码
     */
    @Column(name = "tenant_code")
    private String tenantCode;

    /**
     * 采购单号
     */
    @Column(name = "purchase_order")
    private String purchaseOrder;

    /**
     * 需求分类号
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * po行id
     */
    @Column(name = "po_line_id")
    private Long poLineId;

    /**
     * 料号
     */
    @Column(name = "material_code")
    private String materialCode;

    @Override
    public String getTenantCode() {
        return tenantCode;
    }
    @Override
    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

}
