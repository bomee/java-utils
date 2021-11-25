package net.bomee.event;

/**
 * EventBus
 *
 * @author bomee shiaupo@qq.com
 */
public interface EventBus extends EventPublisher {
    /**
     * Register EventHandler
     *
     * @param handler EventHandler
     */
    void register(EventHandler<? extends Event> handler);

    /**
     * Unregister EventHandler
     *
     * @param handler EventHandler
     * @return {@code true} if the handler exists.
     */
    boolean unregister(EventHandler<? extends Event> handler);
}
