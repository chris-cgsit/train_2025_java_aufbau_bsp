package at.cgsit.train.java.multi.work;

public class MyTask implements Runnable {

    @Override
    public void run() {
        Thread myThread = Thread.currentThread();
        Thread.State state = myThread.getState();
        System.out.println("thread state is" + state.toString());
        long threadId = myThread.threadId();
        System.out.println("thread id is" + threadId);

        new MyTaskKernLogik().executeLoop();
    }

}
