package org.apollo.handlers;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import org.apollo.SubscriptionManager;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class UserInputHandler implements HttpHandler {
  private SubscriptionManager subscriptionManager;

  public UserInputHandler(SubscriptionManager subscriptionManager) {
      this.subscriptionManager = subscriptionManager;
  }
  @Override
  public void handle(HttpExchange exchange) throws IOException {
      String response = "Hello, this is the User Terminal!";
      exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length());
      OutputStream os = exchange.getResponseBody();
      os.write(response.getBytes());
      os.close();
  } 
}
