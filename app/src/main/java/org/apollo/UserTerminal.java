package org.apollo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.Executors;

import org.apollo.handlers.UserInputHandler;
import org.apollo.notification.INotificationService;
import org.apollo.notification.NotificationService;

import com.sun.net.httpserver.HttpServer;

public class UserTerminal {
  private static final int PORT = 8080;
  private SubscriptionManager subscriptionManager;
  private Scanner scanner;

  public UserTerminal() {
    INotificationService notificationService = new NotificationService();
    this.subscriptionManager = new SubscriptionManager(notificationService);
    this.scanner = new Scanner(System.in);
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

  public void displayMainMenu() {
    System.out.println("========================================");
    System.out.println("        Apollo Server");
    System.out.println("========================================");
    System.out.println("Welcome, user");
    System.out.println();
    System.out.println("    1. Subscribe");
    System.out.println("    2. Send event");
    System.out.println("    3. Inbox");
    System.out.println("    4. Exit");
    System.out.println("----------------------------------------");
    int choice = scanner.nextInt();
    scanner.nextLine();

    switch (choice) {
      case 1 -> displaySubscribeMenu();
      case 2 -> displaySendEventMenu();
      case 3 -> displayInboxMenu();
      case 4 -> {
          System.out.println("Exiting...");
          return;
          }
      default -> System.out.println("Invalid choice. Please try again.");
    }
  }

  private void displaySubscribeMenu() {
    System.out.println("========================================");
    System.out.println("        Subscribe to topic - Menu");
    System.out.println("========================================");
    System.out.println("    1. List");
    System.out.println("    2. Subscribe");
    System.out.println("    3. Go back");
    int choice = scanner.nextInt();
    scanner.nextLine();

    switch (choice) {
      case 1 -> {
          System.out.println("Listing available topics...");
          // listAvailableTopics();
          }
      case 2 -> {
          System.out.println("Enter topic number to subscribe:");
          int topicNumber = scanner.nextInt();
          scanner.nextLine();
          // subscribeToTopic(topicNumber);
          }
      case 3 -> {
          return;
          }
      default -> System.out.println("Invalid choice. Please try again.");
    }
  }

  private void displaySendEventMenu() {
    while (true) {
      System.out.println("----------------------------------------");
      System.out.println("    Send event - Menu");
      System.out.println("----------------------------------------");
      System.out.println("    1. List subscribed");
      System.out.println("    2. List available events");
      System.out.println("    3. Send event");
      System.out.println("    4. Go back");

      int choice = scanner.nextInt();
      scanner.nextLine(); // Limpiar el buffer

      switch (choice) {
        case 1 -> {
            System.out.println("Listing subscribed topics...");
            // listSubscribedTopics();
            }
        case 2 -> {
            System.out.println("Listing available events...");
            // listAvailableEvents();
            }
        case 3 -> {
            System.out.println("Enter topic number, event number, and quantity:");
            int topicNum = scanner.nextInt();
            int eventNum = scanner.nextInt();
            int quantity = scanner.nextInt();
            scanner.nextLine();
            // sendEvent(topicNum, eventNum, quantity);
            
            }
        case 4 -> {
            return;
            }
        default -> System.out.println("Invalid choice. Please try again.");
      }

    }

  }

  private void displayInboxMenu() {
    System.out.println("========================================");
    System.out.println("        Inbox - Menu");
    System.out.println("========================================");
    System.out.println("    1. List subscribed");
    System.out.println("    2. Open inbox");
    System.out.println("    3. Go back");
    int choice = scanner.nextInt();
    scanner.nextLine();
    switch (choice) {
      case 1 -> {
          System.out.println("Listing subscribed topics...");
          // listSubscribedTopics();
          }
      case 2 -> {
          System.out.println("Opening inbox...");
          // openInbox();
          }
      case 3 -> {
          return;
          }
      default -> System.out.println("Invalid choice. Please try again.");
    }
  }

}
