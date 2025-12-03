package at.cgsit.train.java.threads.futures;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureDemo {

    public static void main(String[] args) {

         // CompletableFuture-Version
         // braucht keinen eigenen ExecutorService (nutzt intern ForkJoinPool.commonPool)
         // .join() statt get()  das ist kompakter

        System.out.println("Starte parallele Tasks ...");

        // Drei parallele Arbeiten
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            sleep(1000);
            System.out.println("Task 1 fertig");
        });

        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
            sleep(1500);
            System.out.println("Task 2 fertig");
        });

        CompletableFuture<Void> future3 = CompletableFuture.runAsync(() -> {
            sleep(500);
            System.out.println("Task 3 fertig");
        });

        // Auf alle warten mit .join
        CompletableFuture.allOf(future1, future2, future3).join();

        System.out.println("Alle Tasks erledigt!");
    }

    private static void sleep(long ms) {
        try {
            TimeUnit.MILLISECONDS.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
