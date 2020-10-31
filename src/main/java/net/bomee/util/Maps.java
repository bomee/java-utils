package net.bomee.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Map 类相关操作集合
 *
 * @author bomee shiaupo@qq.com
 */
public final class Maps {

    /**
     * 安全获取map的类型化值
     * <pre>
     *  Map<Object, Object> map = Maps.ofBuilder()
     *                 .put("k1", null)
     *                 .put("k2", "v2")
     *                 .put("k3", 0)
     *                 .put("k4", true)
     *                 .put("k5", 1.0)
     *                 .map();
     *
     *  Maps.get(map, "k1", String.class);
     *  Maps.get(map, "k2", String.class);
     *  Maps.get(map, "k3", Integer.class);
     *  Maps.get(map, "k4", Boolean.class);
     *  Maps.get(map, "k5", Double.class);
     * </pre>
     *
     * @param map       Map
     * @param key       key
     * @param valueType value的类型
     * @param <K>       key类型
     * @param <V>       value类型
     * @param <T>       value返回的类型
     * @return 如果key不存在则返回null， 否则转换成对应类型的值
     * @throws ClassCastException 当值不为null且值不是valueType的类型则抛出异常.
     */
    public static <K, V, T> T get(Map<K, V> map, K key, Class<T> valueType) {
        if (map == null) {
            return null;
        }

        V v = map.get(key);
        if (v == null) {
            return null;
        }
        return valueType.cast(v);
    }

    /**
     * 快速创建一个含有key，value的HashMap
     *
     * @param key   key
     * @param value value
     * @param <K>   key 类型
     * @param <V>   value 类型
     * @return HashMap<K, V>
     */
    public static <K, V> Map<K, V> of(K key, V value) {
        HashMap<K, V> map = new HashMap<>();
        map.put(key, value);
        return map;
    }

    /**
     * 快速创建一个HashMapBuilder，可链式写入Map
     * <pre>
     * Map<String, String> map = Maps.<String, String>ofBuilder()
     *                 .put("k1", "v1")
     *                 .put("k2", "v2")
     *                 .map();
     * </pre>
     *
     * @param <K> key类型
     * @param <V> value 类型
     * @return HashMapBuilder<K, V>
     */
    public static <K, V> HashMapBuilder<K, V> ofBuilder() {
        return new HashMapBuilder<>();
    }

    /**
     * 将Map生成一个HashMapBuilder，可链式写入Map
     * <pre>
     *  Map<String, String> map2 = Maps.ofBuilder(new HashMap<String, String>())
     *      .put("k1", "v1")
     *      .put("k2", "v2")
     *      .map();
     * </pre>
     *
     * @param <K> key类型
     * @param <V> value 类型
     * @return HashMapBuilder<K, V>
     */
    public static <K, V> HashMapBuilder<K, V> ofBuilder(Map<K, V> map) {
        return new HashMapBuilder<>(map);
    }

    /**
     * HashMap构造器
     *
     * @param <K> key 类型
     * @param <V> value 类型
     */
    static class HashMapBuilder<K, V> {
        private final Map<K, V> map;

        HashMapBuilder(Map<K, V> map) {
            this.map = map == null ? new HashMap<>() : map;
        }

        HashMapBuilder() {
            this.map = new HashMap<>();
        }

        /**
         * put key value
         *
         * @param key   key
         * @param value value
         * @return HashMapBuilder<K, V>
         */
        public HashMapBuilder<K, V> put(K key, V value) {
            map.put(key, value);
            return this;
        }

        /**
         * remove key
         *
         * @param key key
         * @return HashMapBuilder<K, V>
         */
        public HashMapBuilder<K, V> remove(K key) {
            map.remove(key);
            return this;
        }

        /**
         * clear all key
         *
         * @return HashMapBuilder<K, V>
         */
        public HashMapBuilder<K, V> clear() {
            map.clear();
            return this;
        }

        /**
         * 返回原始HashMap
         *
         * @return Map<K, V>
         */
        public Map<K, V> map() {
            return map;
        }

        /**
         * 返回不可修改的HashMap
         *
         * @return Map<K, V>
         */
        public Map<K, V> unmodifiableMap() {
            return Collections.unmodifiableMap(map);
        }
    }
}
