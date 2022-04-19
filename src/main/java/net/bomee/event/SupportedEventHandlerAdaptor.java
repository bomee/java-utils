package net.bomee.event;

import net.bomee.util.Reflects;

/**
 * Event 与 EventHandler 适配器, 从 EventHandler 中解析出泛型参数作为支持的 Event 类型.
 *
 * @param <E> Event
 * @author bomee shiaupo@qq.com
 */
class SupportedEventHandlerAdaptor<E extends Event> {
    /**
     * delegate event handler.
     */
    private final EventHandler<E> delegate;

    /**
     * support class.
     */
    private final Class<?> supportClazz;

    SupportedEventHandlerAdaptor(EventHandler<E> eventHandler) {
        assert eventHandler != null;
        this.delegate = eventHandler;
        Class<?>[] genericTypes = Reflects.getGenericTypes(eventHandler.getClass(), EventHandler.class);
        this.supportClazz = genericTypes.length == 1 ? genericTypes[0] : Event.class;
    }

    public EventHandler<E> getEventHandler() {
        return delegate;
    }

    public boolean support(Event event) {
        return event.getClass().equals(supportClazz);
    }

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }

    @Override
    @SuppressWarnings("rawtypes")
    public boolean equals(Object obj) {
        if (obj instanceof SupportedEventHandlerAdaptor) {
            return delegate.equals(((SupportedEventHandlerAdaptor) obj).delegate);
        } else {
            return false;
        }
    }
}
