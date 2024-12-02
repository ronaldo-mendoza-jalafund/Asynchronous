package org.example.event_broker;

import org.example.domain.SubscribeEvent;
import org.example.event_processor.IEventProcessor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventPublisherTest {

    @Test
    void happyPathTest() {
        IEventProcessor<SubscribeEvent> subscribeEventProcessor = new IEventProcessor<SubscribeEvent>() {
            @Override
            public void handleEvent(SubscribeEvent event) {
                assertEquals(10, event.getSubscriberId());
            }
        };

        EventPublisher<SubscribeEvent> eventPublisher = new EventPublisher<>();
        eventPublisher.registerTopic("subscribe");
        SubscribeEvent subscribeEvent = new SubscribeEvent("subscribe", 10);
        eventPublisher.subscribe("subscribe", subscribeEventProcessor);
        eventPublisher.publishEvent("subscribe", subscribeEvent);
    }
}