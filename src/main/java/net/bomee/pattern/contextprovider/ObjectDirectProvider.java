package net.bomee.pattern.contextprovider;

/**
 * 直接对象数据提供，用于将实例对象直接注册到 ContextDataProviderFactory 中。
 *
 * @param <T> 对象的类型
 */
public class ObjectDirectProvider<T> implements ContextDataProvider<T> {
    private final T object;

    public ObjectDirectProvider(T object) {
        this.object = object;
    }

    @Override
    public T get(TypedContext context) {
        return object;
    }
}
