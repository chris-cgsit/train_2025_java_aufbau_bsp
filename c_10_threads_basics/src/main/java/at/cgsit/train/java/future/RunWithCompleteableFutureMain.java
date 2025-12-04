package at.cgsit.train.java.future;

import java.util.concurrent.CompletableFuture;

public class RunWithCompleteableFutureMain {

    static void main(String[] args) {

        // run async führ das runnable aus, das runnable execute returniert keinen Wert
        CompletableFuture<Void> cf = CompletableFuture.runAsync(new DemoRun1());
        cf.join();
        System.out.println("main fertig");

        // unterstützt das warten auf mehrere elemente
        // CompletableFuture<?>... cfs
        // CompletableFuture.allOf( );

    }


}
