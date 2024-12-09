package org.example.domain;

import lombok.Value;

import java.util.UUID;

@Value
public class LocationUpdateEvent {
    UUID truckId;
    float lat;
    float longitude;
}
