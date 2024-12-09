package org.apollo.subscription;

import org.apollo.events.Event;

public abstract class Subscription {
  private String id;
  private String subscriberName;

  public Subscription(String id, String subscriberName) {
      this.id = id;
      this.subscriberName = subscriberName;
  }

  public String getId() {
      return id;
  }

  public String getSubscriberName() {
      return subscriberName;
  }

  public abstract void notifySubscriber(String message);
  public abstract void subscribeToEvent(Event event);   
}
