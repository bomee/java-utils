package net.bomee.event;

/**
 * EventPublisher
 *
 * @author bomee shiaupo@qq.com
 */
public interface EventPublisher {

    /**
     * Publish Event
     *
     * @param event Event
     */
    void publish(Event event);
}
