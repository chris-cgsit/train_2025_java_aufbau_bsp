package at.cgsit.train.java.job_fw.core;


import at.cgsit.train.java.job_fw.api.Job;

import java.util.UUID;

public class QueuedJob {

    private final UUID ticketId;
    private final Job job;

    public QueuedJob(UUID ticketId, Job job) {
        this.ticketId = ticketId;
        this.job = job;
    }

    public UUID ticketId() {
        return ticketId;
    }

    public Job job() {
        return job;
    }
}
