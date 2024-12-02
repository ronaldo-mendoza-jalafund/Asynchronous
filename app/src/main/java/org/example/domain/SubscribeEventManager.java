package org.example.domain;

public class SubscribeEventManager {
    private int idCount;

    public SubscribeEvent makeEvent(String topicName){
        return new SubscribeEvent(topicName, idCount++);
    }
}
