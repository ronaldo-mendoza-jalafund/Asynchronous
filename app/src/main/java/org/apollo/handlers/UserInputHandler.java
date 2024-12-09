package org.apollo.handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    String endpointDescription = """
        Endpoint: /user
        Method: POST
        Description: This endpoint handles user input for subscribing to topics, sending events, and accessing the inbox.
        Request Body: JSON format expected.
        Response: Returns a confirmation message.""";
    System.out.println(endpointDescription);

    StringBuilder requestBody = new StringBuilder();
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(exchange.getRequestBody()))) {
      String line;
      while ((line = reader.readLine()) != null) {
        requestBody.append(line);
      }
    }

    String requestBodyString = requestBody.toString();
    System.out.println("Request Body: " + requestBodyString);

    // Manual parsing of the JSON request body
    String action = extractValue(requestBodyString, "action");
    String responseMessage;

    switch (action) {
      case "subscribe" -> {
          int topicNumber = Integer.parseInt(extractValue(requestBodyString, "topic_number"));
          responseMessage = "Subscribed to topic " + topicNumber;
          }
      case "send_event" -> {
          int eventNumber = Integer.parseInt(extractValue(requestBodyString, "event_number"));
          int quantity = Integer.parseInt(extractValue(requestBodyString, "quantity"));
          responseMessage = "Sent " + quantity + " of event " + eventNumber;
          }
      case "inbox" -> responseMessage = "Opening inbox...";
      default -> responseMessage = "Invalid action.";
    }

    String response = "{\"status\":\"success\",\"message\":\"" + responseMessage + "\"}";
    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length());
    OutputStream os = exchange.getResponseBody();
    os.write(response.getBytes());
    os.close();
  }

  private String extractValue(String json, String key) {
    String searchKey = "\"" + key + "\":";
    int startIndex = json.indexOf(searchKey) + searchKey.length();
    int endIndex = json.indexOf(",", startIndex);
    if (endIndex == -1) {
        endIndex = json.indexOf("}", startIndex);
    }
    String value = json.substring(startIndex, endIndex).trim();
    if (value.startsWith("\"") && value.endsWith("\"")) {
        value = value.substring(1, value.length() - 1);
    }
    return value;
  }
}
