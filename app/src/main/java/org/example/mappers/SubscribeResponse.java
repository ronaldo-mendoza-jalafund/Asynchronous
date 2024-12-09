package org.example.mappers;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class SubscribeResponse {
    private final UUID id;
}
