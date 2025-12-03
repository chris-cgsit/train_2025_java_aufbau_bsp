package at.cgsit.train.java.threads.synch;

import java.util.Random;

public class SyncCounterDemo {

    public static void main(String[] args) {

        SharedCounter counter = new SharedCounter();
        Random random = new Random();

        // Worker 1
        Runnable workerA = () -> {
            while (true) {
                counter.increment("Worker A");
                sleep(random.nextInt(500)); // bis zu 500ms
            }
        };

        // Worker 2
        Runnable workerB = () -> {
            while (true) {
                counter.increment("Worker B");
                sleep(random.nextInt(500));
            }
        };

        // Threads starten
        new Thread(workerA).start();
        new Thread(workerB).start();
    }

    // Methode um schlafen kompakt zu halten
    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
