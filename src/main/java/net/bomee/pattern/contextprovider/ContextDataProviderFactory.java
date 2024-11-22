package net.bomee.pattern.contextprovider;

/**
 * ContextDataProviderFactory
 */
public interface ContextDataProviderFactory {
    /**
     * 根据给定的类型获取相应的数据提供者
     *
     * @param clazz 指定的数据类型
     * @return 返回一个数据类型的ContextDataProvider实例
     */
    <T> ContextDataProvider<T> getProvider(Class<T> clazz);


    /**
     * 注册一个数据提供者，用于提供特定类型的上下文数据
     *
     * @param <T>      泛型参数，表示数据提供者处理的数据类型
     * @param clazz    数据类型的Class对象，用于标识数据类型
     * @param provider 实现了ContextDataProvider接口的数据提供者实例，用于提供上下文数据
     */
    <T> void register(Class<T> clazz, ContextDataProvider<T> provider);
}
