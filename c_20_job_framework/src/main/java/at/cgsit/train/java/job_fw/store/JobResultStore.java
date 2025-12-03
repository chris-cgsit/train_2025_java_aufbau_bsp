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
