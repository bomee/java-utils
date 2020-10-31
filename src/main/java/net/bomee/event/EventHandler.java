package net.bomee.event;

/**
 * EventHandler
 *
 * @author bomee shiaupo@qq.com
 */
public interface EventHandler<E extends Event> {

    /**
     * Handle Event
     *
     * @param event Event
     */
    void handle(E event);
}
