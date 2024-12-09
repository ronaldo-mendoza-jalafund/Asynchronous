package org.example.broker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.processor.IEventProcessor;

public class EventPublisher {
  private final HashMap<String, List<IEventProcessor>> eventProcessors;

  public EventPublisher() {
      this.eventProcessors = new HashMap<>();
  }

  public void registerTopic(String topic) {
      eventProcessors.putIfAbsent(topic, new ArrayList<>());
  }

  public void publishEvent(String topic, Object event) {
      List<IEventProcessor> processors = eventProcessors.get(topic);
      if (processors != null) {
          for (IEventProcessor processor : processors) {
              processor.handleEvent(event);
          }
      }
  }

  public void subscribe(String topic, IEventProcessor eventProcessor) {
      registerTopic(topic);
      eventProcessors.get(topic).add(eventProcessor);
  }
}
