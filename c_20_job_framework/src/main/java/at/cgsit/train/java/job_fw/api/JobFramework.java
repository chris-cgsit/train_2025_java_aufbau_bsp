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

package at.cgsit.train.java.job_fw.api;

public interface JobFramework extends AutoCloseable {

  JobTicket submit(Job job);              // Job einwerfen, Ticket zurück

  JobStatus getStatus(JobTicket ticket);  // Status abfragen

  JobResult getResult(JobTicket ticket);  // Ergebnis abfragen (kann null sein)

  @Override
  void close();
}
