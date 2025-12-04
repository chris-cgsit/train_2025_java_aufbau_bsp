package at.cgsit.train.java.multi.work;

public class MeinThreadImpl extends Thread  {

    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        MyTaskKernLogik myBusionessLogic = new MyTaskKernLogik();
        myBusionessLogic.execute( thread.getName());
    }
}
