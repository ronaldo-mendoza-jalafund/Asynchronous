package org.apollo.processors;

import org.apollo.events.Event;
import org.apollo.events.EventProcessor;
import org.apollo.events.LocationUpdateEvent;


public class LocationEventProcessor implements EventProcessor {
  
  @Override
  public void processEvent(Event event) {
        if (event instanceof LocationUpdateEvent locationEvent) {
            String userId = locationEvent.getUserId();
            System.out.println("Processing location update for user: " + userId);
        } else {
            System.out.println("Received an unrecognized event type in LocationEventProcessor.");
        }
  }
  
}
