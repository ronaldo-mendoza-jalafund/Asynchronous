package org.apollo;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apollo.events.Event;
import org.apollo.notification.INotificationService;
import org.apollo.subscription.Subscription;


public class SubscriptionManager {
  private ConcurrentHashMap<User, CopyOnWriteArrayList<Subscription>> subscriptions = new ConcurrentHashMap<>();
  private INotificationService notificationService;
  
  public SubscriptionManager(INotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void subscribeUser (User user, Subscription subscription) {
        subscriptions.computeIfAbsent(user, k -> new CopyOnWriteArrayList<>()).add(subscription);
        System.out.println(user.getName() + "has subscribed to " + subscription.getClass().getSimpleName());
    }

  public void notifySubscribers(Event event) {
    subscriptions.forEach((user, subs) -> {
        for (Subscription sub : subs) {
            sub.subscribeToEvent(event);
            notificationService.sendNotification(user, "New event: " + event.toString());
        }
    });
  }
}
