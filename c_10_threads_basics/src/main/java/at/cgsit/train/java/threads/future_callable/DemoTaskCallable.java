package at.cgsit.train.java.threads.future_callable;

import java.util.concurrent.Callable;

public class DemoTaskCallable implements Callable<String> {

    @Override
    public String call() {
        sleep(1000);
        System.out.println("Task 1 fertig");
        return "Ergebnis Task 1";
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
