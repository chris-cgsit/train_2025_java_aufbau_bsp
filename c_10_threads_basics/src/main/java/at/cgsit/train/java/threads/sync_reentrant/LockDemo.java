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

package at.cgsit.train.java.threads.sync_reentrant;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {

    public static void main(String[] args) {

        SharedCounter counter = new SharedCounter();
        Random random = new Random();

        Runnable workerA = () -> {
            while (true) {
                counter.increment("Worker A");
                sleep(random.nextInt(200));
            }
        };

        Runnable workerB = () -> {
            while (true) {
                counter.increment("Worker B");
                sleep(random.nextInt(200));
            }
        };

        new Thread(workerA).start();
        new Thread(workerB).start();
    }

    private static void sleep(long ms) {
        try { Thread.sleep(ms); }
        catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}

class SharedCounter {

    private int value = 0;
    private final Lock lock = new ReentrantLock();

    public void increment(String who) {

        lock.lock();         // Lock betreten
        try {
            value++;
            System.out.println(who + " -> Counter: " + value);
        } finally {
            lock.unlock();   // WICHTIG: Immer im finally!
        }
    }
}
