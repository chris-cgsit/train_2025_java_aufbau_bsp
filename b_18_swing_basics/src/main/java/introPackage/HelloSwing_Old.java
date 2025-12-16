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

package introPackage;

import javax.swing.*;

public class HelloSwing_Old {
    public static void main(String[] args) {
        // Ein Objekt für unser Hauptfenster erzeugen
        JFrame hauptfenster = new JFrame("Erstes Swing-Programm");
        hauptfenster.setSize(300, 400);

        // TODO: Steuerelemente (=Components hinzufügen)
        // ...

        // beim Klick auf Schließen soll das Fenster zerstört werden
        hauptfenster.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);


        // und anzeigen
        hauptfenster.setVisible(true);
    }
}
