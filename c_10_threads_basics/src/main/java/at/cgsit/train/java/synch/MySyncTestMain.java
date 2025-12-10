package at.cgsit.train.java.synch;

import java.util.Random;

public class MySyncTestMain {

    static void main(String[] args) {

        SharedCounter2 myCouner = new SharedCounter2();
        Random random = new Random();

        // Worker 1 ...
        Runnable workerA = () -> {
            while (true) {
                myCouner.increment("Worker A");
                sleep(random.nextInt(500)); // bis zu 500ms
            }
        };

        // Worker 2
        Runnable workerB = () -> {
            while (true) {
                myCouner.increment("Worker B");
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
