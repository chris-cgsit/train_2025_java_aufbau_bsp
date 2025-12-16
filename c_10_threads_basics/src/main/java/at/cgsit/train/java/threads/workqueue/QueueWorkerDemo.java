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

package at.cgsit.train.java.threads.workqueue;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.*;

/**
 * Organizer-Klasse:
 * - Erzeugt Jobs
 * - Legt sie in die Queue
 * - Startet WorkerTasks (Virtual Threads)
 * - Wartet auf Abschluss und gibt Ergebnisse aus
 */
public class QueueWorkerDemo {

    private static final int ANZAHL_JOBS = 20;
    private static final int ANZAHL_WORKER = 4;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== QueueWorkerDemo (Organizer + WorkerTask) ===");

        BlockingQueue<Job> queue = new ArrayBlockingQueue<>(ANZAHL_JOBS);
        List<Result> results = new CopyOnWriteArrayList<>();

        // Jobs erzeugen (Producer)
        for (int i = 0; i < ANZAHL_JOBS; i++) {
            // add führt zu IllegalStateException: Queue full wenn zuviel
            queue.add(new Job("job-" + i, "payload-" + i));
            // put wartet bis
            // Wenn die Queue voll ist → der aufrufende Thread blockiert, bis wieder Platz ist
            // queue.put(job);

            // non-blocking, mit Rückgabewert
            // boolean ok = queue.offer(job);
            // wenn platz ist ok sonst false

        }

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            CountDownLatch workersDone = new CountDownLatch(ANZAHL_WORKER);
            Instant start = Instant.now();

            // WorkerTasks starten
            for (int w = 0; w < ANZAHL_WORKER; w++) {
                String workerName = "worker-" + w;
                executor.submit(new WorkerTask(queue, results, workerName, workersDone));
            }

            // Warten, bis alle Worker fertig sind
            workersDone.await();
            Instant end = Instant.now();

            System.out.println("Alle Jobs verarbeitet in " +
                    Duration.between(start, end).toMillis() + " ms");
            System.out.println();

            results.stream()
                    .sorted((r1, r2) -> r1.jobId().compareTo(r2.jobId()))
                    .forEach(r -> System.out.printf(
                            "%s erledigt von %s in %d ms%n",
                            r.jobId(), r.workerName(), r.durationMs()
                    ));
        }
    }
}
