package com.donlim.aps.entity;

import com.changhong.sei.core.dto.TreeEntity;
import com.changhong.sei.core.entity.BaseAuditableEntity;
import com.changhong.sei.core.entity.IFrozen;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 组织机构(ApsOrganize)实体类
 *
 * @author sei
 * @since 2022-04-25 11:27:41
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = "aps_organize")
@DynamicInsert
@DynamicUpdate
public class ApsOrganize extends BaseAuditableEntity implements Serializable ,IFrozen , TreeEntity<ApsOrganize> {
    private static final long serialVersionUID = 987693832066725744L;
    /**
     * 代码
     */
    @Column(name = "code")
    private String code;
    /**
     * 名称
     */
    @Column(name = "name")
    private String name;

    @Column(name = "parent_id")
    private String parentId;

    @Column(name = "rank")
    private Integer rank;
    /**
     * 层级
     */
    @Column(name = "node_level")
    private Integer nodeLevel;
    /**
     * 代码路径
     */
    @Column(name = "code_path")
    private String codePath;
    /**
     * 名称路径
     */
    @Column(name = "name_path")
    private String namePath;

    @Column(name = "en_code")
    private String enCode;

    @Column(name = "parent_en_code")
    private String parentEnCode;

    @Column(name = "erp_id")
    private Long erpId;

    @Column(name = "erp_code")
    private String erpCode;

    @Column(name = "category")
    private String category;

    @Column(name = "manager_id")
    private String managerId;

    @Column(name = "description")
    private String description;
    /**
     * 总人数
     */
    @Column(name = "peoples")
    private Integer peoples;
    /**
     * 拉线数量
     */
    @Column(name = "line_count")
    private Integer lineCount;

    @Column(name = "line_type_id")
    private String lineTypeId;
    /**
     * 租户代码
     */
    @Column(name = "tenant_code")
    private String tenantCode;

    /**
     * 冻结
     */
    @Column(name = "frozen")
    private Boolean frozen;

    /**
     * 子节点列表
     */
    @Transient
    private List<ApsOrganize> children;

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getEnCode() {
        return enCode;
    }

    public void setEnCode(String enCode) {
        this.enCode = enCode;
    }

    public String getParentEnCode() {
        return parentEnCode;
    }

    public void setParentEnCode(String parentEnCode) {
        this.parentEnCode = parentEnCode;
    }

    public Long getErpId() {
        return erpId;
    }

    public void setErpId(Long erpId) {
        this.erpId = erpId;
    }

    public String getErpCode() {
        return erpCode;
    }

    public void setErpCode(String erpCode) {
        this.erpCode = erpCode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPeoples() {
        return peoples;
    }

    public void setPeoples(Integer peoples) {
        this.peoples = peoples;
    }

    public Integer getLineCount() {
        return lineCount;
    }

    public void setLineCount(Integer lineCount) {
        this.lineCount = lineCount;
    }

    public String getLineTypeId() {
        return lineTypeId;
    }

    public void setLineTypeId(String lineTypeId) {
        this.lineTypeId = lineTypeId;
    }



    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getParentId() {
        return parentId;
    }

    @Override
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public Integer getRank() {
        return rank;
    }

    @Override
    public Integer getNodeLevel() {
        return nodeLevel;
    }

    @Override
    public void setNodeLevel(Integer nodeLevel) {
        this.nodeLevel = nodeLevel;
    }

    @Override
    public String getCodePath() {
        return codePath;
    }

    @Override
    public void setCodePath(String codePath) {
        this.codePath = codePath;
    }

    @Override
    public String getNamePath() {
        return namePath;
    }

    @Override
    public void setNamePath(String namePath) {
        this.namePath = namePath;
    }

    @Override
    public List<ApsOrganize> getChildren() {
        return children;
    }

    @Override
    public void setChildren(List<ApsOrganize> children) {
        this.children = children;
    }

    @Override
    public Boolean getFrozen() {
        return frozen;
    }

    @Override
    public void setFrozen(Boolean frozen) {
        this.frozen = frozen;
    }
}
