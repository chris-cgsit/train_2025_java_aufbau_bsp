package at.cgsit.train.java.threads.workqueue;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * WorkerTask:
 * - Läuft als Runnable in einem (virtuellen) Thread
 * - Holt Jobs aus der Queue
 * - Verarbeitet sie
 * - Schreibt Result-Einträge in die Ergebnisliste
 */
public class WorkerTask implements Runnable {

    private final BlockingQueue<Job> queue;
    private final List<Result> results;
    private final String workerName;
    private final CountDownLatch workersDone;

    public WorkerTask(BlockingQueue<Job> queue,
                      List<Result> results,
                      String workerName,
                      CountDownLatch workersDone) {
        this.queue = queue;
        this.results = results;
        this.workerName = workerName;
        this.workersDone = workersDone;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Warten auf Job, aber nicht unendlich → Demo-Exit
                Job job = queue.poll(200, TimeUnit.MILLISECONDS);
                if (job == null) {
                    // Keine Jobs mehr → Worker beendet sich
                    break;
                }

                long before = System.currentTimeMillis();
                processJob(job);
                long after = System.currentTimeMillis();

                results.add(new Result(job.id(), workerName, after - before));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            workersDone.countDown();
        }
    }

    private void processJob(Job job) {
        try {
            System.out.printf("[%s] Verarbeite %s (%s)%n",
                    workerName, job.id(), job.payload());
            // Simulierte Arbeit (z.B. IO, DB, HTTP ...)
            Thread.sleep(100 + (long) (Math.random() * 200));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
