package at.cgsit.train.java.threads.c_threads;

/**
 * Simuliert einen "Logging-Task":
 * - Läuft etwas länger als die anderen
 * - Schreibt in regelmäßigen Abständen Log-Meldungen
 */
public class LoggingTask implements Runnable {

    private final int laufzeitSekunden;

    public LoggingTask(int laufzeitSekunden) {
        this.laufzeitSekunden = laufzeitSekunden;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.printf("[%s] Starte Logging (%d Sekunden)...%n",
                name, laufzeitSekunden);

        for (int sek = 1; sek <= laufzeitSekunden; sek++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.printf("[%s] Logging unterbrochen!%n", name);
                return;
            }

            // Logging jede 5 Sekunden
            if (sek % 5 == 0 || sek == 1 || sek == laufzeitSekunden) {
                System.out.printf("[%s] Log-Eintrag bei Sekunde %d / %d%n",
                        name, sek, laufzeitSekunden);
            }
        }

        System.out.printf("[%s] Logging beendet.%n", name);
    }
}
