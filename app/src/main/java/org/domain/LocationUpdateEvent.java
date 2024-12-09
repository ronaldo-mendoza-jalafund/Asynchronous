package org.domain;

import java.util.UUID;

public class LocationUpdateEvent {
  private UUID truckId;
  private float lat;
  private float longitude;

  public LocationUpdateEvent(UUID truckId, float lat, float longitude) {
      this.truckId = truckId;
      this.lat = lat;
      this.longitude = longitude;
  }

  public UUID getTruckId() {
    return truckId;
  }

  public float getLat() {
    return lat;
  }

  public float getLongitude() {
    return longitude;
  }
}
