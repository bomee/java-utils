package net.bomee.pattern.contextprovider;

import java.util.HashMap;
import java.util.Map;

/**
 * 基于Map的ContextDataProvider工厂
 */
public class TypedMapProviderFactory implements ContextDataProviderFactory {

    private final Map<Class<?>, ContextDataProvider<?>> providerMap = new HashMap<>();

    @SuppressWarnings("unchecked")
    @Override
    public <T> ContextDataProvider<T> getProvider(Class<T> clazz) {
        return (ContextDataProvider<T>) providerMap.get(clazz);
    }

    @Override
    public <T> void register(Class<T> clazz, ContextDataProvider<T> provider) {
        providerMap.put(clazz, provider);
    }
}
