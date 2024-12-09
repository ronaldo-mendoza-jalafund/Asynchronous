package org.apollo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.apollo.handlers.UserInputHandler;
import org.apollo.notification.INotificationService;
import org.apollo.notification.NotificationService;

import com.sun.net.httpserver.HttpServer;

public class UserTerminal {
  private static final int PORT = 8080;
  private SubscriptionManager subscriptionManager;

  public UserTerminal() {
    INotificationService notificationService = new NotificationService();
    this.subscriptionManager = new SubscriptionManager(notificationService);
  }

  public static void main(String[] args) throws IOException {
    UserTerminal terminal = new UserTerminal();
    terminal.startServer();
  }

  public void startServer() throws IOException {
    HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
    server.createContext("/user", new UserInputHandler(subscriptionManager));
    server.setExecutor(Executors.newFixedThreadPool(10));
    server.start();
    System.out.println("Server started on port " + PORT);
  }
}
