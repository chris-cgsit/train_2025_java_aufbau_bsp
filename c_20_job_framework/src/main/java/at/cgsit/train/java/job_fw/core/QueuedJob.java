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
