package at.cgsit.train.java.objser;

import at.cgsit.train.java.sharedObj.ChatMessage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileMultipleObjectLIstDemo {

    public static void main(String[] args) {

        String filename = "message.bin";

        // 1. Objekte erzeugen
        ChatMessage msg = new ChatMessage("Wifi Kurs", "Hallo eins!");
        ChatMessage msg2 = new ChatMessage("Wifi Kurs 2 ", "Hallo zwei!");
        ChatMessage msg3 = new ChatMessage("Wifi Kurs 3", "Hallo drei!");
        List<ChatMessage> list = new ArrayList<ChatMessage>();
        list.add(msg);
        list.add(msg2);
        list.add(msg3);


        // 2. Objekt serialisiert in Datei schreiben
        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(filename))) {

               out.writeObject(list);

            } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }


        // 3. Objekt wieder aus Datei lesen (deserialisieren)
        List<ChatMessage> listFromFile = null;
        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(filename))) {

            listFromFile = (List<ChatMessage>) in.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        }

        listFromFile.forEach(chatMessage -> System.out.println(chatMessage.toString()));

    }
}
