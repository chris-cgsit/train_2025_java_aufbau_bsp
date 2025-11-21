package at.cgsit.train.java.chatsys.model;

import java.util.UUID;

public class SystemUserCreator {

  private static final ChatUser SYSTEM_USER = createSystemUser();

  public static ChatUser createSystemUser() {

    if (SYSTEM_USER == null) {
      // Systemuser mit fixer UUID — über package-lokalen Konstruktor
      return new ChatUser(UUID.fromString("00000000-0000-0000-0000-000000000001"),
          "System", "system@chat.local");
    }

    return SYSTEM_USER;
  }

}
