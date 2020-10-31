package net.bomee.event;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SupportedEventHandlerAdaptorImplTest {

    @Test
    public void support() {
        SupportedEventHandlerAdaptor<TestEvent> adaptor1 = new SupportedEventHandlerAdaptor<>(new TestEvent1Handler());
        assertTrue(adaptor1.support(new TestEvent()));

        SupportedEventHandlerAdaptor<TestEvent> adaptor2 = new SupportedEventHandlerAdaptor<>(new TestEvent2Handler());
        assertTrue(adaptor2.support(new TestEvent()));

        SupportedEventHandlerAdaptor<TestEvent> adaptor3 = new SupportedEventHandlerAdaptor<>(new TestEvent3Handler());
        assertTrue(adaptor3.support(new TestEvent()));
    }

    static class TestEvent implements Event {
    }

    interface TestEventHandler extends EventHandler<TestEvent> {
    }

    static class TestEvent1Handler implements EventHandler<TestEvent> {
        @Override
        public void handle(TestEvent event) {

        }
    }

    static class TestEvent2Handler implements EventAsyncHandler<TestEvent> {

        @Override
        public void handle(TestEvent event) {

        }
    }

    static class TestEvent3Handler implements TestEventHandler {
        @Override
        public void handle(TestEvent event) {

        }
    }

}