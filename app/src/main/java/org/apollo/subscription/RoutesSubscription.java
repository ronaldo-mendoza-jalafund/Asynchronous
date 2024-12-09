package org.apollo.subscription;

import org.apollo.events.Event;

public class RoutesSubscription extends Subscription {
  public RoutesSubscription(String id, String subscriberName) {
    super(id, subscriberName);
}

@Override
public void notifySubscriber(String message) {
    System.out.println("Preparing routes notification...");
    System.out.println("Notifying " + getSubscriberName() + " about routes: " + message);
}

@Override
public void subscribeToEvent(Event event) {
    System.out.println(getSubscriberName() + " has subscribed to the route event: " + event);
}
}