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

package at.cgsit.train.java.threads.examples;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CountDownLatch;

/**
 * Demonstriert den Unterschied zwischen klassischen Plattform-Threads
 * und Virtual Threads in Java 21+.
 *
 * Achtung: Zahlen (ANZAHL_TASKS) so wählen, dass die Maschine es packt.
 */
public class ClassicVsVirtualDemo {

    private static final int ANZAHL_TASKS = 500;  // zum Spielen ändern

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== ClassicVsVirtualDemo ===");
        System.out.println("Tasks: " + ANZAHL_TASKS);
        System.out.println();

        runPlatformThreads();
        System.out.println();
        runVirtualThreads();
    }

    private static void runPlatformThreads() throws InterruptedException {
        System.out.println(">>> Plattform-Threads");

        CountDownLatch latch = new CountDownLatch(ANZAHL_TASKS);
        Instant start = Instant.now();

        for (int i = 0; i < ANZAHL_TASKS; i++) {
            Thread.ofPlatform().start(() -> {
                try {
                    simulateWork();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        Instant end = Instant.now();
        System.out.println("Plattform-Threads fertig in: " +
                Duration.between(start, end).toMillis() + " ms");
    }

    private static void runVirtualThreads() throws InterruptedException {
        System.out.println(">>> Virtual Threads");

        CountDownLatch latch = new CountDownLatch(ANZAHL_TASKS);
        Instant start = Instant.now();

        for (int i = 0; i < ANZAHL_TASKS; i++) {
            Thread.startVirtualThread(() -> {
                try {
                    simulateWork();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        Instant end = Instant.now();
        System.out.println("Virtual Threads fertig in: " +
                Duration.between(start, end).toMillis() + " ms");
    }

    /**
     * Simuliert eine kleine IO-ähnliche Arbeit.
     */
    private static void simulateWork() {
        try {
            // z.B. DB-Call, HTTP-Call etc.
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
