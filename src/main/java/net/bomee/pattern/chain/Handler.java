package net.bomee.pattern.chain;

/**
 * 处理器接口
 *
 * @param <T> 上下文类型
 * @author bomee shiaupo@qq.com
 */
public interface Handler<T extends HandleContext> {
    /**
     * 执行处理
     *
     * @param context 上下文
     * @throws HandleException 处理异常
     */
    void handle(T context) throws HandleException;
}
