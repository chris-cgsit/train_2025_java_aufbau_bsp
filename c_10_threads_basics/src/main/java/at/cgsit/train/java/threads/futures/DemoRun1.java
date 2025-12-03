package at.cgsit.train.java.threads.futures;

public class DemoRun1 implements Runnable {

    @Override
    public void run() {
        sleep(1000);
        System.out.println("Task 1 fertig");
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
