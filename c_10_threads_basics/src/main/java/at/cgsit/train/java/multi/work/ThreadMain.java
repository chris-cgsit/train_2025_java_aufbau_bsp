package at.cgsit.train.java.multi.work;

import java.time.Instant;

public class ThreadMain {

    static void main(String[] args) throws InterruptedException {

        MeinThreadImpl m = new MeinThreadImpl();
        m.start();

        m.join(); // hier kann ich warten auf diese Threads

        // wann die db check.
        // Thread thread = new Thread();
        // MyTask myTask = new MyTask();
        // myTask.run();

        System.out.println("main methode nach Thread aufruf: " + Instant.now().toString());
    }



}
