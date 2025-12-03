package at.cgsit.train.java.job_fw.store;

import at.cgsit.train.java.job_fw.api.JobResult;
import at.cgsit.train.java.job_fw.api.JobStatus;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * In-Memory Implementierung – später leicht durch eine DB-Variante ersetzbar.
 * (z.B. DbJobResultStore mit JPA/JDBC oder DynamoDB)
 */
public class InMemoryJobResultStore implements JobResultStore {

  private final ConcurrentMap<UUID, JobStatus> statusMap = new ConcurrentHashMap<>();
  private final ConcurrentMap<UUID, JobResult> resultMap = new ConcurrentHashMap<>();

  @Override
  public void initJob(UUID ticketId) {
    statusMap.put(ticketId, JobStatus.PENDING);
  }

  @Override
  public void markRunning(UUID ticketId) {
    statusMap.put(ticketId, JobStatus.RUNNING);
  }

  @Override
  public void storeResult(UUID ticketId, JobResult result) {
    resultMap.put(ticketId, result);
    statusMap.put(ticketId, result.status());
  }

  @Override
  public JobStatus getStatus(UUID ticketId) {
    return statusMap.getOrDefault(ticketId, null);
  }

  @Override
  public JobResult getResult(UUID ticketId) {
    return resultMap.get(ticketId);
  }
}
