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

package at.cgsit.train.java.simplenet;

import java.io.*;
import java.net.*;

public class SimpleSocketServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(5000)) {
            System.out.println("Server läuft auf Port 5000 ...");
            try (Socket socket = server.accept()) { // wartet auf 1 Client
                System.out.println("Client verbunden: " + socket.getInetAddress());
                BufferedReader in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                String msg = in.readLine();           // Empfang
                System.out.println("Empfangen: " + msg);
                out.println("Server sagt hallo zurück!"); // Antwort senden
            }
        }
    }
}
