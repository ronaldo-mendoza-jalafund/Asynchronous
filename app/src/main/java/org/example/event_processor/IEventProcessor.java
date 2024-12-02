package org.example.event_processor;

public interface IEventProcessor<T> {
    void handleEvent(T event);
}
