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
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class ChatMessage {

  /**
   * Represents a message in a chat system.
   * Supports 1:1 and room messages. <br/>
   * a universal unique identifier, to be able to create it anywhere in the universe
   * immediately when the message is constructed
   */
  private final UUID messageId  = UUID.randomUUID();

  // chat room ID or 1:1 ID
  private String chatId;

  // the senders user id as a string
  private final String senderId;
  // the user who's the message recipent or null for room messages
  // optional for 1:1, null for room broadcast
  private Optional<String> recipientId = Optional.empty();

  // this is the message itself, or any other content as a string
  private String content;

  private MessageTyp type;             // text | image | file

  // a timestamp when the message was created

  private Instant timestamp = Instant.now();

  // let it autobox from boolean to Boolean for the fallback default
  private Boolean isSystemMessage =  false;

  public ChatMessage(String senderId, String content, Boolean isSystemMessage) {
    this.senderId = senderId;
    this.content = content;
    this.isSystemMessage = isSystemMessage;
    if (!isSystemMessage) {
      throw new RuntimeException("Only System Message is allowed for This constructor");
    }
  }

  public ChatMessage(ChatUser sender, ChatUser recipient, String content) {
    this.senderId = sender.getStringId();
    this.recipientId = recipient.getStringId().describeConstable();
    this.content = content;
  }


  public ChatMessage(String senderId, String recipientId, String content) {
    this.senderId = senderId;
    this.recipientId = recipientId.describeConstable();
    this.content = content;
  }

  /**
   * validate myself
   */
  public void validate() {

    /// objects require not null will throw a NullPointerException already if null
    Objects.requireNonNull(chatId);
    Objects.requireNonNull(senderId, "the sender id must be given for ANY message");
    Objects.requireNonNull(content);
  }

  public UUID getMessageId() {
    return messageId;
  }


  public String getChatId() {
    return chatId;
  }

  public void setChatId(String chatId) {
    this.chatId = chatId;
  }

  public String getSenderId() {
    return senderId;
  }

  public Optional<String> getRecipientId() {
    return recipientId;
  }

  public void setRecipientId(String recipientId) {
    this.recipientId = recipientId.describeConstable();
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public MessageTyp getType() {
    return type;
  }

  public void setType(MessageTyp type) {
    this.type = type;
  }

  public Instant getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Instant timestamp) {
    this.timestamp = timestamp;
  }

  public void setRecipientId(Optional<String> recipientId) {
    this.recipientId = recipientId;
  }

  public Boolean getSystemMessage() {
    return isSystemMessage;
  }

  public void setSystemMessage(Boolean systemMessage) {
    isSystemMessage = systemMessage;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof ChatMessage that)) return false;
    return Objects.equals(getMessageId(), that.getMessageId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getMessageId());
  }
}

