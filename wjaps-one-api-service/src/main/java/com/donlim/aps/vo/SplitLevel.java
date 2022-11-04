package com.donlim.aps.vo;

/**
 * @ClassName SplitLevel
 * @Description 拆分层级
 * @Author p09835
 * @Date 2022/5/18 13:44
 **/
public class SplitLevel {

    private Long materialId;

    private Integer level;

    public SplitLevel(Long materialId, Integer level) {
        this.materialId = materialId;
        this.level = level;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
