package at.cgsit.train.java.future;

import at.cgsit.train.java.threads.futures.DemoRun1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RunWithFutreMain {

    static void main(String[] args)   {

        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        List<Future<?>> futures = new ArrayList<>();

        // execute ist fire and forget
        // executor.execute(new at.cgsit.train.java.future.DemoRun1());
        // submit ermöglicht es mit einem Future also zukünfitigen ergebnis auf das ergenis zu warten

        for (int i = 0; i < 20; i++) {
            Future<?> submitted = executor.submit(new DemoRun1());
            futures.add(submitted);
        }

        // warten bis alle Tasks fertig sind
        for (Future<?> f : futures) {
            try {
                f.get();
            } catch (Exception e) { }
        }

        /*
        try {
            // mit get können wir auf das ergebnis warten
            Object o = submitted.get();
            // bei runnables ist der return wert void also kein Rückgabe also hier NULL
        } catch (Exception e) {
        }
         */
        System.out.println("Demo Run 1 Main methode: fertig");

    }

}
