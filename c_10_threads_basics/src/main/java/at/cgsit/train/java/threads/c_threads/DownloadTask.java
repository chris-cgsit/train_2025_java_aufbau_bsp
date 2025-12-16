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

package at.cgsit.train.java.threads.c_threads;

/**
 * Simuliert einen "Download":
 * - Läuft laufzeitSekunden lang
 * - Gibt alle paar Sekunden Fortschritt aus
 */
public class DownloadTask implements Runnable {

    private final int laufzeitSekunden;

    public DownloadTask(int laufzeitSekunden) {
        this.laufzeitSekunden = laufzeitSekunden;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.printf("[%s] Starte Download (%d Sekunden)...%n",
                name, laufzeitSekunden);

        for (int sek = 1; sek <= laufzeitSekunden; sek++) {
            try {
                Thread.sleep(1000); // 1 Sekunde
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.printf("[%s] Download unterbrochen!%n", name);
                return;
            }

            if (sek % 10 == 0 || sek == laufzeitSekunden) {
                System.out.printf("[%s] Download-Fortschritt: %d / %d Sekunden%n",
                        name, sek, laufzeitSekunden);
            }
        }

        System.out.printf("[%s] Download abgeschlossen.%n", name);
    }
}
