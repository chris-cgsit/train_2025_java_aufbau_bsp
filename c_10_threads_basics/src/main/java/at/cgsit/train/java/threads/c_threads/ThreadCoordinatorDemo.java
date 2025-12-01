package at.cgsit.train.java.threads.c_threads;

/**
 * Startet drei verschiedene Runnable-Tasks in eigenen Threads:
 * - DownloadTask
 * - BackupTask
 * - LoggingTask
 *
 * Zeigt klassisches Thread-Handling mit Runnable:
 *   new Thread(runnable, "Name").start();
 */
public class ThreadCoordinatorDemo {

    public static void main(String[] args) {
        System.out.println("=== ThreadCoordinatorDemo (Runnable-Version) ===");

        // 2–3 Minuten Gesamtlaufzeit:
        // Download & Backup je ~90s, Logging ~120s
        Runnable downloadTask = new DownloadTask(90);
        Runnable backupTask   = new BackupTask(90);
        Runnable loggingTask  = new LoggingTask(120);

        Thread downloadThread = new Thread(downloadTask, "Download-Thread");
        Thread backupThread   = new Thread(backupTask, "Backup-Thread");
        Thread loggingThread  = new Thread(loggingTask, "Logging-Thread");

        System.out.println("Main: Starte Threads...");

        downloadThread.start();
        backupThread.start();
        loggingThread.start();

        // Auf alle Threads warten (join)
        try {
            downloadThread.join();
            backupThread.join();
            loggingThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Main: Unterbrochen beim Warten auf Threads.");
        }

        System.out.println("Main: Alle Aufgaben wurden beendet. Programmende.");
    }
}
