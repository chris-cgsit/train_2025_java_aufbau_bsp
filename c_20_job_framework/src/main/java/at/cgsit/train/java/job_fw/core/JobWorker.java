package at.cgsit.train.java.job_fw.core;

import at.cgsit.train.java.job_fw.api.ErrorResult;
import at.cgsit.train.java.job_fw.api.JobResult;
import at.cgsit.train.java.job_fw.api.JobStatus;
import at.cgsit.train.java.job_fw.store.JobResultStore;
import at.cgsit.train.java.job_fw.queue.BlockingQueueAdapter;

import java.util.concurrent.atomic.AtomicBoolean;

class JobWorker implements Runnable {

    private final BlockingQueueAdapter<QueuedJob> queue;
    private final JobResultStore resultStore;
    private final AtomicBoolean running;

    JobWorker(BlockingQueueAdapter<QueuedJob> queue,
              JobResultStore resultStore,
              AtomicBoolean running) {
        this.queue = queue;
        this.resultStore = resultStore;
        this.running = running;
    }

    @Override
    public void run() {
        while (running.get() || !queue.isEmpty()) {
            try {
                QueuedJob qj = queue.poll(500);
                if (qj == null) {
                    continue; // Timeout – erneut versuchen
                }

                resultStore.markRunning(qj.ticketId());

                JobResult result;
                try {
                    result = qj.job().execute();
                    if (result == null) {
                        // Fallback, falls der Job kein Result zurückgibt
                        result = new at.cgsit.train.java.job_fw.api.SuccessResult(null);
                    }
                } catch (Exception e) {
                    result = new ErrorResult(e.getMessage(), e);
                }

                resultStore.storeResult(qj.ticketId(), result);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break; // Worker beenden
            }
        }
    }
}
