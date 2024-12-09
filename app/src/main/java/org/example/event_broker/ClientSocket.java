package org.example.event_broker;

import java.util.concurrent.CompletableFuture;

public class ClientSocket {
  public void send(String data) {
      CompletableFuture.runAsync(() -> {
          System.out.println("Notification sent to client: " + data);
      });
  }
}
