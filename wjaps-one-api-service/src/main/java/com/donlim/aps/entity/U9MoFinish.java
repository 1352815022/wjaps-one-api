package com.donlim.aps.entity;

import com.changhong.sei.core.entity.BaseAuditableEntity;
import com.changhong.sei.core.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

/**
 * U9完工表实体类
 *
 * @author sei
 * @since 2023-04-08 11:27:28
 */
@Entity
@Table(name = "u9_mo_finish")
@DynamicInsert
@DynamicUpdate
@Data
public class U9MoFinish extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -18921762760490670L;


    /**
     * 工单号
     */
    @Column(name = "order_no")
    private String orderNo;
    /**
     * 已完成数量
     */
    @Column(name = "finish_qty")
    private BigDecimal finishQty;
    /**
     * 完工日期
     */
    @Column(name = "finish_date")
    private LocalDate finishDate;



}
