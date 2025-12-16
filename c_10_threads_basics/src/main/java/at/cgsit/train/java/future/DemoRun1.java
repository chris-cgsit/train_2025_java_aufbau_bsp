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

package at.cgsit.train.java.future;

public class DemoRun1 implements Runnable {

    @Override
    public void run() {
        long threadId = Thread.currentThread().threadId();
        System.out.printf("Task with thread id: %s started \n", threadId);
        this.msleep(5000);
        System.out.printf("Task with thread id: %s ended \n", threadId);
    }

    private void msleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
        }
    }
}
