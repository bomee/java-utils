package net.bomee.event;

import net.bomee.pattern.sorted.Ordered;
import net.bomee.util.Threads;

import java.util.LinkedList;
import java.util.List;

/**
 * 基于内存的EventBus, 使用 MemoryEventBus.GLOBAL 全局实例快速实现进程内的事件模型(Pub-Sub模型)
 *
 * @author bomee shiaupo@qq.com
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class MemoryEventBus implements EventBus {
    /**
     * GLOBAL EventBus
     */
    public static final EventBus GLOBAL = new MemoryEventBus();

    private final List<SupportedEventHandlerAdaptor<? super Event>> registerHandlers = new LinkedList<>();

    /**
     * register.
     *
     * @param handler EventHandler
     */
    @Override
    public void register(EventHandler<? extends Event> handler) {
        if (handler == null) {
            throw new IllegalArgumentException("handler must not be null.");
        }
        if (exists(handler)) {
            return;
        }
        registerHandlers.add(new SupportedEventHandlerAdaptor(handler));
        registerHandlers.sort((adaptorA, adaptorB) -> {
            EventHandler<? super Event> a = adaptorA.getEventHandler();
            EventHandler<? super Event> b = adaptorB.getEventHandler();
            return (a instanceof Ordered ? ((Ordered) a).getOrder() : Ordered.NORMAL_PRIORITY)
                    - (b instanceof Ordered ? ((Ordered) b).getOrder() : Ordered.NORMAL_PRIORITY);
        });
    }

    /**
     * unregister.
     *
     * @param handler EventHandler
     * @return true if any handler were removed
     */
    @Override
    public boolean unregister(EventHandler<? extends Event> handler) {
        if (handler == null) {
            throw new IllegalArgumentException("handler must not be null.");
        }
        return registerHandlers.removeIf(handlerAdaptor -> handlerAdaptor.getEventHandler().equals(handler));
    }

    private boolean exists(EventHandler<? extends Event> handler) {
        return registerHandlers.stream().anyMatch(handlerAdaptor -> handlerAdaptor.getEventHandler().equals(handler));
    }

    /**
     * publish.
     *
     * @param event Event
     */
    @Override
    public void publish(Event event) {
        registerHandlers.stream()
                .filter(adaptor -> adaptor.support(event))
                .map(SupportedEventHandlerAdaptor::getEventHandler)
                .forEach((handler -> {
                    try {
                        if (handler instanceof EventAsyncHandler) {
                            Threads.runAsync(() -> handler.handle(event));
                        } else {
                            handler.handle(event);
                        }
                    } catch (Exception ignored) {
                        // TODO: 目前出了异常暂未处理, 是否需要重试?
                    }
                }));
    }
}
