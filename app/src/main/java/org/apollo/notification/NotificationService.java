package org.apollo.notification;

import java.util.concurrent.CompletableFuture;

import org.apollo.User;

public class NotificationService implements INotificationService {
  @Override
  public void sendNotification(User user, String message) {
        CompletableFuture.runAsync(() -> {
            try {
              Thread.sleep(2000); 
            } catch (InterruptedException e) {
              Thread.currentThread().interrupt();
              System.err.println("Notification sending was interrupted");
            }
            System.out.println("Sending notification to " + user.getName() + ": " + message);
        });
  }
  
}
