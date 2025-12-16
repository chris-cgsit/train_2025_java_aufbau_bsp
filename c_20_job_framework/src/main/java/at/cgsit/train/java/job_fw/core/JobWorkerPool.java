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

package at.cgsit.train.java.job_fw.core;

import at.cgsit.train.java.job_fw.queue.BlockingQueueAdapter;
import at.cgsit.train.java.job_fw.store.JobResultStore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

class JobWorkerPool {

    private final BlockingQueueAdapter<QueuedJob> queue;
    private final JobResultStore resultStore;
    private final int workerCount;
    private final boolean useVirtualThreads;

    private final AtomicBoolean running = new AtomicBoolean(false);
    private final List<Thread> workers = new ArrayList<>();

    JobWorkerPool(BlockingQueueAdapter<QueuedJob> queue,
                  JobResultStore resultStore,
                  int workerCount,
                  boolean useVirtualThreads) {
        this.queue = queue;
        this.resultStore = resultStore;
        this.workerCount = workerCount;
        this.useVirtualThreads = useVirtualThreads;
    }

    void start() {
        if (!running.compareAndSet(false, true)) {
            return; // schon gestartet
        }

        for (int i = 0; i < workerCount; i++) {
            JobWorker worker = new JobWorker(queue, resultStore, running);

            Thread t = useVirtualThreads
                    ? Thread.ofVirtual().name("job-worker-vt-" + i).unstarted(worker)
                    : Thread.ofPlatform().name("job-worker-" + i).unstarted(worker);

            workers.add(t);
            t.start();
        }
    }

    void shutdown() {
        if (!running.compareAndSet(true, false)) {
            return; // schon gestoppt
        }

        for (Thread t : workers) {
            t.interrupt();
        }
        for (Thread t : workers) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
