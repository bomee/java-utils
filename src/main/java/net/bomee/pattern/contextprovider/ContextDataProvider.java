package net.bomee.pattern.contextprovider;

/**
 * <pre>
 * context-provider，分离数据的提供和访问。常应用于处理业务流程中不同的行为可能依赖相同的数据上下文，但又不希望在每个方法中都声明一个参数来接收上下文数据。
 * 使用 {@link TypedContextDefault} 实现上下文中的数据存储，数据的提供则通过ContextDataProvider。
 * 这可能不是Gof中提到的模式（和repo库模式有些相似），但的确是业务场景中非常实用的一种模式。
 * </pre>
 * ContextDataProvider，特定上下文数据提供
 *
 * @param <T> 类型
 */
public interface ContextDataProvider<T> {
    /**
     * 获取数据
     *
     * @param context TypedContext
     * @return 数据
     */
    T get(TypedContext context);
}
