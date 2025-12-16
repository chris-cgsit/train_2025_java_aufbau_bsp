/*
 *  Copyright © 2025 CGS IT Solutions GmbH.
 *  All rights reserved.
 *
 *  This source code is proprietary intellectual property
 *  of CGS IT Solutions GmbH and is provided solely for use
 *  within licensed training programs.
 *
 *   Any copying, redistribution, modification, or use
 *   beyond the permitted scope is strictly prohibited.
 */

package at.cgsit.train.java.multi.work;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class ThreadMainWithRunnable2 {

    static void main(String[] args) throws InterruptedException {

        /*
         das hier ist IMHO nicht sinnvoll. Denn im programm auflauf von main
         hätten wir hier jetzt den Ablauf und die Methoden Implementierung von unserem Run
         direkt enthalten, das ist eher verwirrend.
         besser sowas nur verwenden, wenn das run bzw generell die Funktion nur auferufen
         wird oder und einfach ist .. also wenn mehr code, dann eigene Runnable
         Klasse implementieren.
         IntellJ würde her auch refactoring vorschlagen in eigene Methode get thread,
         was sinn machen könnte.
        */
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
