package at.cgsit.train.java.multi.work;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunWithExecutorPoolMain {

    static void main(String[] args) {

        /*
         Executors ist wieder eine Klasse die mit statischenb Methoden als
         factory fungiert und uns hier einen Thread Pool von 3 Thread instanzen erzeugt

         ist auch verfügbar mit Thread factgory man müsste dann implements ThreadFactory implmentieren
         dann kann man die z.b. namen der threads im pool bestimmen
        */

        // ExecutorService excecSrvce = Executors.newFixedThreadPool(5);

        /*
         virtuelle threads werden von der JVM aus einem JVM Thread pool bedient
         der so groß ist wie java das bestimmt, auch aufgrund der Harware darunter
         limitierung auf x virutelle threads ist hier seitens platform unerwünscht
        */
        ExecutorService excecSrvce = Executors.newVirtualThreadPerTaskExecutor();

        for (int i = 0; i < 10; i++) {
            // execute methode is fire und forget. also start und laufen lassen
            excecSrvce.execute(() -> {
                String name = Thread.currentThread().getName();
                long threadId = Thread.currentThread().threadId();
                System.out.printf("runn with executor serverice: %s@%s \n", name, threadId );
            });
        }

        System.out.println("main methode after ececute");

    }

}
