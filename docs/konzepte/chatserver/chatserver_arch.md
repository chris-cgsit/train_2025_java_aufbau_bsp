## **Chat Server – Command vs. Message Design (Java Training Note)**

### Ziel

Nachrichtenmodell so gestalten, dass:

* Client Kommandos senden kann (`/nick`, `/join`, `/giphy`)
* Server Zeitpunkt, ID & Reihenfolge kontrolliert
* Messages immutable & audit-fähig sind
* Schüler Builder/Record/DTO/Command/Event lernen

---

## Grundidee

**Trenne**:

| Typ                        | Wer erzeugt? | Zweck                         |
| -------------------------- | ------------ | ----------------------------- |
| ClientMessageCommand       | Client       | Roh-Input (Text oder Command) |
| ChatMessage (Server Event) | Server       | Finalisierte Nachricht        |

Begründung:

* Server setzt **authoritative data**
* Commands werden **interpretiert**
* Messages sind **immutable events**
* Ermöglicht DDD / CQRS / Event-Log

---

## Key Concepts

| Konzept                 | Grund                                    |
| ----------------------- | ---------------------------------------- |
| Authoritative timestamp | Trust the server, not clients            |
| UUID + idempotencyKey   | Packet loss / retry-safe                 |
| Room-sequence           | Strikte Ordnung pro Chat                 |
| Commands vs Text        | Chat != nur Chat, sondern Protokoll      |
| Immutability            | Nachvollziehbarkeit, Audit, Parallelität |

---

## Client → Server DTO (Command)

```java
public record ClientMessageCommand(
    UUID idempotencyKey,      // vom Client generiert
    String chatId,
    String senderId,
    Instant clientSentAt,     // optional
    Payload payload           // TEXT oder COMMAND
) {
    sealed interface Payload permits Text, Command {}
    public record Text(String content) implements Payload {}
    public record Command(String raw) implements Payload {} // z.B. "/nick Bob"
}
```

---

## Server Event (final Message)

```java
public record ChatMessage(
    UUID messageId,           // vom Server
    String chatId,
    String senderId,
    String recipientId,       // null/optional
    String content,           // finaler Text (Command aufgelöst)
    MessageType type,         // TEXT/SYSTEM/COMMAND_RESULT/...
    long sequence,            // strikte Reihenfolge
    Instant timestamp,        // vom Server gesetzt
    Instant clientSentAt      // optional Info
) {}
```

---

## ⚙️ Server-Processing Flow (Kurzskizze)

```java
public ChatMessage handle(ClientMessageCommand cmd) {
    // 1) Idempotenz prüfen
    // 2) Kommandos parsen
    NormalizedResult r = commandOrText(cmd.payload());
    // 3) Sequenz vergeben
    long seq = sequenceRepo.next(cmd.chatId());
    
    return new ChatMessage(
        UUID.randomUUID(),
        cmd.chatId(),
        normalizeUser(cmd.senderId()),
        r.recipientId(),
        r.content(),
        r.type(),
        seq,
        Instant.now(),          // authoritative
        cmd.clientSentAt()
    );
}

record NormalizedResult(String content, String recipientId, MessageType type) {}
```

---

##  Messaging-Lebenszyklus

Events statt Edit-Mutation:

| Event          | Zweck                    |
| -------------- | ------------------------ |
| MessageCreated | 1. Nachricht             |
| MessageEdited  | Chat edit, Audit OK      |
| MessageDeleted | Soft delete (compliance) |
| ReactionAdded  | 👍❤️😂                   |

---

## Übungs-Ideen für Studenten

| Übung                           | Lernziel                   |
| ------------------------------- | -------------------------- |
| Implement `/nick`               | String parsing, state mgmt |
| Implement `/me`                 | Action messages            |
| Implement idempotency           | Retry-safe messaging       |
| Implement `sequence`            | AtomicLong / Map           |
| Add `Delivered` / `Read` events | Message state machine      |
| Immutable DTOs                  | Record & builder practice  |

---

## 📎 Tipp für Schulung

Aufbau **slides** für diesen Teil:

1. Problem: Chat braucht Commands
2. Lösung: Request vs Event
3. Model: ClientCommand → ServerMessage
4. Java Record DTOs
5. Demo / Live-Coding
6. Student exercise

---

## ✅ Summary

> **Commands rein, Messages raus.**
> Client sendet intent → Server bestätigt event.

Stellt sicher:

* Reproduzierbare Logik
* Audit-fähig
* Moderne Architektur (CQRS-lite)
* Perfekt für Java Training + DDD Einstieg

---
