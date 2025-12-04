package at.cgsit.train.java.multi.work;

public class MyTask implements Runnable {

    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        MyTaskKernLogik myBusionessLogic = new MyTaskKernLogik();
        myBusionessLogic.execute( thread.getName());
    }

}
