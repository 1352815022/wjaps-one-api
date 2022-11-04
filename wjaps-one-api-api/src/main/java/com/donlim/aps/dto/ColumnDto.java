package com.donlim.aps.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName ColumnDto
 * @Description TODO
 * @Author p09835
 * @Date 2022/4/26 17:06
 **/
public class ColumnDto {

    /**
     * 字段标题
     */
    @ApiModelProperty(value = "字段标题")
    private String title;
    /**
     * 绑定的后台字段
     */
    @ApiModelProperty(value = "绑定字段")
    private String dataIndex;
    /**
     * 列宽
     */
    @ApiModelProperty(value = "列宽")
    private Integer width;
    /**
     * 是否编辑,通过elem修改 true or false
     */
    @ApiModelProperty(value = "是否编辑")
    private Boolean editFlag = false;
    /**
     * 表单元素
     */
    @ApiModelProperty(value = "表单元素")
    private FormElem elem;

    public enum FormElem{
        /**
         * 文本输入框
         */
        INPUT,
        /**
         * 数值输入框
         */
        INPUT_NUMBER,
        /**
         * 数值输入框 左右箭头
         */
        INPUT_NUMBER_ALL,
        /**
         * 数值输入框 左箭头
         */
        INPUT_NUMBER_LEFT,
        /**
         * 数值输入框 右箭头
         */
        INPUT_NUMBER_RIGHT,
        /**
         * 日期选择框
         */
        DATE_PICK,
        /**
         * 勾选框
         */
        CHECKBOX,
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDataIndex() {
        return dataIndex;
    }

    public void setDataIndex(String dataIndex) {
        this.dataIndex = dataIndex;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Boolean getEditFlag() {
        return editFlag;
    }

    public FormElem getElem() {
        return elem;
    }

    public void setElem(FormElem elem) {
        this.elem = elem;
        this.editFlag = true;
    }
}
