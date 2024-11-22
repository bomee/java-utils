package net.bomee.pattern.contextprovider;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TypedContextTest {

    @Test
    public void test() {
        // 全局共有的provider
        TypedMapProviderFactory globalProviderFactory = new TypedMapProviderFactory();
        globalProviderFactory.register(ContextA.class, context -> new ContextA("contextA"));
        globalProviderFactory.register(ContextB.class, context -> new ContextB("contextB"));

        // 差异化的provider
        CompositedContextDataProviderFactory context1ProviderFactory = new CompositedContextDataProviderFactory(globalProviderFactory);
        context1ProviderFactory.register(ContextC.class, new ObjectDirectProvider<>(new ContextC("context1-C")));
        TypedContext context1 = new TypedContextDefault(
                context1ProviderFactory
        );
        assertEquals("contextA", context1.getData(ContextA.class).getName());
        assertEquals("contextB", context1.getData(ContextB.class).getName());
        assertEquals("context1-C", context1.getData(ContextC.class).getName());

        // 差异化的provider
        CompositedContextDataProviderFactory context2ProviderFactory = new CompositedContextDataProviderFactory(globalProviderFactory);
        context2ProviderFactory.register(ContextC.class, context -> {
            ContextA contextA = context.getData(ContextA.class);
            ContextB contextB = context.getData(ContextB.class);
            return new ContextC(contextA.getName() + "-" + contextB.getName());
        });
        TypedContext context2 = new TypedContextDefault(
                context2ProviderFactory
        );
        assertEquals("contextA", context2.getData(ContextA.class).getName());
        assertEquals("contextB", context2.getData(ContextB.class).getName());
        assertEquals("contextA-contextB", context2.getData(ContextC.class).getName());
    }

    @Getter
    @AllArgsConstructor
    public static class ContextA {
        private String name;
    }

    @Getter
    @AllArgsConstructor
    public static class ContextB {
        private String name;
    }

    @Getter
    @AllArgsConstructor
    public static class ContextC {
        private String name;
    }
}
