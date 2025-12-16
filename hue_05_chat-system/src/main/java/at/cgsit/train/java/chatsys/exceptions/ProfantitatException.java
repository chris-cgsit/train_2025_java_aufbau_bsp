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