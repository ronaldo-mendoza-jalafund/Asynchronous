package org.example.processor;

import org.example.domain.NotificationEvent;
import org.example.broker.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class NotificationEventProcessor implements IEventProcessor {
  private final ClientSocket clientSocket;
  private EventPublisher eventBroker;
  private List<NotificationEvent> pendingNotifications;

  public NotificationEventProcessor(ClientSocket clientSocket, EventPublisher eventBroker) {
      this.clientSocket = clientSocket;
      this.eventBroker = eventBroker;
      this.pendingNotifications = new ArrayList<>();
  }

  @Override
  public void handleEvent(Object event) {
      if (event instanceof NotificationEvent notificationEvent) {
          pendingNotifications.add(notificationEvent);
          send(notificationEvent);
      }
  }

  public void send(NotificationEvent event) {
      CompletableFuture.runAsync(() -> {
          int maxRetries = 3;
          int retryCount = 0;
          long waitTime = 1000;
          while (retryCount < maxRetries) {
              try {
                  clientSocket.send(event.getPayload());
                  System.out.println("Notification sent to " + event.getSubscriberId() + ": " + event.getPayload());
                  return;
              } catch (Exception e) {
                  retryCount++;
                  System.err.println("Error sending notification. Attempt " + retryCount + " of " + maxRetries + ": " + e.getMessage());
                  if (retryCount < maxRetries) {
                      try {
                          Thread.sleep(waitTime);
                          waitTime *= 2;
                      } catch (InterruptedException ie) {
                          Thread.currentThread().interrupt();
                          System.err.println("Thread interrupted during timeout.");
                          return;
                      }
                  }
              }
          }
          System.err.println("The maximum number of attempts to send the notification has been reached.");
      }).exceptionally(ex -> {
          System.err.println("Error in asynchronous sending: " + ex.getMessage());
          return null;
      });
  }

  public List<NotificationEvent> getPendingNotifications() {
      return new ArrayList<>(pendingNotifications);
  }
}