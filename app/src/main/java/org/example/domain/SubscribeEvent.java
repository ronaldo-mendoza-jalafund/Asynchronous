package org.example.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import java.util.UUID;

@Value
public class SubscribeEvent {
  UUID id;
  String topic;
}
