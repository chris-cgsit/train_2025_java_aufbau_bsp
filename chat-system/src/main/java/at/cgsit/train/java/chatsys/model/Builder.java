package at.cgsit.train.java.chatsys.model;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;


/**
 * siehe
 * https://www.baeldung.com/java-builder-pattern
 *
 *
 * ChatMessage msg = new ChatMessage.Builder("room123", "user42", "Hallo Welt!", MessageType.TEXT)
 *         .recipientId("user99")       // optional
 *         .timestamp(Instant.now())    // optional
 *         .build();
 */
public class Builder {

  // WILL be used in SW Design and not in basic workshop

  private UUID messageId = UUID.randomUUID();
  private String chatId;
  private String senderId;
  private Optional<String> recipientId = Optional.empty();
  private String content;
  private MessageTyp type = MessageTyp.TEXT;
  private Instant timestamp = Instant.now();

  public Builder chatId(String c) { this.chatId = c; return this; }
  public Builder senderId(String s) { this.senderId = s; return this; }
  public Builder content(String t) { this.content = t; return this; }
  // public ChatMessage build() { return new ChatMessage(this); }
}
