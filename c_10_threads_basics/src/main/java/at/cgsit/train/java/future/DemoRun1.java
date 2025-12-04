package at.cgsit.train.java.future;

public class DemoRun1 implements Runnable {

    @Override
    public void run() {
        long threadId = Thread.currentThread().threadId();
        System.out.printf("Task with thread id: %s started \n", threadId);
        sleep(4000);
        System.out.printf("Task with thread id: %s ended \n", threadId);

    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
        }
    }
}
