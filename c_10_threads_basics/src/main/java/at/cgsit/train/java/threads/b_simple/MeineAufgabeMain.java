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

package at.cgsit.train.java.threads.b_simple;

/**
 * Demo-Aufgabe für klassische Threads (Java Runnable).
 * Läuft ca. 2–3 Minuten und zeigt Fortschritt.
 */
public class MeineAufgabeMain implements Runnable {

    private final int laufzeitSekunden;

    public MeineAufgabeMain(int laufzeitSekunden) {
        this.laufzeitSekunden = laufzeitSekunden;
    }


  // Direkt startbar:
  public static void main(String[] args) {
    // 2–3 Minuten = 120–180 Sekunden → hier: 150 Sekunden (2.5 Minuten)
    MeineAufgabeMain aufgabe = new MeineAufgabeMain(15);

    Thread t = new Thread(aufgabe, "MeineAufgabe-Thread");
    t.setDaemon(true);
    t.start();

    try {
      t.join(); // auf Abschluss warten (optional)
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    System.out.println("Main-Thread: Aufgabe vollständig beendet.");
  }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.printf("[%s] Starte Aufgabe (%d Sekunden)...%n", name, laufzeitSekunden);

        long startMillis = System.currentTimeMillis();

        for (int sek = 1; sek <= laufzeitSekunden; sek++) {
            try {
                Thread.sleep(1000); // 1 Sekunde warten
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.printf("[%s] Abgebrochen!%n", name);
                return;
            }

            System.out.printf("[%s] Fortschritt: %d / %d Sekunden%n",
                    name, sek, laufzeitSekunden);
        }

        long duration = System.currentTimeMillis() - startMillis;
        System.out.printf("[%s] Aufgabe abgeschlossen! Gesamtdauer: %.2f Sekunden%n",
                name, duration / 1000.0);
    }

}
