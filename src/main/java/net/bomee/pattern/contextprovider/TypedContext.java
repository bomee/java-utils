package net.bomee.pattern.contextprovider;

/**
 * 强类型上下文
 */
public interface TypedContext {
    /**
     * 获取对应类型的数据
     *
     * @param clazz 类型
     * @param <T>   范型
     * @return Data
     */
    <T> T getData(Class<T> clazz);
}
