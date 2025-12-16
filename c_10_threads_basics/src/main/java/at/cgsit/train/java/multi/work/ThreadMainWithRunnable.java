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

public class ThreadMainWithRunnable {

    static void main(String[] args) throws InterruptedException {

        // unser task ist ein Runnable
        MyTask myTask = new MyTask();

        // dieses Runnable Object kann ich dann dem Thread übergeben als auszuführendes Element
        // via run methode

        Thread thread = new Thread(myTask, "MY Custom Thread");

        // parallel weg starten als thread
        thread.start();

        // warten auf fertigstellung
        thread.join();

        LocalDateTime localDateTime = Instant.now().atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println("main methode nach Thread aufruf: " + localDateTime.toString());
    }
}
