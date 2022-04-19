package net.bomee.util;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Set 类常用操作集合
 *
 * @author bomee shiaupo@qq.com
 */
public final class Sets {
    private Sets() {
    }

    /**
     * 根据多个值快速创建一个Set
     *
     * @param elements 值
     * @param <E>      值类型
     * @return Set
     */
    @SafeVarargs
    public static <E> Set<E> of(E... elements) {
        HashSet<E> set = new HashSet<>();
        Collections.addAll(set, elements);
        return set;
    }

    /**
     * 安全的返回Set是否为空
     *
     * @param set Set
     * @param <E> 元素类型
     * @return 当set为null或者size==0时返回true
     */
    public static <E> boolean isEmpty(Set<E> set) {
        return set == null || set.isEmpty();
    }
}
