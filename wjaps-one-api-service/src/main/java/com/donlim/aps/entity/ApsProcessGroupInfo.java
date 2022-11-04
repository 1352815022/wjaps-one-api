package com.donlim.aps.entity;

import com.changhong.sei.core.entity.BaseAuditableEntity;
import com.changhong.sei.core.entity.IFrozen;
import com.changhong.sei.core.entity.ITenant;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 工艺工序关连表(ApsProcessGroupInfo)实体类
 *
 * @author sei
 * @since 2022-04-20 16:11:43
 */
@Entity
@Table(name = "aps_process_group_info")
@Access(AccessType.FIELD)
@DynamicInsert
@DynamicUpdate
public class ApsProcessGroupInfo extends BaseAuditableEntity implements Serializable , ITenant  , IFrozen {
    private static final long serialVersionUID = 374810348197846841L;

    /**
     * 关联ApsProcess
     */
    @ManyToOne
    @JoinColumn(name = "process_id",insertable = false,updatable = false)
    private ApsProcess apsProcess;

    /**
     * 关联ApsProcessGroup
     */
    @ManyToOne
    @JoinColumn(name = "process_group_id",insertable = false,updatable = false)
    private ApsProcessGroup apsProcessGroup;
    /**
     * 是否排产
     */
    @Column(name = "scheduling_flag")
    private Boolean schedulingFlag;
    /**
     * 自动后工序关联
     */
    @Column(name = "post_process_id")
    private String postProcessId;
    /**
     * 是否采集
     */
    @Column(name = "acquisition_flag")
    private Boolean acquisitionFlag;
    /**
     * 采集次数
     */
    @Column(name = "acquisition_times")
    private Integer acquisitionTimes;
    /**
     * 是否报产
     */
    @Column(name = "production_flag")
    private Boolean productionFlag;
    /**
     * 记录报产人
     */
    @Column(name = "person_flag")
    private Boolean personFlag;
    /**
     * 优先级
     */
    @Column(name = "process_priority")
    private Integer processPriority;
    /**
     * 报产来源
     */
    @Column(name = "production_process_id")
    private String productionProcessId;
    /**
     * 组织id
     */
    @Column(name = "organize_id")
    private String organizeId;
    /**
     * 加工次数
     */
    @Column(name = "processing_times")
    private Integer processingTimes;
    /**
     * 状态
     */
    @Column(name = "status")
    private Integer status;
    /**
     * 备注1
     */
    @Column(name = "remark")
    private String remark;
    /**
     * 报产方式
     */
    @Column(name = "output_type")
    private String outputType;
    /**
     * 提前期
     */
    @Column(name = "fix_day")
    private Integer fixDay;
    /**
     * 后段约束
     */
    @Column(name = "post_constraint")
    private String postConstraint;
    /**
     * 额外报产
     */
    @Column(name = "extra_yield")
    private Boolean extraYield;
    /**
     * 适用组织
     */
    @Column(name = "company_id")
    private String companyId;
    /**
     * 适用部门
     */
    @Column(name = "dept_id")
    private String deptId;
    /**
     * 启用状态
     */
    @Column(name = "frozen")
    private Boolean frozen = Boolean.FALSE;
    /**
     * 租户代码
     */
    @Column(name = "tenant_code")
    private String tenantCode;

    public ApsProcess getApsProcess() {
        return apsProcess;
    }

    public void setApsProcess(ApsProcess apsProcess) {
        this.apsProcess = apsProcess;
    }

    public ApsProcessGroup getApsProcessGroup() {
        return apsProcessGroup;
    }

    public void setApsProcessGroup(ApsProcessGroup apsProcessGroup) {
        this.apsProcessGroup = apsProcessGroup;
    }


    public Boolean getSchedulingFlag() {
        return schedulingFlag;
    }

    public void setSchedulingFlag(Boolean schedulingFlag) {
        this.schedulingFlag = schedulingFlag;
    }

    public String getPostProcessId() {
        return postProcessId;
    }

    public void setPostProcessId(String postProcessId) {
        this.postProcessId = postProcessId;
    }

    public Boolean getAcquisitionFlag() {
        return acquisitionFlag;
    }

    public void setAcquisitionFlag(Boolean acquisitionFlag) {
        this.acquisitionFlag = acquisitionFlag;
    }

    public Integer getAcquisitionTimes() {
        return acquisitionTimes;
    }

    public void setAcquisitionTimes(Integer acquisitionTimes) {
        this.acquisitionTimes = acquisitionTimes;
    }

    public Boolean getProductionFlag() {
        return productionFlag;
    }

    public void setProductionFlag(Boolean productionFlag) {
        this.productionFlag = productionFlag;
    }

    public Boolean getPersonFlag() {
        return personFlag;
    }

    public void setPersonFlag(Boolean personFlag) {
        this.personFlag = personFlag;
    }

    public Integer getProcessPriority() {
        return processPriority;
    }

    public void setProcessPriority(Integer processPriority) {
        this.processPriority = processPriority;
    }

    public String getProductionProcessId() {
        return productionProcessId;
    }

    public void setProductionProcessId(String productionProcessId) {
        this.productionProcessId = productionProcessId;
    }

    public String getOrganizeId() {
        return organizeId;
    }

    public void setOrganizeId(String organizeId) {
        this.organizeId = organizeId;
    }

    public Integer getProcessingTimes() {
        return processingTimes;
    }

    public void setProcessingTimes(Integer processingTimes) {
        this.processingTimes = processingTimes;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOutputType() {
        return outputType;
    }

    public void setOutputType(String outputType) {
        this.outputType = outputType;
    }

    public Integer getFixDay() {
        return fixDay;
    }

    public void setFixDay(Integer fixDay) {
        this.fixDay = fixDay;
    }

    public String getPostConstraint() {
        return postConstraint;
    }

    public void setPostConstraint(String postConstraint) {
        this.postConstraint = postConstraint;
    }

    public Boolean getExtraYield() {
        return extraYield;
    }

    public void setExtraYield(Boolean extraYield) {
        this.extraYield = extraYield;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    @Override
    public Boolean getFrozen() {
        return frozen;
    }

    @Override
    public void setFrozen(Boolean frozen) {
        this.frozen = frozen;
    }

    @Override
    public String getTenantCode() {
        return tenantCode;
    }
    @Override
    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

}
