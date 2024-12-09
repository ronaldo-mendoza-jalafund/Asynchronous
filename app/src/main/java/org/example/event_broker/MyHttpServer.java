package org.example.event_broker;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import org.example.domain.SubscribeEvent;
import org.example.domain.SubscribeEventManager;
import org.example.event_processor.IEventProcessor;
import org.example.mappers.SubscribeResponse;

import java.io.IOException;
import java.net.InetSocketAddress;

public class MyHttpServer implements IServer {
    public static int PORT = 8080;
    private SubscribeEventManager subscribeEventManager;
    private EventPublisher<SubscribeEvent> eventPublisher;

    public MyHttpServer() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT),0);
        server.createContext("/apollo/v1/subscribe", this::subscribeHandler);
    }

    @Override
    public void startListening() {

    }

    public SubscribeResponse subscribeHandler(HttpExchange exchange){
        String query = exchange.getRequestURI().getQuery();
        if (query.contains("topicName")){
            String topicName = query.split("=")[0];
            SubscribeEvent subscribeEvent = subscribeEventManager.makeEvent(topicName);
            // fire subscribe event
            return new SubscribeResponse(String.valueOf(subscribeEvent.getSubscriberId()));
        }

        return null;
    }
}
