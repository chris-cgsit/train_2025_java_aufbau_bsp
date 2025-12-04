package at.cgsit.train.java.multi.work;

public class MeinThreadImpl extends Thread  {

    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        MyTaskKernLogik myBusionessLogic = new MyTaskKernLogik();
        for(int i = 0; i < 20; i++) {
            myBusionessLogic.execute( thread.getName());
            try {
                Thread.sleep(1000 * 3 );
            } catch (InterruptedException e) {

            }
        }
    }
}
