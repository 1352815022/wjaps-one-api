package com.donlim.aps.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import com.changhong.sei.core.dto.TreeEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 组织机构(ApsOrganize)DTO类
 *
 * @author sei
 * @since 2022-04-25 11:27:43
 */
@ApiModel(description = "组织机构DTO")
public class ApsOrganizeDto extends BaseEntityDto implements TreeEntity<ApsOrganizeDto> {
    private static final long serialVersionUID = 837296511796356622L;
    /**
     * 代码
     */
    @ApiModelProperty(value = "代码")
    private String code;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 父节点Id
     */
    @ApiModelProperty(value = "父节点Id")
    private String parentId;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer rank;
    /**
     * 层级
     */
    @ApiModelProperty(value = "层级")
    private Integer nodeLevel;
    /**
     * 代码路径
     */
    @ApiModelProperty(value = "代码路径")
    private String codePath;
    /**
     * 名称路径
     */
    @ApiModelProperty(value = "名称路径")
    private String namePath;


    private String enCode;


    private String parentEnCode;


    private Long erpId;


    private String erpCode;


    private String category;


    private String managerId;


    private String description;
    /**
     * 总人数
     */
    @ApiModelProperty(value = "总人数")
    private Integer peoples;
    /**
     * 拉线数量
     */
    @ApiModelProperty(value = "拉线数量")
    private Integer lineCount;


    private String lineTypeId;
    /**
     * 租户代码
     */
    @ApiModelProperty(value = "租户代码")
    private String tenantCode;

    /**
     * 是否冻结
     *
     */
    @ApiModelProperty(value = "是否冻结")
    private Boolean frozen = Boolean.FALSE;

    /**
     * 子节点列表
     */
    @ApiModelProperty(value = "子节点列表")
    private List<ApsOrganizeDto> children;

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setRank(Integer rank) {
        this.rank = rank;
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

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

    public Boolean getFrozen() {
        return frozen;
    }

    public void setFrozen(Boolean frozen) {
        this.frozen = frozen;
    }

    @Override
    public List<ApsOrganizeDto> getChildren() {
        return children;
    }

    @Override
    public void setChildren(List<ApsOrganizeDto> children) {
        this.children = children;
    }
}
