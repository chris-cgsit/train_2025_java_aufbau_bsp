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

import at.cgsit.train.java.job_fw.api.*;
import at.cgsit.train.java.job_fw.queue.BlockingQueueAdapter;
import at.cgsit.train.java.job_fw.queue.InMemoryBlockingQueue;
import at.cgsit.train.java.job_fw.store.JobResultStore;

import java.util.Objects;
import java.util.UUID;

public class InMemoryJobFramework implements JobFramework {

    private final BlockingQueueAdapter<QueuedJob> queue;
    private final JobResultStore resultStore;
    private final JobWorkerPool workerPool;

    public InMemoryJobFramework(int queueCapacity,
                                int workerCount,
                                boolean useVirtualThreads,
                                JobResultStore resultStore) {

        this.queue = new InMemoryBlockingQueue<>(queueCapacity);
        this.resultStore = Objects.requireNonNull(resultStore, "resultStore must not be null");
        this.workerPool = new JobWorkerPool(queue, resultStore, workerCount, useVirtualThreads);

        this.workerPool.start();
    }

    @Override
    public JobTicket submit(Job job) {
        Objects.requireNonNull(job, "job must not be null");

        UUID ticketId = UUID.randomUUID();
        resultStore.initJob(ticketId);

        try {
            queue.put(new QueuedJob(ticketId, job));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted while submitting job", e);
        }

        return new JobTicket(ticketId);
    }

    @Override
    public JobStatus getStatus(JobTicket ticket) {
        return resultStore.getStatus(ticket.id());
    }

    @Override
    public JobResult getResult(JobTicket ticket) {
        return resultStore.getResult(ticket.id());
    }

    @Override
    public void close() {
        workerPool.shutdown();
    }
}
