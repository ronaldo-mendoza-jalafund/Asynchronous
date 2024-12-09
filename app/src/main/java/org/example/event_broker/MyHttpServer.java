package org.example.event_broker;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import lombok.AllArgsConstructor;
import org.example.domain.SubscribeEvent;
import org.example.mappers.SubscribeResponse;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.UUID;

@AllArgsConstructor
public class MyHttpServer implements IServer {
    public static int PORT = 8080;
    private EventPublisher<SubscribeEvent> eventPublisher;

    public MyHttpServer() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
        server.createContext("/apollo/v1/subscribe", this::subscribeHandler);
    }

    @Override
    public void startListening() {

    }

    public SubscribeResponse subscribeHandler(HttpExchange exchange) {
        String query = exchange.getRequestURI().getQuery();
        if (query.contains("topicName")) {
            String topicName = query.split("=")[0];
            SubscribeEvent subscribeEvent = new SubscribeEvent(UUID.randomUUID(), topicName);
            eventPublisher.publishEvent(topicName, subscribeEvent);
            return SubscribeResponse.builder().id(subscribeEvent.getId()).build();
        }

        return null;
    }
}
