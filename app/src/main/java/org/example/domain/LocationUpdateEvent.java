package org.example.domain;

import lombok.Value;

import java.util.UUID;

@Value
public class LocationUpdateEvent {
    UUID truckId;
    float lat;
    float longitude;

    @Override
    public String toString() {
        return "{" +
                "lat=" + lat +
                ", longitude=" + longitude +
                '}';
    }
}
