package at.cgsit.train.java.net.simplest;

import at.cgsit.train.java.net.constants.AppConstants;

import java.io.*;
import java.net.*;

public class SimpleServer {

    public static void main(String[] args) {
      int port = AppConstants.SERVER_PORT_IP;
        System.out.println("Server startet auf Port " + port + " ...");

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client verbunden: " + clientSocket.getInetAddress());

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()))) {

                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println("[Server empfängt] " + line);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
