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

package at.cgsit.train.java.threads.future_completeble;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {

        // Parallele Arbeiten mit Rückgabewert
        CompletableFuture<String> future1 =
                CompletableFuture.supplyAsync(DemoTasks::task1);

        CompletableFuture<String> future2 =
                CompletableFuture.supplyAsync(DemoTasks::task2);

        CompletableFuture<Integer> future3 =
                CompletableFuture.supplyAsync(DemoTasks::task3);

        System.out.println("Warte auf Ergebnisse...");

        // Warten auf alle Futures
        CompletableFuture<Void> all =
                CompletableFuture.allOf(future1, future2, future3);

        // .join() blockiert wie Future.get(), wirft aber unchecked exceptions
        all.join();

        // Ergebnisse abholen
        String r1 = future1.join();
        String r2 = future2.join();
        Integer r3 = future3.join();

        System.out.println("Alle Ergebnisse:");
        System.out.println(r1);
        System.out.println(r2);
        System.out.println(r3);
    }
}
