package at.cgsit.train.java.multi.work;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class ThreadMainWithRunnable2 {

    static void main(String[] args) throws InterruptedException {

        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {

                String name = this.getClass().getName();
                System.out.println("Class name of runnable:" + name);

                MyTaskKernLogik myBusionessLogic = new MyTaskKernLogik();
                myBusionessLogic.execute("execute String");
            }
        };

        // dieses Runnable Object kann ich dann dem Thread übergeben als auszuführendes Element
        // via run methode
        Thread thread = new Thread(myRunnable);

        // parallel weg starten als thread
        thread.start();

        // warten auf fertigstellung
        thread.join();

        LocalDateTime localDateTime = Instant.now().atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println("main methode nach Thread aufruf: " + localDateTime.toString());
    }

}
