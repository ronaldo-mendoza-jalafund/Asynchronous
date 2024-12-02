package org.example.event_broker;

import org.example.event_processor.IEventProcessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventPublisher<T> {
    private final Map<String, List<IEventProcessor<T>>> eventProcessorsMap;

    public EventPublisher() {
        eventProcessorsMap = new HashMap<>();
    }

    public void registerTopic(String topic) {
        if (eventProcessorsMap.containsKey(topic)) {
            throw new RuntimeException(String.format("Topic: %s already registered", topic));
        } else {
            eventProcessorsMap.put(topic, new ArrayList<>());
        }
    }

    public void subscribe(String topic, IEventProcessor<T> eventProcessor) {
        if (!eventProcessorsMap.containsKey(topic)) {
            throw new RuntimeException(String.format("Could not find topic: %s", topic));
        }

        eventProcessorsMap.get(topic).add(eventProcessor);
    }

    public void publishEvent(String topic, T event) {
        if (!eventProcessorsMap.containsKey(topic)) {
            throw new RuntimeException(String.format("Could not find topic: %s", topic));
        }
        eventProcessorsMap.get(topic).forEach(eventProcessor -> eventProcessor.handleEvent(event));
    }
}
