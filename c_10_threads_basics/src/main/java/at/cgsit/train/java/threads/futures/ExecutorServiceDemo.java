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

package at.cgsit.train.java.threads.futures;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorServiceDemo {

    public static void main(String[] args) {

      // ExecutorService-Version
      // man kontrolliert den Pool selbst
      //  submit(...) gibt klassische Future<?> zurück
      // future.get() blockiert bis der Task fertig ist
      // executor.shutdown() selbst ausführen !!


        System.out.println("Starte parallele Tasks ...");

        // ExecutorService ist ein Abstraktions-Layer über der direkten Thread-Erzeugung.
        // Ein ExecutorService erzeugt Threads einmal und benutzt sie danach immer wieder.
        // Executor mit 3 Threads (kann auch Virtual Threads sein)
        // ExecutorService executor = Executors.newFixedThreadPool(3);
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        List<Future<?>> futures = new ArrayList<>();

        // Drei parallele Arbeiten
        Future<?> submitted = executor.submit(new DemoRun1());
        futures.add(submitted);

        // anonyme klasse
      Future<?> submitted2 = executor.submit(new Runnable() {
        @Override
        public void run() {
          sleep(1500);
          System.out.println("Task 2 fertig");
        }
      });
      futures.add(submitted2);

      futures.add(executor.submit(() -> {
            sleep(500);
            System.out.println("Task 3 fertig");
        }));

        // Auf alle warten (wie CompletableFuture.allOf)
        for (Future<?> f : futures) {
            try {
                f.get();  // blockiert bis der Task fertig ist
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();

        System.out.println("Alle Tasks erledigt!");
    }

    private static void sleep(long ms) {
        try {
            TimeUnit.MILLISECONDS.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
