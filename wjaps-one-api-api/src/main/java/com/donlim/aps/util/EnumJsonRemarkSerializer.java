package com.donlim.aps.util;

import com.changhong.sei.util.EnumUtils;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @ClassName EnumJsonRemarkSerializer
 * @Description 枚举类Json生成Remark输出
 * @Author p09835
 * @Date 2022/6/9 16:24
 **/
public class EnumJsonRemarkSerializer extends JsonSerializer<Enum> {
    private final static Logger LOG = LoggerFactory.getLogger(EnumJsonRemarkSerializer.class);

    /**
     * 对添加@Remark的枚举属性，输出含义到json
     * @param value
     * @param gen
     * @param serializers
     * @throws IOException
     */
    @Override
    public void serialize(Enum value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        //自身值
        gen.writeString(value.name());
        //新增字段
        gen.writeFieldName(gen.getOutputContext().getCurrentName()+"Remark");
        String remark = EnumUtils.getEnumItemRemark(value.getClass(),value);
        try {
            Class<?> contextUtilClazz = Class.forName("com.changhong.sei.core.context.ContextUtil");
            Method method = contextUtilClazz.getMethod("getMessage", String.class);
            remark = (String) method.invoke(null, remark);
        } catch (Exception e) {
            LOG.error("枚举多语言序列化异常.", e);
        }
        //新增属性值
        gen.writeString(remark);
    }
}
