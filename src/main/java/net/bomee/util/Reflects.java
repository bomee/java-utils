package net.bomee.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 反射常用操作集合
 *
 * @author bomee shiaupo@qq.com
 */
public final class Reflects {
    private Reflects() {
    }

    /**
     * 获取所有的定义字段(含父类定义)
     *
     * @param clazz Class
     * @return Map{String: Field}
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

    /**
     * 获取泛型参数的类型
     * <pre>
     * 泛型接口: interface StringList extends List&lt;String&gt;
     *          getGenericTypes(StringList.class, List.class)-> [String.class]
     *          getGenericTypes(StringList.class, Collection.class)-> [String.class]
     *
     * 泛型类: class StringArrayList extends ArrayList&lt;String&gt;
     *          getGenericTypes(StringArrayList.class, List.class)-> [String.class]
     *          getGenericTypes(StringArrayList.class, Collection.class)-> [String.class]
     *          getGenericTypes(StringArrayList.class, ArrayList.class)-> [String.class]
     * </pre>
     *
     * @param clazz        被查找的类
     * @param genericClazz 泛型参数定义的类
     * @return 泛型参数数组, 不为null
     */
    public static Class<?>[] getGenericTypes(Class<?> clazz, Class<?> genericClazz) {
        List<Type> types = new ArrayList<>(Arrays.asList(clazz.getGenericInterfaces()));
        if (clazz.getGenericSuperclass() != null) {
            types.add(clazz.getGenericSuperclass());
        }

        for (Type genericType : types) {
            if (genericType instanceof ParameterizedType) {
                if (genericClazz.isAssignableFrom((Class<?>) ((ParameterizedType) genericType).getRawType())) {
                    Type[] typeArguments = ((ParameterizedType) genericType).getActualTypeArguments();
                    Class<?>[] generics = new Class[typeArguments.length];
                    for (int i = 0; i < typeArguments.length; i++) {
                        generics[i] = (Class<?>) typeArguments[i];
                    }
                    return generics;
                }
            } else {
                Class<?>[] genericTypes = getGenericTypes(((Class<?>) genericType), genericClazz);
                if (genericTypes.length > 0) {
                    return genericTypes;
                }
            }
        }
        return new Class[0];
    }
}
