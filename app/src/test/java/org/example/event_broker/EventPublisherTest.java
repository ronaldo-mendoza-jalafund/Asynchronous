package org.example.event_broker;

import org.example.domain.SubscribeEvent;
import org.example.event_processor.IEventProcessor;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class EventPublisherTest {

    @Test
    void happyPathTest() {
        IEventProcessor<SubscribeEvent> subscribeEventProcessor = new IEventProcessor<SubscribeEvent>() {
            @Override
            public void handleEvent(SubscribeEvent event) {
                assertNotNull(event.getId());
            }
        };

        EventPublisher<SubscribeEvent> eventPublisher = new EventPublisher<>();
        eventPublisher.registerTopic("subscribe");
        SubscribeEvent subscribeEvent = new SubscribeEvent(UUID.randomUUID(), "subscribe");
        eventPublisher.subscribe("subscribe", subscribeEventProcessor);
        eventPublisher.publishEvent("subscribe", subscribeEvent);
    }
}