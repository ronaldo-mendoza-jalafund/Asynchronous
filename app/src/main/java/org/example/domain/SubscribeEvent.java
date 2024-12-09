package org.example.domain;

public class SubscribeEvent {
    private String topicName;
    private int subscriberId;

    public SubscribeEvent(String topicName, int subscriberId) {
        this.topicName = topicName;
        this.subscriberId = subscriberId;
    }

    public String getTopicName() {
        return topicName;
    }

    public int getSubscriberId() {
        return subscriberId;
    }
}
