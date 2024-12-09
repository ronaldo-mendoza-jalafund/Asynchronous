package org.apollo;

import java.util.UUID;

public class User {
  private UUID id;
  private String name;

  public User(String name) {
    this.id = UUID.randomUUID();
    this.name = name;
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

}
