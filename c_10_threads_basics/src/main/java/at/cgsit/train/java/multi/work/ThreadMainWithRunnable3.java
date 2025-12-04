package at.cgsit.train.java.multi.work;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class ThreadMainWithRunnable3 {

    static void main(String[] args) throws InterruptedException {

        // echten Sinn macht die Parallelisierung, wenn mehrere Dinge gleichzeitig macht:

        new Thread(() -> {
            System.out.println("df");
            System.out.println("dfdf");
        });

        Thread thread = new Thread(() -> new MyTaskKernLogik().executeLoop() );
        Thread thread2 = new Thread(() -> new MyTaskKernLogik().executeLoop() );
        Thread thread3 = new Thread(() -> new MyTaskKernLogik().executeLoop() );

        // lambda version mit Runnable variable
        Runnable job = () -> System.out.println("Task läuft yet another");
        new Thread(job).start();

        // parallel weg starten als thread
        thread.start();
        thread2.start();
        thread3.start();

        // warten auf fertigstellung
        thread.join();
        thread2.join();
        thread3.join();


        LocalDateTime localDateTime = Instant.now().atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println("main methode nach Thread aufruf: " + localDateTime.toString());
    }

}
