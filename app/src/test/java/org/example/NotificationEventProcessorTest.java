package org.example;

import java.util.UUID;

public class NotificationEventProcessorTest {
    private EventPublisher eventPublisher;
    private ClientSocket clientSocket;
    private NotificationEventProcessor notificationEventProcessor;

    @Before
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