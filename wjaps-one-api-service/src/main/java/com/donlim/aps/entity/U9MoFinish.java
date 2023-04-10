package com.donlim.aps.entity;

import com.changhong.sei.core.entity.BaseAuditableEntity;
import com.changhong.sei.core.entity.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * U9数据扩展字段(U9MoFinish)实体类
 *
 * @author sei
 * @since 2023-04-08 11:27:28
 */
@Entity
@Table(name = "u9_mo_finish")
@DynamicInsert
@DynamicUpdate
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
    private Double finishQty;
    /**
     * 完工日期
     */
    @Column(name = "finish_date")
    private Date finishDate;


    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Double getFinishQty() {
        return finishQty;
    }

    public void setFinishQty(Double finishQty) {
        this.finishQty = finishQty;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

}