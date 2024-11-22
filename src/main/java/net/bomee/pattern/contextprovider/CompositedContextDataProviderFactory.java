package net.bomee.pattern.contextprovider;

import java.util.Optional;

/**
 * 组合的ProviderFactory，常用于建立特定的ProviderFactory进行注册，避免进行上层污染。
 */
public class CompositedContextDataProviderFactory implements ContextDataProviderFactory {

    private final TypedMapProviderFactory providerFactory = new TypedMapProviderFactory();
    private final ContextDataProviderFactory delegate;

    public CompositedContextDataProviderFactory(ContextDataProviderFactory delegate) {
        this.delegate = delegate;
    }

    @Override
    public <T> ContextDataProvider<T> getProvider(Class<T> clazz) {
        return Optional.ofNullable(providerFactory.getProvider(clazz)).orElseGet(
                () -> delegate.getProvider(clazz)
        );
    }

    @Override
    public <T> void register(Class<T> clazz, ContextDataProvider<T> provider) {
        providerFactory.register(clazz, provider);
    }
}
