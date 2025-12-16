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

package at.cgsit.train.java.json_util_generic;

import at.cgsit.train.java.sharedObj.ChatMessage;
import com.fasterxml.jackson.core.JsonProcessingException;

public class JacksonWithUtilGenericDemo {

  static void main() throws JsonProcessingException {

    // verwendung als Instanz Obekt

    JsonUtilsGen<ChatMessage> jsonUtil = new JsonUtilsGen<>(ChatMessage.class);

    // 1. Objekt erzeugen
    ChatMessage msg = new ChatMessage("Wifi Kurs", "<html><body><h1>hello world</h1></body></html>");

    String json = jsonUtil.toJson(msg);
    System.out.println("Objekt gespeichert: " + json);

    ChatMessage chatMessageDeserialized = jsonUtil.fromJson(json);
    System.out.println("Objekt gespeichert deserialized: " + chatMessageDeserialized);


  }

}
