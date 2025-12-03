package at.cgsit.train.java.job_fw.api;

public record SuccessResult(Object payload) implements JobResult {
  @Override
  public JobStatus status() {
    return JobStatus.SUCCESS;
  }
}
