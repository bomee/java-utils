package net.bomee.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 反射常用操作集合
 *
 * @author bomee shiaupo@qq.com
 */
public class Reflects {

    /**
     * 获取所有的定义字段(含父类定义)
     *
     * @param clazz Class
     * @return Map<String, Field>
     */
    public static Map<String, Field> getAllDeclaredFields(Class<?> clazz) {
        Map<String, Field> fieldMap = new HashMap<>();
        while (!clazz.equals(Object.class)) {
            for (Field declaredField : clazz.getDeclaredFields()) {
                fieldMap.put(declaredField.getName(), declaredField);
            }
            clazz = clazz.getSuperclass();
        }
        return fieldMap;
    }

    /**
     * 获取指定name的Field(含父类定义)
     *
     * @param clazz Class
     * @param name  Field name
     * @return Field if exists else null
     */
    public static Field getDeclaredField(Class<?> clazz, String name) {
        while (!clazz.equals(Object.class)) {
            for (Field declaredField : clazz.getDeclaredFields()) {
                if (declaredField.getName().equals(name)) {
                    return declaredField;
                }
            }
            clazz = clazz.getSuperclass();
        }
        return null;
    }
}
