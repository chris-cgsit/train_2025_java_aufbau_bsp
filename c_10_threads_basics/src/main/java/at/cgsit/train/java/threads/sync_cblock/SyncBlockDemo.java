package at.cgsit.train.java.threads.sync_cblock;

import java.util.Random;

public class SyncBlockDemo {

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
    private final Object lock = new Object();  // eigenes Lock-Objekt

    public void increment(String who) {

        synchronized (lock) {   // kritischer Abschnitt
            value++;
            System.out.println(who + " -> Counter: " + value);
        }
    }
}
