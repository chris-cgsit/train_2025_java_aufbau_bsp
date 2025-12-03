package at.cgsit.train.java.job_fw.api;
public interface Job {
    String name();                 // für Logging / Monitoring
    JobResult execute() throws Exception;
}
