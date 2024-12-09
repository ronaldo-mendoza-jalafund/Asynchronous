package org.apollo.events;

public class GarbageUpdateEvent extends Event {
  private String garbageType;
  private int quantity;

  public GarbageUpdateEvent(String garbageType, int quantity) {
    super();
    this.garbageType = garbageType;
    this.quantity = quantity;
  }

  public String getGarbageType() {
    return garbageType;
  }

  public int getQuantity() {
    return quantity;
  }

  @Override
  public String toString() {
    return "GarbageUpdateEvent{" +
            ", garbageType='" + garbageType + '\'' +
            ", quantity=" + quantity +
            ", timestamp=" + getTimestamp() +
            '}';
  }
}
