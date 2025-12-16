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

package at.cgsit.train.java.job_fw.demo;

import at.cgsit.train.java.job_fw.store.JobResultStore;
import at.cgsit.train.java.job_fw.api.SuccessResult;
import at.cgsit.train.java.job_fw.api.*;
import at.cgsit.train.java.job_fw.core.InMemoryJobFramework;
import at.cgsit.train.java.job_fw.store.InMemoryJobResultStore;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class JobFrameworkDemo {

  public static void main(String[] args) throws Exception {

    JobResultStore store = new InMemoryJobResultStore();

    // z.B. 10 Plätze in der Queue, 3 Worker, Virtual Threads aktiv
    try (JobFramework framework = new InMemoryJobFramework(
        10,
        3,
        true,
        store)) {

      List<JobTicket> tickets = new ArrayList<>();

      // 5 Demo-Jobs einwerfen
      for (int i = 1; i <= 5; i++) {
        int jobNo = i;
        Job job = new DemoJob("Job-" + jobNo, jobNo);
        JobTicket ticket = framework.submit(job);
        System.out.println(ts() + " submitted " + job.name() + " -> ticket " + ticket.id());
        tickets.add(ticket);
      }

      // Ergebnisse pollen (wie bei REST GET /jobs/{id})
      boolean allDone = false;
      while (!allDone) {
        allDone = true;

        for (JobTicket ticket : tickets) {
          JobStatus status = framework.getStatus(ticket);
          System.out.println(ts() + " ticket " + ticket.id() + " status = " + status);

          if (status == JobStatus.SUCCESS || status == JobStatus.FAILED) {
            JobResult result = framework.getResult(ticket);
            if (result instanceof SuccessResult sr) {
              System.out.println("  -> RESULT OK: " + sr.payload());
            } else if (result instanceof ErrorResult er) {
              System.out.println("  -> RESULT ERROR: " + er.message());
            }
          } else {
            allDone = false; // mindestens ein Job noch nicht fertig
          }
        }

        System.out.println("----");
        Thread.sleep(500);
      }

      System.out.println("Alle Jobs abgearbeitet.");
    }
  }

  private static String ts() {
    return "[" + LocalTime.now().withNano(0) + "]";
  }


}
