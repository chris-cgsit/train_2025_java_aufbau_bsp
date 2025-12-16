/*
 *  Copyright © 2025 CGS IT Solutions GmbH.
 *  All rights reserved.
 *
 *  This source code is proprietary intellectual property
 *  of CGS IT Solutions GmbH and is provided solely for use
 *  within licensed training programs.
 *
 *   Any copying, redistribution, modification, or use
 *   beyond the permitted scope is strictly prohibited.
 */

package at.cgsit.train.java.chatsys.model;

import java.time.Instant;
import java.util.UUID;

public class ChatUser {

  private UUID id =  UUID.randomUUID();

  private String username;

  private String email;

  private Instant createdAt = Instant.now();

  private boolean active = true;

  public ChatUser(String username, String email) {
    this.username = username;
    this.email = email;
  }

  // Package-lokaler Konstruktor für SystemUser oder ähnliches
  ChatUser(UUID id, String username, String email) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.active = false; // Systemuser meist inaktiv
  }


  public UUID getId() {
    return id;
  }

  public String getStringId() {
    return id.toString();
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }
}