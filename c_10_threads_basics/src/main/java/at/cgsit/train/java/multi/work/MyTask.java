package at.cgsit.train.java.multi.work;

public class MyTask implements Runnable {

    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        MyTaskKernLogik myBusionessLogic = new MyTaskKernLogik();

        Thread.State state = thread.getState();
        System.out.println("thread state is" + state.toString());
        long threadId = thread.threadId();
        System.out.println("thread id is" + threadId);

        for(int i = 0; i < 10; i++) {
            myBusionessLogic.execute( thread.getName() + "@" + threadId);
            try {
                Thread.sleep(1000 * 2 );
            } catch (InterruptedException e) {

            }
        }

    }

}
