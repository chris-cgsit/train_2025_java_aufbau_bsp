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

package at.cgsit.train.java.threads.callable;

import java.util.concurrent.*;

public class CallableDemo {

    public static void main(String[] args) throws Exception {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Callable ist wie Runnable, aber mit Rückgabe und Exception-Unterstützung.
        Callable<String> task = () -> {
            Thread.sleep(1000);
            return "Berechnung fertig in " + Thread.currentThread().getName();
        };

        Future<String> future = executor.submit(task);

        System.out.println("Warte auf Ergebnis...");

        future.isDone()

        String result = future.get();  // blockiert

        System.out.println("Ergebnis: " + result);

        executor.shutdown();
    }
}
