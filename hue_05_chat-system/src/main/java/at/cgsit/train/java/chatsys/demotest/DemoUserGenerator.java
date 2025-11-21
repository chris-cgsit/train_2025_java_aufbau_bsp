package at.cgsit.train.java.chatsys.demotest;

import at.cgsit.train.java.chatsys.model.ChatUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility-Klasse zur Erzeugung von Demo-Usern.
 * Erstellt automatisch Benutzer A-Z mit typischen Vornamen.
 */
public class DemoUserGenerator {

    private static final String[] DEMO_NAMES = {
            "Alice", "Bob", "Carla", "David", "Eva", "Frank", "Gina", "Hans",
            "Ines", "Jonas", "Klara", "Leo", "Mara", "Nils", "Olivia", "Paul",
            "Quentin", "Rita", "Stefan", "Tina", "Uwe", "Vera", "Walter", "Xenia",
            "Yvonne", "Zoe"
    };

    /**
     * Erzeugt eine Liste von Demo-Usern mit alphabetisch sortierten Namen.
     */
    public static List<ChatUser> generateAll() {
        List<ChatUser> users = new ArrayList<>();
        for (String name : DEMO_NAMES) {
            String email = name.toLowerCase() + "@demo.local";
            users.add(new ChatUser(name, email));
        }
        return users;
    }

    /**
     * Liefert eine definierte Anzahl Demo-User (z.B. für kleine Tests).
     */
    public static List<ChatUser> generate(int count) {
        List<ChatUser> all = generateAll();
        if (count >= all.size()) return all;
        return all.subList(0, count);
    }
}
