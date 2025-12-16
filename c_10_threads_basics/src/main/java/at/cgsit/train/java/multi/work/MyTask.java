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

public class MyTask implements Runnable {

    @Override
    public void run() {
        Thread myThread = Thread.currentThread();
        Thread.State state = myThread.getState();
        System.out.println("thread state is" + state.toString());
        long threadId = myThread.threadId();
        System.out.println("thread id is" + threadId);

        new MyTaskKernLogik().executeLoop();
    }

}
