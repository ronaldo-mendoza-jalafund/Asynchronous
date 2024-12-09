package org.apollo.events;

public class RouteUpdateEvent extends Event {
  private String routeId;
  private String status;

  public RouteUpdateEvent(String routeId, String status) {
    super();
    this.routeId = routeId;
    this.status = status;
  }

  public String getRouteId() {
    return routeId;
  }

  public String getStatus() {
    return status;
  }

  @Override
  public String toString() {
    return "RouteUpdateEvent{" +
            "routeId='" + routeId + '\'' +
            ", status='" + status + '\'' +
            ", timestamp=" + getTimestamp() +
            '}';
  }
}
