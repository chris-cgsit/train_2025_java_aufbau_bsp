package at.cgsit.train.java.job_fw.api;

public record ErrorResult(String message, Throwable error) implements JobResult {
  @Override
  public JobStatus status() {
    return JobStatus.FAILED;
  }
}
