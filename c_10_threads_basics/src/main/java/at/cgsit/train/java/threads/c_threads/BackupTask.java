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
 * Simuliert einen "Backup"-Prozess:
 * - Läuft laufzeitSekunden lang
 * - Meldet sich z.B. alle 15 Sekunden
 */
public class BackupTask implements Runnable {

    private final int laufzeitSekunden;

    public BackupTask(int laufzeitSekunden) {
        this.laufzeitSekunden = laufzeitSekunden;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.printf("[%s] Starte Backup (%d Sekunden)...%n",
                name, laufzeitSekunden);

        for (int sek = 1; sek <= laufzeitSekunden; sek++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.printf("[%s] Backup unterbrochen!%n", name);
                return;
            }

            if (sek % 15 == 0 || sek == laufzeitSekunden) {
                System.out.printf("[%s] Backup-Fortschritt: %d / %d Sekunden%n",
                        name, sek, laufzeitSekunden);
            }
        }

        System.out.printf("[%s] Backup abgeschlossen.%n", name);
    }
}
