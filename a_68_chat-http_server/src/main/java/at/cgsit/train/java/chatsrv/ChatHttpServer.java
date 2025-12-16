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

package at.cgsit.train.java.chatsrv;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.awt.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.lang.Integer.parseInt;

public class ChatHttpServer {

    // Buckets
    private static final String KEY_SYSTEM  = "_SYSTEM";
    private static final String KEY_NO_ROOM = "_NO_ROOM";

    // Historien: system, no_room, rooms
    private static final Map<String, List<ChatMessage>> HISTORIES = new ConcurrentHashMap<>();

    // Profanity (lowercase)
    private static final Set<String> PROFANITIES = Set.of("iditot", "dummkopf", "trottel");

    public static void main(String[] args) throws Exception {
        HISTORIES.put(KEY_SYSTEM,  new CopyOnWriteArrayList<>());
        HISTORIES.put(KEY_NO_ROOM, new CopyOnWriteArrayList<>());

      String chatSrvPort = System.getenv("CHAT_SRV_PORT");
      if (chatSrvPort == null) {
        chatSrvPort = "8080";
      }
      System.out.println("Chat SRV Port : " + chatSrvPort);
      Integer port = parseInt(chatSrvPort);
      HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/message", new PostMessageHandler());
        server.createContext("/messages", new GetMessagesHandler());
        server.createContext("/system", new PostSystemHandler()); // Trainer: System-Broadcast

        // mit setExecutor(null) → requests handled one-by-one also "sequenziell".
        // das ist ausreichend für uns und einfacher im debugging
        server.setExecutor(null);
        System.out.printf("Chat HTTP Server läuft auf http://localhost:%s", port);
        server.start();
    }

    // ----- POST /message  (body: senderId=...&content=...&chatId=optional) -----
    static class PostMessageHandler implements HttpHandler {
        @Override public void handle(HttpExchange ex) throws IOException {
            if (!"POST".equalsIgnoreCase(ex.getRequestMethod())) { respond(ex, 405, "use POST"); return; }
            Map<String,String> form = readForm(ex);
            String senderId = form.get("senderId");
            String content  = form.get("content");
            String chatId   = form.getOrDefault("chatId", "");

            if (isBlank(senderId) || isBlank(content)) { respond(ex, 400, "senderId and content required"); return; }
            if (containsProfanity(content)) { respond(ex, 400, "message rejected: profanity"); return; }

            ChatMessage msg = ChatMessage.userMsg(senderId, content, chatId);
            appendToHistory(msg);
            System.out.println(msg);


            respond(ex, 200, "ok");
        }
    }

    // ----- POST /system  (body: text=... )  -> Trainer sendet Systemnachricht -----
    static class PostSystemHandler implements HttpHandler {
        @Override public void handle(HttpExchange ex) throws IOException {
            if (!"POST".equalsIgnoreCase(ex.getRequestMethod())) { respond(ex, 405, "use POST"); return; }
            Map<String,String> form = readForm(ex);
            String text = form.get("text");
            if (isBlank(text)) { respond(ex, 400, "text required"); return; }
            if (containsProfanity(text)) { respond(ex, 400, "system message rejected: profanity"); return; }

            ChatMessage sys = ChatMessage.systemMsg(text);
            HISTORIES.get(KEY_SYSTEM).add(sys);

            respond(ex, 200, "ok");
        }
    }

    // ----- GET /messages?scope=all|system|no_room|room&chatId=...&since=0 -----
    // Rückgabe: text/plain, eine Nachricht pro Zeile:
    // index|timestamp|senderId|chatId|content
    static class GetMessagesHandler implements HttpHandler {
        @Override public void handle(HttpExchange ex) throws IOException {
            if (!"GET".equalsIgnoreCase(ex.getRequestMethod())) { respond(ex, 405, "use GET"); return; }

            try {

            System.out.printf("GET message receved on server ");

            Map<String,String> q = parseQuery(ex.getRequestURI().getRawQuery());
            String scope = q.getOrDefault("scope", "all"); // all/system/no_room/room
            String chatId = q.getOrDefault("chatId", "");
            int since = safeParseInt(q.get("since"), 0);
            // int since = parseInt(q.getOrDefault("since", "0"), 0);

            List<ChatMessage> out = new ArrayList<>();
            switch (scope) {
                case "system"  -> out = HISTORIES.getOrDefault(KEY_SYSTEM, List.of());
                case "no_room" -> out = HISTORIES.getOrDefault(KEY_NO_ROOM, List.of());
                case "room"    -> out = HISTORIES.getOrDefault(chatId, List.of());
                case "all" -> {
                    out = new ArrayList<>();
                    out.addAll(HISTORIES.getOrDefault(KEY_SYSTEM, List.of()));
                    out.addAll(HISTORIES.getOrDefault(KEY_NO_ROOM, List.of()));
                    // alle rooms
                    for (var e : HISTORIES.entrySet()) {
                        String k = e.getKey();
                        if (!KEY_SYSTEM.equals(k) && !KEY_NO_ROOM.equals(k)) out.addAll(e.getValue());
                    }
                    // stabile Ausgabe-Reihenfolge
                    out.sort(Comparator.comparing(ChatMessage::timestamp));
                }
                default -> {}
            }

            StringBuilder sb = new StringBuilder();
            for (int i = since; i < out.size(); i++) {
                ChatMessage m = out.get(i);
                sb.append(i).append("|")
                  .append(m.timestamp()).append("|")
                  .append(m.senderId()).append("|")
                  .append(nullToEmpty(m.chatId())).append("|")
                  .append(escapePipes(m.content()))
                  .append("\n");
            }
            respond(ex, 200, sb.toString());

            } catch (Exception e) {
              e.printStackTrace(); // oder System.err.println("Fehler: " + e.getMessage());
              respond(ex, 400, "Bad request: " + e.getMessage());
            }

        }
    }

    // ----- Helpers -----
    private static void appendToHistory(ChatMessage msg) {
        if (msg.isSystem()) { HISTORIES.get(KEY_SYSTEM).add(msg); return; }
        String chatId = msg.chatId();
        if (isBlank(chatId)) {
            HISTORIES.get(KEY_NO_ROOM).add(msg);
        } else {
            HISTORIES.computeIfAbsent(chatId, k -> new CopyOnWriteArrayList<>()).add(msg);
        }
    }

    private static boolean containsProfanity(String content) {
        String lower = content.toLowerCase(Locale.ROOT);
        for (String bad : PROFANITIES) if (lower.contains(bad)) return true;
        return false;
    }

    private static Map<String,String> readForm(HttpExchange ex) throws IOException {
        String body = new String(ex.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
        return parseQuery(body);
    }

    private static Map<String,String> parseQuery(String raw) {
        Map<String,String> map = new HashMap<>();
        if (raw == null || raw.isEmpty()) return map;
        for (String part : raw.split("&")) {
            int i = part.indexOf('=');
            String k = i >= 0 ? part.substring(0, i) : part;
            String v = i >= 0 ? part.substring(i + 1) : "";
            map.put(urlDecode(k), urlDecode(v));
        }
        return map;
    }

    private static String urlDecode(String s) {
        return URLDecoder.decode(s, StandardCharsets.UTF_8);
    }

    private static void respond(HttpExchange ex, int code, String text) throws IOException {
        byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
        ex.getResponseHeaders().add("Content-Type", "text/plain; charset=utf-8");
        ex.sendResponseHeaders(code, bytes.length);
        try (OutputStream os = ex.getResponseBody()) { os.write(bytes); }
    }

    private static String nullToEmpty(String s) { return s == null ? "" : s; }
    private static String escapePipes(String s) { return s.replace("|", "\\|"); }
    private static boolean isBlank(String s) { return s == null || s.isBlank(); }

    // Minimaler Message-Record (keine JSON-Dependency)
    record ChatMessage(String senderId, String content, String chatId, boolean isSystem, Instant timestamp) {
        static ChatMessage userMsg(String sender, String content, String chatId) {
            return new ChatMessage(sender, content, (chatId == null ? "" : chatId), false, Instant.now());
        }
        static ChatMessage systemMsg(String content) {
            return new ChatMessage("SYSTEM", content, "", true, Instant.now());
        }
    }


  private static int safeParseInt(String s, int def) {
    if (s == null) return def;
    try {
      return Integer.parseInt(s.trim());
    } catch (NumberFormatException e) {
      return def;
    }
  }

}
