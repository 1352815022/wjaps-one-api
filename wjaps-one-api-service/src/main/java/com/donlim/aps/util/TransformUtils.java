package com.donlim.aps.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

/**
 * @ClassName TransformUtils
 * @Description 对象转换工具类
 * @Author p09835
 * @Date 2022/6/1 15:59
 **/
public class TransformUtils {
    // 将对象转换为 Map，只包含对象属性值，不包含方法
    public static Map<String, Object> convertToMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(obj);
            map.put(fieldName, value);
        }
        return map;
    }
    //对象转Map
 /*   public static Map<String, Object> convertToMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }
        return map;
    }*/

    //只要属性值的对像转map
    public static Map<String, Object> convertToMap2(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object o = field.get(obj);

            if (o instanceof String || o instanceof Integer || o instanceof Double || o instanceof Float || o instanceof Long || o instanceof Boolean || o instanceof Short
                    || o instanceof Byte || o instanceof Character || o instanceof BigDecimal || o instanceof Date||o instanceof LocalDate) {
                map.put(field.getName(), field.get(obj));
            }
        }
        return map;
    }

}
