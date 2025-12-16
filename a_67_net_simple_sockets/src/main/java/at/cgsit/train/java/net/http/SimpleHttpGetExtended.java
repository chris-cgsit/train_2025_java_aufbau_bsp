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

package at.cgsit.train.java.net.http;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SimpleHttpGetExtended {


    public static void main(String[] args) throws Exception {

        HttpResponse<String> response;

        try (HttpClient client = HttpClient.newHttpClient()) {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://www.orf.at"))
                    .header("User-Agent", "JavaHttpClient/25 (CGS IT Training)")
                    .header("Accept", "text/html,application/xhtml+xml")
                    .header("Accept-Language", "de-AT,de;q=0.9")
                    .GET()
                    .build();

            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        }

        // ---- Status ----
        System.out.println("Status: " + response.statusCode());

        explainStatus(response.statusCode());

        // ---- Response Headers ----
        System.out.println("\nResponse Headers:");
        response.headers().map().forEach((key, valueList) -> {
            System.out.println(key + ": " + valueList);
        });

        // ---- Body ----
        System.out.println("\nBody:");
        System.out.println(response.body());
    }


    private static void explainStatus(int code) {
        System.out.println("Status Erklärung mit Java-Konstanten:");

        if (code == HttpURLConnection.HTTP_OK) {
            System.out.println("200 OK – Anfrage erfolgreich.");
        } else if (code == HttpURLConnection.HTTP_CREATED) {
            System.out.println("201 Created – Ressource erfolgreich angelegt.");
        } else if (code == HttpURLConnection.HTTP_BAD_REQUEST) {
            System.out.println("400 Bad Request – Fehlerhafte Anfrage.");
        } else if (code == HttpURLConnection.HTTP_NOT_FOUND) {
            System.out.println("404 Not Found – Ressource nicht gefunden.");
        } else if (code == HttpURLConnection.HTTP_INTERNAL_ERROR) {
            System.out.println("500 Internal Server Error – Serverfehler.");
        } else {
            System.out.println("Anderer Statuscode: " + code);
        }
    }

}
