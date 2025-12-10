package at.cgsit.train.java.threads.c_threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Moderne Version des ThreadCoordinatorDemo:
 * - Verwendet Virtual Threads statt klassischer Threads.
 * - Gleiche Runnable-Tasks: DownloadTask, BackupTask, LoggingTask.
 *
 * Zeigt:
 *   - Aufgaben-orientierte Programmierung
 *   - Virtual Threads via Executor
 */
public class VirtualThreadCoordinatorDemo {

    public static void main(String[] args) {
        System.out.println("=== VirtualThreadCoordinatorDemo (Runnable + Virtual Threads) ===");

        // Gleiche Laufzeiten wie im klassischen Beispiel
        Runnable downloadTask = new DownloadTask(9);
        Runnable backupTask   = new BackupTask(9);
        Runnable loggingTask  = new LoggingTask(12);

        // Executor erstellt für jede Aufgabe einen eigenen Virtual Thread
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {

            System.out.println("Main: Starte Tasks als Virtual Threads...");

            List<Future<?>> futures = new ArrayList<>();

            Future<?> downloadFuture = executor.submit(downloadTask);
            futures.add(downloadFuture);

            futures.add(executor.submit(backupTask));
            futures.add(executor.submit(loggingTask));

            // Auf Abschluss aller Tasks warten
            for (Future<?> future : futures) {
                try {
                    Object o = future.get();// blockiert bis Task fertig ist
                    System.out.println("object von runnable retuned" + o);

                } catch (Exception e) {
                    System.out.println("Main: Fehler in einem Task: " + e.getMessage());
                    e.printStackTrace(System.out);
                }
            }
        }

        System.out.println("Main: Alle Virtual-Thread-Tasks wurden beendet. Programmende.");
    }
}
