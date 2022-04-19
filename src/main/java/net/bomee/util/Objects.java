package net.bomee.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Collection;
import java.util.Map;

/**
 * Object 常用操作集合
 *
 * @author bomee shiaupo@qq.com
 */
public final class Objects {
    private Objects() {
    }

    /**
     * 判断一个对象是否为空，当出现以下情况中的一种时返回true
     * <ul>
     *     <li>obj==null</li>
     *     <li>obj 为 String 类型且 isEmpty</li>
     *     <li>obj 为 Collection 类型且 size==0</li>
     *     <li>obj 为 Array 类型且 length==0</li>
     * </ul>
     *
     * @param obj 被判断的对象
     * @return 是否为空
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }

        if (obj instanceof String) {
            return ((String) obj).isEmpty();
        }

        if (obj instanceof Collection) {
            return ((Collection<?>) obj).isEmpty();
        }

        if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        }

        return false;
    }

    /**
     * 通过反射的方式复制值对象间的同名字段，在做PlainObject的转换时可简化操作
     *
     * @param src          源
     * @param dest         目标
     * @param ignoreFields 忽略的字段集
     */
    public static void copy(Object src, Object dest, String... ignoreFields) {
        if (src == null || dest == null) {
            return;
        }

        Map<String, Field> srcFieldMap = Reflects.getAllDeclaredFields(src.getClass());
        Map<String, Field> destFieldMap = Reflects.getAllDeclaredFields(dest.getClass());
        for (String ignoreField : ignoreFields) {
            destFieldMap.remove(ignoreField);
        }

        destFieldMap.forEach((name, destField) -> {
            try {
                Field srcField = srcFieldMap.get(destField.getName());
                if (srcField != null
                    && !Modifier.isFinal(destField.getModifiers())
                    && !Modifier.isStatic(destField.getModifiers())) {
                    AccessController.doPrivileged((PrivilegedAction<?>) () -> {
                        destField.setAccessible(true);
                        srcField.setAccessible(true);
                        return null;
                    });
                    destField.set(dest, srcField.get(src));
                }
            } catch (IllegalAccessException ignored) {

            }
        });
    }
}
