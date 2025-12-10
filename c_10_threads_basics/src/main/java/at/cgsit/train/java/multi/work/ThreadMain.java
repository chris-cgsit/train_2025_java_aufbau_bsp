package at.cgsit.train.java.multi.work;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class  ThreadMain {

    static void main(String[] args) {

        MeinThreadImpl m = new MeinThreadImpl();
        m.start();

        try {
            m.join(); // hier kann ich warten auf diese Threads
        } catch (InterruptedException e) { }

        // wann die db check.
        // Thread thread = new Thread();
        // MyTask myTask = new MyTask();
        // myTask.run();

        LocalDateTime localDateTime = Instant.now().atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println("main methode nach Thread aufruf: " + localDateTime.toString());
    }



}
