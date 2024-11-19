package net.bomee.pattern.chain;

import net.bomee.pattern.sorted.Ordered;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 有序的 Handler Chain
 *
 * @param <T>
 * @author bomee shiaupo@qq.com
 */
public class OrderedHandlerChain<T extends HandleContext> implements Handler<T> {
    private final List<Handler<T>> handlers;

    public OrderedHandlerChain(Handler<T>... handlers) {
        this(Arrays.asList(handlers));
    }

    public OrderedHandlerChain(List<Handler<T>> handlers) {
        assert handlers != null;

        this.handlers = new ArrayList<>(handlers);
        this.handlers.sort((h1, h2) -> {
            int order1 = h1 instanceof Ordered ? ((Ordered) h1).getOrder() : Ordered.NORMAL_PRIORITY;
            int order2 = h2 instanceof Ordered ? ((Ordered) h2).getOrder() : Ordered.NORMAL_PRIORITY;
            return order1 - order2;
        });
    }

    @Override
    public void handle(T context) throws HandleException {
        for (Handler<T> handler : handlers) {
            handler.handle(context);
        }
    }
}
