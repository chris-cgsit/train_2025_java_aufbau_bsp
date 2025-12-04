package at.cgsit.train.java.multi.work;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * komplett neutrale kern logik klasse, easy to test
 */
public class MyTaskKernLogik {

    public void executeLoop() {
        Thread myThread = Thread.currentThread();
        for(int i = 0; i < 10; i++) {
            this.execute( myThread.getName() + "@" + myThread);
            try {
                Thread.sleep(1000 * 2 );
            } catch (InterruptedException e) {

            }
        }
    }

    public void execute(String name) {

        LocalDateTime localDateTime = Instant.now().atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println("Ich laufe gerade jetzt: " + name + " " + localDateTime.toString());
    }

}
