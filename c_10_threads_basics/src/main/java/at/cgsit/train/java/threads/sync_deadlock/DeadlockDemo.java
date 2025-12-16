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

package at.cgsit.train.java.threads.sync_deadlock;

public class DeadlockDemo {

    public static void main(String[] args) {

        final Object lockA = new Object();
        final Object lockB = new Object();

        // Thread 1: erst A, dann B
        Runnable worker1 = () -> {
            while (true) {
                System.out.println("Worker 1: versucht Lock A");
                synchronized (lockA) {
                    System.out.println("Worker 1: hat Lock A");
                    sleep(100);

                    System.out.println("Worker 1: versucht Lock B");
                    synchronized (lockB) {
                        System.out.println("Worker 1: hat Lock B");
                    }
                }
            }
        };

        // Thread 2: erst B, dann A (umgekehrte Reihenfolge!)
        Runnable worker2 = () -> {
            while (true) {
                System.out.println("Worker 2: versucht Lock B");
                synchronized (lockB) {
                    System.out.println("Worker 2: hat Lock B");
                    sleep(100);

                    System.out.println("Worker 2: versucht Lock A");
                    synchronized (lockA) {
                        System.out.println("Worker 2: hat Lock A");
                    }
                }
            }
        };

        new Thread(worker1, "Worker-1-Thread").start();
        new Thread(worker2, "Worker-2-Thread").start();
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
