package org.example.event_broker;

import org.example.mappers.SubscribeResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class HttpServerTest {

    @Test
    void simpleHttpRequest() throws IOException {
        String query = "/apollo/v1/subscribe?topicName=location";
        MyHttpServer server = new MyHttpServer();
        SubscribeResponse response = server.subscribeHandler(null);
        assertNotNull(response.getId());
    }
}
