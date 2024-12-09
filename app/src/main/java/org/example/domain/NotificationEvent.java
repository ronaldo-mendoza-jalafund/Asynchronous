package org.example.domain;

import lombok.Value;

import java.util.UUID;

@Value
public class NotificationEvent {
    UUID subscriberId;
    String payload;
}