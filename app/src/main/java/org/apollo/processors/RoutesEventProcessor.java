package org.apollo.processors;

import org.apollo.events.Event;
import org.apollo.events.EventProcessor;
import org.apollo.events.RouteUpdateEvent;



public class RoutesEventProcessor implements EventProcessor {
  @Override
  public void processEvent(Event event) {
      if (event instanceof RouteUpdateEvent routeEvent) {
          String routeId = routeEvent.getRouteId();
          String newRouteData = routeEvent.getStatus();
          if (newRouteData.equals("closed")) {
            System.out.println("Warning: Route " + routeId + " is closed.");
          }
            System.out.println("Processing route update for route: " + routeId);
          } else {
            System.out.println("Received an unrecognized event type in RoutesEventProcessor.");
      }
  }
}