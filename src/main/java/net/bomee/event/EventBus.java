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
}
