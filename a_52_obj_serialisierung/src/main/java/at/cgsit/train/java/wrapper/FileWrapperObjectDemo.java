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

package at.cgsit.train.java.wrapper;

import at.cgsit.train.java.sharedObj.ChatMessage;

import java.io.*;

public class FileWrapperObjectDemo {

    public static void main(String[] args) {

        String filename = "message.bin";

        // 1. Objekt erzeugen
        ChatMessage msg = new ChatMessage("chris", "Hallo Datei!");
        MessageWrapper<ChatMessage> wrapper =
                new MessageWrapper<>("chat", msg);

        // 2. Objekt serialisiert in Datei schreiben
        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(filename))) {

            out.writeObject(wrapper);
            out.flush();
            System.out.println("Objekt gespeichert: " + wrapper);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // 3. Objekt wieder aus Datei lesen (deserialisieren)
        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(filename))) {

            Object obj = in.readObject();
            System.out.println("Objekt geladen:   " + obj);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
