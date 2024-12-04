package org.domain;

import java.util.UUID;

public class SubscribeEvent {
  private UUID id;
  private String topic;

  public SubscribeEvent(String topic) {
      this.id = UUID.randomUUID();
      this.topic = topic;
  }

  public UUID getId() {
      return id;
  }

  public String getTopic() {
      return topic;
  }
}