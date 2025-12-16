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

package at.cgsit.train.java.chatsys.server;

import at.cgsit.train.java.chatsys.exceptions.ProfantitatException;
import at.cgsit.train.java.chatsys.model.ChatMessage;


/**
 * unser chat server implementiert mehr als nur das,
 * aber send message und system message ist der Kern der Sache
 * diejenigen Verwender die nur senden, MUESSEN gg dieses Interface arbeiten
 */
public interface ChatInterface {

  void sendMessage(ChatMessage message) throws ProfantitatException;

  void sendSystemMessage(String text) throws ProfantitatException;
}
