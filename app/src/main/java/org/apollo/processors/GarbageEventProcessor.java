package org.apollo.processors;

import org.apollo.events.Event;
import org.apollo.events.EventProcessor;
import org.apollo.events.GarbageUpdateEvent;

public class GarbageEventProcessor extends EventProcessor {
  @Override
  public void processEvent(Event event) {
      if (event instanceof GarbageUpdateEvent garbageEvent) {
          String location = garbageEvent.getLocation();
          int garbageLevel = garbageEvent.getQuantity();
          if (garbageLevel > 80) {
            System.out.println("Warning: High garbage level at " + location);
          }
            System.out.println("Processing garbage update at location: " + garbageEvent.getLocation());
          } else {
            System.out.println("Received an unrecognized event type in GarbageEventProcessor.");
          }
  }
}
