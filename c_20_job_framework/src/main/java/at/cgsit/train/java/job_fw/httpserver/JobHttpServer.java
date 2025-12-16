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

package at.cgsit.train.java.job_fw.httpserver;

import at.cgsit.train.java.job_fw.api.*;
import at.cgsit.train.java.job_fw.core.InMemoryJobFramework;
import at.cgsit.train.java.job_fw.store.InMemoryJobResultStore;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.util.UUID;

/**
 * Einfacher Java-HTTP-Server, der das Job-Framework per REST anbietet.
 *
 * Endpoints:
 *  POST /jobs                 -> Job anlegen, Ticket zurückgeben
 *  GET  /jobs/{id}/status     -> Status abfragen
 *  GET  /jobs/{id}/result     -> Ergebnis abfragen
 */
public class JobHttpServer {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .findAndRegisterModules()
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    // Unser In-Memory JobFramework
    private static final JobFramework JOB_FRAMEWORK =
            new InMemoryJobFramework(
                    100,                       // Queue Capacity
                    4,                         // Worker-Threads
                    true,                      // Virtual Threads verwenden
                    new InMemoryJobResultStore()
            );

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/jobs", new JobsHandler());
        server.createContext("/jobs/", new JobByIdHandler());

        server.setExecutor(null); // default Executor
        server.start();

        System.out.println(ts() + " HTTP-Server läuft auf http://localhost:8080");
        System.out.println("POST /jobs           -> Job anlegen");
        System.out.println("GET  /jobs/{id}/status");
        System.out.println("GET  /jobs/{id}/result");
    }

    // --------- Handler für POST /jobs ---------

    static class JobsHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            try {
                if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                    handleSubmitJob(exchange);
                } else {
                    sendPlainText(exchange, 405, "Method Not Allowed");
                }
            } catch (Exception e) {
                e.printStackTrace();
                sendPlainText(exchange, 500, "Internal Server Error: " + e.getMessage());
            }
        }

        private void handleSubmitJob(HttpExchange exchange) throws IOException {
            JobSubmitRequest req = readJson(exchange.getRequestBody(), JobSubmitRequest.class);

            if (req == null || req.text() == null || req.text().isBlank()) {
                sendPlainText(exchange, 400, "Request body must contain non-empty 'text'");
                return;
            }

            // Hier bauen wir einen konkreten Job aus der Request-Payload
            Job job = new UppercaseJob(req.text());

            JobTicket ticket = JOB_FRAMEWORK.submit(job);

            JobSubmitResponse response = new JobSubmitResponse(ticket.id().toString());

            sendJson(exchange, 202, response); // 202 Accepted
        }
    }

    // --------- Handler für GET /jobs/{id}/status und /jobs/{id}/result ---------

    static class JobByIdHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            try {
                String path = exchange.getRequestURI().getPath(); // z.B. /jobs/123/status
                if (!path.startsWith("/jobs/")) {
                    sendPlainText(exchange, 404, "Not Found");
                    return;
                }

                String[] parts = path.substring("/jobs/".length()).split("/");
                if (parts.length < 2) {
                    sendPlainText(exchange, 404, "Not Found");
                    return;
                }

                String idPart = parts[0];
                String action = parts[1]; // "status" oder "result"

                UUID ticketId;
                try {
                    ticketId = UUID.fromString(idPart);
                } catch (IllegalArgumentException e) {
                    sendPlainText(exchange, 400, "Invalid ticketId UUID");
                    return;
                }

                JobTicket ticket = new JobTicket(ticketId);

                if ("status".equalsIgnoreCase(action)) {
                    handleStatus(exchange, ticket);
                } else if ("result".equalsIgnoreCase(action)) {
                    handleResult(exchange, ticket);
                } else {
                    sendPlainText(exchange, 404, "Unknown sub-path: " + action);
                }

            } catch (Exception e) {
                e.printStackTrace();
                sendPlainText(exchange, 500, "Internal Server Error: " + e.getMessage());
            }
        }

        private void handleStatus(HttpExchange exchange, JobTicket ticket) throws IOException {
            JobStatus status = JOB_FRAMEWORK.getStatus(ticket);
            if (status == null) {
                sendPlainText(exchange, 404, "Ticket not found");
                return;
            }
            JobStatusResponse resp = new JobStatusResponse(ticket.id().toString(), status.name());
            sendJson(exchange, 200, resp);
        }

        private void handleResult(HttpExchange exchange, JobTicket ticket) throws IOException {
            JobStatus status = JOB_FRAMEWORK.getStatus(ticket);
            if (status == null) {
                sendPlainText(exchange, 404, "Ticket not found");
                return;
            }

            JobResult result = JOB_FRAMEWORK.getResult(ticket);

            // Wenn noch nicht fertig → 202
            if (status == JobStatus.PENDING || status == JobStatus.RUNNING || result == null) {
                JobResultResponse resp = new JobResultResponse(
                        ticket.id().toString(),
                        status.name(),
                        null,
                        null
                );
                sendJson(exchange, 202, resp);
                return;
            }

            // Erfolg oder Fehler
            String payload = null;
            String errorMessage = null;

            if (result instanceof SuccessResult sr) {
                Object p = sr.payload();
                payload = (p != null ? p.toString() : null);
            } else if (result instanceof ErrorResult er) {
                errorMessage = er.message();
            }

            JobResultResponse resp = new JobResultResponse(
                    ticket.id().toString(),
                    status.name(),
                    payload,
                    errorMessage
            );

            int statusCode = (status == JobStatus.SUCCESS) ? 200 : 500;
            sendJson(exchange, statusCode, resp);
        }
    }

    // --------- Beispiel-Job: Uppercase-Transformation ---------

    /**
     * Sehr einfacher Beispiel-Job:
     * Nimmt Eingabetext und macht UPPERCASE draus.
     */
    static class UppercaseJob implements Job {

        private final String input;

        UppercaseJob(String input) {
            this.input = input;
        }

        @Override
        public String name() {
            return "UppercaseJob";
        }

        @Override
        public JobResult execute() {
            String result = input.toUpperCase();
            return new SuccessResult(result);
        }
    }

    // --------- DTOs für JSON (Request/Response) ---------

    public record JobSubmitRequest(String text) { }

    public record JobSubmitResponse(String ticketId) { }

    public record JobStatusResponse(String ticketId, String status) { }

    public record JobResultResponse(String ticketId,
                                    String status,
                                    String payload,
                                    String errorMessage) { }

    // --------- Helper-Methoden ---------

    private static <T> T readJson(InputStream is, Class<T> type) throws IOException {
        if (is == null) return null;
        if (is.available() == 0) {
            byte[] data = is.readAllBytes(); // dennoch lesen
            if (data.length == 0) return null;
            return OBJECT_MAPPER.readValue(data, type);
        }
        return OBJECT_MAPPER.readValue(is, type);
    }

    private static void sendJson(HttpExchange exchange, int statusCode, Object body) throws IOException {
        byte[] json = OBJECT_MAPPER.writeValueAsBytes(body);
        Headers headers = exchange.getResponseHeaders();
        headers.set("Content-Type", "application/json; charset=UTF-8");
        exchange.sendResponseHeaders(statusCode, json.length);

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(json);
        }
    }

    private static void sendPlainText(HttpExchange exchange, int statusCode, String msg) throws IOException {
        if (msg == null) msg = "";
        byte[] data = msg.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "text/plain; charset=UTF-8");
        exchange.sendResponseHeaders(statusCode, data.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(data);
        }
    }

    private static String ts() {
        return "[" + LocalTime.now().withNano(0) + "]";
    }
}
