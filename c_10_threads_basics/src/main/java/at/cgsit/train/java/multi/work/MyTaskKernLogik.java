package at.cgsit.train.java.multi.work;

import java.time.Instant;

/**
 * komplett neutrale kern logik klasse, easy to test
 */
public class MyTaskKernLogik {

    public void execute(String name) {
        System.out.println("Ich laufe gerade jetzt: " + name + " time: " +  Instant.now().toString());
    }

}
