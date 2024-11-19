package net.bomee.pattern.chain;

import java.util.HashMap;
import java.util.Map;

/**
 * 基于Map的上下文
 *
 * @author bomee shiaupo@qq.com
 */
public class MapContext implements HandleContext {
    private final Map<String, Object> context = new HashMap<>();

    /**
     * 获取指定key的数据
     *
     * @param key Key
     * @return 数据 or null
     */
    public Object get(String key) {
        return context.get(key);
    }

    /**
     * 设置指定key的数据
     *
     * @param key   Key
     * @param value Value
     */
    public void put(String key, Object value) {
        context.put(key, value);
    }
}
