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

package at.cgsit.train.java.threads.sync_atomic;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounterDemo {

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
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// Modern: Keine Race Conditions ohne synchronized
class SharedCounter {

    private final AtomicInteger value = new AtomicInteger(0);

    public void increment(String who) {

        int newValue = value.incrementAndGet(); // atomar!

        System.out.println(who + " -> Counter: " + newValue);

    }
}
