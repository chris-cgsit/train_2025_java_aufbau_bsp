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

package at.cgsit.train.java.sharedObj;

import java.io.Serializable;

public class ChatMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private String sender;
    private String text;

    public ChatMessage() {
      super();
    }

    public ChatMessage(String sender, String text) {
        this.sender = sender;
        this.text = text;
    }

  public String getSender() {
    return sender;
  }

  public String getText() {
    return text;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Override
    public String toString() {
        return sender + ": " + text;
    }



}
