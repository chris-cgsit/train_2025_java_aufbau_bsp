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

package at.cgsit.train.java.threads.future_callable;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws Exception {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        Future<String> future1 = executor.submit(new DemoTaskCallable());

        Future<String> future2 = executor.submit(() -> {
            Thread.sleep(1500);
            System.out.println("Task 2 fertig");
            return "Ergebnis Task 2";
        });
        Future<Integer> future3 = executor.submit(() -> {
            Thread.sleep(1200);
            System.out.println("Task 3 fertig");
            return 42;  // beliebiges Ergebnis
        });

        System.out.println("Warte auf Ergebnisse...");

        // Future.get() blockiert und holt das Ergebnis
        String r1 = future1.get();
        String r2 = future2.get();
        Integer r3 = future3.get();

        System.out.println("Alle Ergebnisse:");
        System.out.println(r1);
        System.out.println(r2);
        System.out.println(r3);

        executor.shutdown();
    }
}
