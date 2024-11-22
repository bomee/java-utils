package net.bomee.pattern.contextprovider;

import java.util.HashMap;
import java.util.Map;

/**
 * TypedContext的默认实现，基于Map存储
 */
public class TypedContextDefault implements TypedContext {
    protected final ContextDataProviderFactory providerFactory;
    private final Map<Class<?>, Object> contextHolder = new HashMap<>();

    public TypedContextDefault(ContextDataProviderFactory providerFactory) {
        this.providerFactory = providerFactory;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getData(Class<T> clazz) {
        return (T) contextHolder.computeIfAbsent(clazz, (c) -> providerFactory.getProvider(c).get(TypedContextDefault.this));
    }
}
