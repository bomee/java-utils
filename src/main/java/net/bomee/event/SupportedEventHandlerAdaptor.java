package net.bomee.event;

import net.bomee.util.Reflects;

/**
 * Event 与 EventHandler 适配器, 从 EventHandler 中解析出泛型参数作为支持的 Event 类型
 *
 * @author bomee shiaupo@qq.com
 */
class SupportedEventHandlerAdaptor<E extends Event> {
    private final EventHandler<E> eventHandler;
    private final Class<?> supportClazz;

    SupportedEventHandlerAdaptor(EventHandler<E> eventHandler) {
        assert eventHandler != null;
        this.eventHandler = eventHandler;
        Class<?>[] genericTypes = Reflects.getGenericTypes(eventHandler.getClass(), EventHandler.class);
        this.supportClazz = genericTypes.length == 1 ? genericTypes[0] : Event.class;
    }

    public EventHandler<E> getEventHandler() {
        return eventHandler;
    }

    public boolean support(Event event) {
        return event.getClass().equals(supportClazz);
    }

    @Override
    public int hashCode() {
        return eventHandler.hashCode();
    }

    @Override
    @SuppressWarnings("rawtypes")
    public boolean equals(Object obj) {
        if (obj instanceof SupportedEventHandlerAdaptor) {
            return eventHandler.equals(((SupportedEventHandlerAdaptor) obj).eventHandler);
        } else {
            return false;
        }
    }
}
