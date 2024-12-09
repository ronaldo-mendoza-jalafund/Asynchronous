package org.apollo.subscription;

import org.apollo.events.Event;

public class LocationSubscription extends Subscription {
  public LocationSubscription(String id, String subscriberName) {
    super(id, subscriberName);
}

  @Override
  public void notifySubscriber(String message) {
    System.out.println("Preparing location change notification...");	
    System.out.println("Notifying " + getSubscriberName() + " on changes of location: " + message);
  }

  @Override
  public void subscribeToEvent(Event event) {
    System.out.println(getSubscriberName() + " has subscribed to the location event: " + event);
  }
}
