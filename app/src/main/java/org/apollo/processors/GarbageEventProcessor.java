package org.apollo.processors;

import org.apollo.events.Event;
import org.apollo.events.EventProcessor;
import org.apollo.events.GarbageUpdateEvent;

public class GarbageEventProcessor implements EventProcessor {
  @Override
  public void processEvent(Event event) {
      if (event instanceof GarbageUpdateEvent garbageEvent) {
          int garbageLevel = garbageEvent.getQuantity();
          if (garbageLevel > 80) {
            System.out.println("Warning: Garbage level is above 80%.");
          }else{
            System.out.println("Garbage level is below 80%.");
          }
      }
  }
}
