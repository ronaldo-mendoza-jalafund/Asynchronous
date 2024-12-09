package org.example;

import org.example.event_broker.ClientSocket;
import org.example.domain.NotificationEvent;
import org.example.event_broker.EventPublisher;
import org.example.processor.NotificationEventProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotificationEventProcessorTest {
    private EventPublisher eventPublisher;
    private ClientSocket clientSocket;
    private NotificationEventProcessor notificationEventProcessor;

    @BeforeEach
    public void setup() { 
        this.eventPublisher = new EventPublisher();
        this.clientSocket = new ClientSocket(); 
        this.notificationEventProcessor = new NotificationEventProcessor(clientSocket, eventPublisher);
        eventPublisher.subscribe("notification", notificationEventProcessor);
    }

    @Test
    public void testHandleEvent() { 
        UUID subscriberId = UUID.randomUUID();
        String payload = "Hello, World!";
        NotificationEvent notificationEvent = new NotificationEvent(subscriberId, payload);
        notificationEventProcessor.handleEvent(notificationEvent);
        assertEquals("Hello, World!", notificationEvent.getPayload());
    }

    @Test 
    public void testHandleEventWithClientSocket() { 
        UUID subscriberId = UUID.randomUUID();
        String payload = "Hello, World!";
        NotificationEvent notificationEvent = new NotificationEvent(subscriberId, payload);
        notificationEventProcessor.handleEvent(notificationEvent);
        assertEquals("Hello, World!", notificationEvent.getPayload());
    }
}