package com.donlim.aps.entity.cust;

import com.donlim.aps.entity.ScmXbDelivery;
import com.donlim.aps.entity.U9Material;

import java.io.Serializable;

/**
 * @ClassName ScmOrderAndMaterialCust
 * @Description 送货单与物料联合查询实体类
 * @Author p09835
 * @Date 2022/5/20 16:33
 **/
public class ScmOrderAndMaterialCust implements Serializable {

    private static final long serialVersionUID = 4808303028504229774L;

    private ScmXbDelivery scmXbDelivery;

    private U9Material u9Material;

    public ScmOrderAndMaterialCust(ScmXbDelivery scmXbDelivery, U9Material u9Material) {
        this.scmXbDelivery = scmXbDelivery;
        this.u9Material = u9Material;
    }

    public ScmXbDelivery getScmXbDelivery() {
        return scmXbDelivery;
    }

    public void setScmXbDelivery(ScmXbDelivery scmXbDelivery) {
        this.scmXbDelivery = scmXbDelivery;
    }

    public U9Material getU9Material() {
        return u9Material;
    }

    public void setU9Material(U9Material u9Material) {
        this.u9Material = u9Material;
    }
}
