package org.example.domain;

import java.util.UUID;

public class NotificationEvent {
  private UUID subscriberId;
  private String payload;

  public NotificationEvent(UUID subscriberId, String payload) {
      this.subscriberId = subscriberId;
      this.payload = payload;
  }

  public UUID getSubscriberId() {
      return subscriberId;
  }

  public String getPayload() {
      return payload;
  } 
}
