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
    private Maps() {
    }

    /**
     * 安全获取map的类型化值
     * <pre>
     *  Map&lt;Object, Object&gt; map = Maps.ofMap()
     *                 .put("k1", null)
     *                 .put("k2", "v2")
     *                 .put("k3", 0)
     *                 .put("k4", true)
     *                 .put("k5", 1.0)
     *                 .asMap();
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
     * @return HashMap&lt;K, V&gt;
     */
    public static <K, V> Map<K, V> of(K key, V value) {
        HashMap<K, V> map = new HashMap<>();
        map.put(key, value);
        return map;
    }

    /**
     * 快速创建一个HashMapBuilder，可链式写入Map
     * <pre>
     * Map&lt;String, String&gt; map = Maps.&lt;String, String&gt;ofMap()
     *                 .put("k1", "v1")
     *                 .put("k2", "v2")
     *                 .asMap();
     * </pre>
     *
     * @param <K> key类型
     * @param <V> value 类型
     * @return HashMapBuilder&lt;K, V&gt;
     */
    public static <K, V> HashMapBuilder<K, V> ofMap() {
        return new HashMapBuilder<>();
    }

    /**
     * 将Map生成一个HashMapBuilder，可链式写入Map
     * <pre>
     *  Map&lt;String, String&gt; map2 = Maps.ofMap(new HashMap&lt;String, String&gt;())
     *      .put("k1", "v1")
     *      .put("k2", "v2")
     *      .asMap();
     * </pre>
     *
     * @param <K> key类型
     * @param <V> value 类型
     * @return HashMapBuilder&lt;K, V&gt;
     */
    public static <K, V> HashMapBuilder<K, V> ofMap(Map<K, V> map) {
        return new HashMapBuilder<>(map);
    }

    /**
     * HashMap构造器
     *
     * @param <K> key 类型
     * @param <V> value 类型
     */
    public static class HashMapBuilder<K, V> {
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
         * @return HashMapBuilder&lt;K, V&gt;
         */
        public HashMapBuilder<K, V> put(K key, V value) {
            map.put(key, value);
            return this;
        }

        /**
         * remove key
         *
         * @param key key
         * @return HashMapBuilder&lt;K, V&gt;
         */
        public HashMapBuilder<K, V> remove(K key) {
            map.remove(key);
            return this;
        }

        /**
         * clear all key
         *
         * @return HashMapBuilder&lt;K, V&gt;
         */
        public HashMapBuilder<K, V> clear() {
            map.clear();
            return this;
        }

        /**
         * 返回原始HashMap
         *
         * @return Map&lt;K, V&gt;
         */
        public Map<K, V> asMap() {
            return map;
        }

        /**
         * 返回不可修改的HashMap
         *
         * @return Map&lt;K, V&gt;
         */
        public Map<K, V> unmodifiableMap() {
            return Collections.unmodifiableMap(map);
        }
    }
}
