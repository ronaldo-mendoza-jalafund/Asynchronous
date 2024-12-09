package org.apollo.events;

public class LocationUpdateEvent extends Event {
  private String userId;
  private double latitude;
  private double longitude;

  public LocationUpdateEvent(String userId, double latitude, double longitude) {
    this.userId = userId;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public String getUserId() {
    return userId;
  }

  public double getLatitude() {
    return latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  @Override
  public String toString() {
    return "LocationUpdateEvent{" +
            "userId='" + userId + '\'' +
            ", latitude=" + latitude +
            ", longitude=" + longitude +
            '}';
  }
}
