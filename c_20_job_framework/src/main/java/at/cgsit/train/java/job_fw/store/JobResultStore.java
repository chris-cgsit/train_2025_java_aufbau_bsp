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

package at.cgsit.train.java.job_fw.store;

import at.cgsit.train.java.job_fw.api.JobResult;
import at.cgsit.train.java.job_fw.api.JobStatus;

import java.util.UUID;

public interface JobResultStore {

  void initJob(UUID ticketId);

  void markRunning(UUID ticketId);

  void storeResult(UUID ticketId, JobResult result);

  JobStatus getStatus(UUID ticketId);

  JobResult getResult(UUID ticketId);
}
