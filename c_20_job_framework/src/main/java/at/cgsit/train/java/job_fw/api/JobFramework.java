package at.cgsit.train.java.job_fw.api;

public interface JobFramework extends AutoCloseable {

  JobTicket submit(Job job);              // Job einwerfen, Ticket zurück

  JobStatus getStatus(JobTicket ticket);  // Status abfragen

  JobResult getResult(JobTicket ticket);  // Ergebnis abfragen (kann null sein)

  @Override
  void close();
}
