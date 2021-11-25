package net.bomee.event;

import lombok.Getter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MemoryEventBusTest {
    @Test
    public void test_handler_order() {
        TestEventHandler eventHandler = new TestEventHandler();
        TestEvent2Handler event2Handler = new TestEvent2Handler();
        MemoryEventBus.GLOBAL.register(eventHandler);
        MemoryEventBus.GLOBAL.register(event2Handler);
        TestEvent event = new TestEvent();
        MemoryEventBus.GLOBAL.publish(event);
        assertEquals(2, event.getHandled().size());
        assertEquals(event2Handler.toString(), event.getHandled().get(0));
        assertEquals(eventHandler.toString(), event.getHandled().get(1));
    }


    @Getter
    static class TestEvent implements Event {
        private final List<String> handled = new ArrayList<>();

        void handleRecord(String name) {
            handled.add(name);
        }
    }

    static class TestEventAsyncHandler implements EventAsyncHandler<TestEvent> {

        @Override
        public void handle(TestEvent event) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ignored) {

            }
            event.handleRecord(this.toString());
        }
    }

    static class TestEventHandler implements EventHandler<TestEvent> {

        @Override
        public void handle(TestEvent event) {
            event.handleRecord(this.toString());
        }
    }

    static class TestEvent2Handler implements EventHandler<TestEvent>, Ordered {

        @Override
        public void handle(TestEvent event) {
            event.handleRecord(this.toString());
        }

        @Override
        public int getOrder() {
            return Ordered.HIGH_PRIORITY;
        }
    }

}