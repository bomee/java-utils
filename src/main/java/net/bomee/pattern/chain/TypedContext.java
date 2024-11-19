package net.bomee.pattern.chain;

import java.util.HashMap;
import java.util.Map;

/**
 * 强类型的上下文，推荐使用
 *
 * @author bomee shiaupo@qq.com
 */
@SuppressWarnings("unchecked")
public class TypedContext implements HandleContext {
    private final Map<Class<?>, Object> context = new HashMap<>();

    /**
     * 获取指定类型的数据
     *
     * @param clazz Class
     * @param <T>   类型
     * @return 对用类型的数据，可能为null
     */
    public <T> T get(Class<T> clazz) {
        return (T) context.get(clazz);
    }

    /**
     * 设置指定类型的数据，null值无任何效果
     *
     * @param value 数据
     * @param <T>   类型
     */
    public <T> void put(T value) {
        if (value == null) return;
        context.put(value.getClass(), value);
    }

    /**
     * 移除指定类型的数据
     *
     * @param clazz Class
     */
    public void remove(Class<?> clazz) {
        context.remove(clazz);
    }
}
