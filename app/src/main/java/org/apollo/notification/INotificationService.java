package org.apollo.notification;

import org.apollo.User;

public interface INotificationService {
  void sendNotification(User user, String message);
}
