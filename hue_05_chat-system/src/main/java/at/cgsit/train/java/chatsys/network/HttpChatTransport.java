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

package at.cgsit.train.java.chatsys.network;

import java.io.IOException;

public class HttpChatTransport implements ChatTransport {
    @Override
    public void send(String senderId, String text, String chatId) throws IOException {
        ChatHttpClient.send(senderId, text, chatId);
    }
}