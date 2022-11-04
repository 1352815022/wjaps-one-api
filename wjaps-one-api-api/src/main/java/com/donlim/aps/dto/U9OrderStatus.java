package com.donlim.aps.dto;

import com.changhong.sei.annotation.Remark;

/**
 * U9状态(0、开立、1、已核准。2、开工。3、完工。4、核准中。)
 * @author p09835
 */
public enum U9OrderStatus {

    /** 1 */
    @Remark("已核准")
    Approved,
    /** 2 */
    @Remark("核准中")
    Approving,
    /** 3 */
    @Remark("完工")
    Completed,
    /** 4 */
    @Remark("开立")
    Create,
    /** 5 */
    @Remark("开工")
    Working,
    ;

    /**
     * U9Status 转换为 可读性更高的字符串
     * @param integer
     * @return
     */
    public static U9OrderStatus transformStatus(Integer integer){
        if (integer ==null){
            return null ;
        }
        switch (integer){
            case 0:
                return Create;
            case 1:
                return Approved;
            case 2:
                return Working;
            case 3:
                return Completed;
            case  4:
                return Approving;
            default:
                return null;

        }
    }


}
