package net.bomee.pattern.chain;

import org.junit.Test;


public class OrderedHandlerChainTest {

    @Test
    public void handle() throws HandleException {
        MapContext context = new MapContext();
        OrderedHandlerChain<MapContext> chain = new OrderedHandlerChain<>(
                new AContextHandler(),
                new BContextHandler(),
                new BizHandler()
        );
        chain.handle(context);
    }
}

class AContextHandler implements Handler<MapContext> {
    @Override
    public void handle(MapContext context) throws HandleException {
        context.put("A", "A");
    }
}

class BContextHandler implements Handler<MapContext> {
    @Override
    public void handle(MapContext context) throws HandleException {
        context.put("B", "B");
    }
}

class BizHandler implements Handler<MapContext> {
    @Override
    public void handle(MapContext context) throws HandleException {
        context.put("C", "C");
    }
}
