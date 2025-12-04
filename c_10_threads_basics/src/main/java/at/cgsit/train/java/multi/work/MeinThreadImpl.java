package at.cgsit.train.java.multi.work;

public class MeinThreadImpl extends Thread  {

    @Override
    public void run() {
        new MyTaskKernLogik().executeLoop();
    }
}
