package org.example.event_processor;

import org.example.domain.LocationUpdateEvent;
import org.example.domain.NotificationEvent;
import org.example.event_broker.EventPublisher;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LocationEventProcessor implements IEventProcessor<LocationUpdateEvent> {
    private EventPublisher<NotificationEvent> notificationEventProcessorEventPublisher;
    private List<UUID> subscriberList;
    private List<UUID> truckList;
    private boolean isGateOpen;
    private Map<UUID, LocationUpdateEvent> buffer = new HashMap<>();
    private ScheduledExecutorService scheduler;

    public LocationEventProcessor(EventPublisher<NotificationEvent> notificationEventProcessorEventPublisher) {
        this.notificationEventProcessorEventPublisher = notificationEventProcessorEventPublisher;
        subscriberList = new ArrayList<>();
        truckList = new ArrayList<>();
        isGateOpen = false;
        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(this::processEvents, 0, 25000, TimeUnit.MILLISECONDS);
    }

    @Override
    public void handleEvent(LocationUpdateEvent event) {
        if (isGateOpen) {
            addEvent(event);
        }
    }

    private void processEvents() {
        subscriberList.forEach(subId -> {
            for (UUID truckID : truckList) {
                fireNotificationEvent(subId, buffer.get(truckID).toString());
            }
        });
    }

    private void addEvent(LocationUpdateEvent event) {
        isGateOpen = true;
        if (!buffer.containsKey(event.getTruckId())) {
            buffer.put(event.getTruckId(), event);
        }
        isGateOpen = false;
    }

    private void fireNotificationEvent(UUID subscriberId, String payload) {
        notificationEventProcessorEventPublisher.publishEvent("notification",
                new NotificationEvent(subscriberId, payload)
        );
    }

    private void addSubscriber(UUID id) {
        subscriberList.add(id);
    }

    private void addTruck(UUID id) {
        truckList.add(id);
    }
}
