package org.apollo.subscription;

import org.apollo.events.Event;

public class GarbageSubscription extends Subscription {
  public GarbageSubscription(String id, String subscriberName) {
    super(id, subscriberName);
}

  @Override
  public void notifySubscriber(String message) {
    System.out.println("Preparing garbage collection notification...");
    System.out.println("Notifying " + getSubscriberName() + " on garbage collection: " + message);
  }

  @Override
  public void subscribeToEvent(Event event) {
    System.out.println(getSubscriberName() + " has subscribed to the trash event: " + event);
  }
}