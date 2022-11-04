package com.donlim.aps.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TransformUtils
 * @Description 对象转换工具类
 * @Author p09835
 * @Date 2022/6/1 15:59
 **/
public class TransformUtils {

    public static Map<String,Object> transBean2Map(Object obj){
        return transBean2Map(obj,Object.class);
    }
    /**
     * 实体类转换Map
     * @param obj
     * @return
     */
    public static Map<String,Object> transBean2Map(Object obj,Class stopCls){
        if (obj == null){
            return null;
        }
        Map<String,Object> map = new HashMap<>();

        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass(), stopCls);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();;
            for (PropertyDescriptor property : propertyDescriptors) {
                String name = property.getName();
                if (!"class".equals(name)){
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    map.put(name,value);
                }
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return map;
    }
}
