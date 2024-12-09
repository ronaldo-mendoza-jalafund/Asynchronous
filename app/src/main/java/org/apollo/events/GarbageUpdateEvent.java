package org.apollo.events;

public class GarbageUpdateEvent extends Event {
  private String location;
  private String garbageType;
  private int quantity;

  public GarbageUpdateEvent(String location, String garbageType, int quantity) {
    super();
    this.location = location;
    this.garbageType = garbageType;
    this.quantity = quantity;
  }

  public String getLocation() {
    return location;
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
            "location='" + location + '\'' +
            ", garbageType='" + garbageType + '\'' +
            ", quantity=" + quantity +
            ", timestamp=" + getTimestamp() +
            '}';
  }
}
