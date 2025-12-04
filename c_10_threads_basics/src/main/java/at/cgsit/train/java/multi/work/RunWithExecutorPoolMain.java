package at.cgsit.train.java.multi.work;

import java.util.Random;
import java.util.concurrent.*;

public class RunWithExecutorPoolMain {

    static void main(String[] args) throws InterruptedException {

        /*
         Executors ist wieder eine Klasse die mit statischenb Methoden als
         factory fungiert und uns hier einen Thread Pool von 3 Thread instanzen erzeugt

         ist auch verfügbar mit Thread factgory man müsste dann implements ThreadFactory implmentieren
         dann kann man die z.b. namen der threads im pool bestimmen
        */

        ExecutorService excecSrvce = Executors.newFixedThreadPool(5);

        /*
         virtuelle threads werden von der JVM aus einem JVM Thread pool bedient
         der so groß ist wie java das bestimmt, auch aufgrund der Harware darunter
         limitierung auf x virutelle threads ist hier seitens platform unerwünscht
        */
        // ExecutorService excecSrvce = Executors.newVirtualThreadPerTaskExecutor();

        for (int i = 0; i < 50; i++) {
            // execute methode is fire und forget. also start und laufen lassen
            excecSrvce.execute(() -> {
                String name = Thread.currentThread().getName();
                long threadId = Thread.currentThread().threadId();
                try {
                    Random random = new Random();
                    int randomSleepDuration = random.nextInt(3000) + 1000;
                    Thread.sleep(randomSleepDuration);
                } catch (InterruptedException e) { }
                System.out.printf("run with executor servíce: %s@%s \n", name, threadId );
            });
        }

        ThreadPoolExecutor pool = (ThreadPoolExecutor) excecSrvce;
        // Die Methode, die die Anzahl der aktiv laufenden Threads liefert
        System.out.println("Aktive Threads: " + pool.getActiveCount());

        excecSrvce.shutdown();

        try {
            // nach dem shutdown darf man keine elemente / tasks mehr in den pool zur verarbeitung einschleusen
            excecSrvce.execute(() -> System.out.println("after shutdown"));
        } catch (RejectedExecutionException ex) {
            System.out.println( "as exprected" + ex.getMessage());
        }

        while (true) {
            BlockingQueue<Runnable> queue = pool.getQueue();
            System.out.printf("tasks noch offen zur Abarbeitung: %s \n", queue.size());
            Thread.sleep(250);
            if(queue.isEmpty()) {
                break;
            }
        }

        // Blocks until all tasks have completed execution after a shutdown request, or the timeout occurs
        excecSrvce.awaitTermination(1, TimeUnit.HOURS);

        System.out.println("main methode after ececute");

    }

}
