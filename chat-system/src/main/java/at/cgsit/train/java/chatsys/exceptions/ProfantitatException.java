package at.cgsit.train.java.chatsys.exceptions;

/** Checked Exception für Profanity-Verstöße. */
public class ProfantitatException extends RuntimeException {

    private final String senderUserId;

    public ProfantitatException(String message, String senderUserId) { super(message);
      this.senderUserId = senderUserId;
    }

  public String getSenderUserId() {
    return senderUserId;
  }
}