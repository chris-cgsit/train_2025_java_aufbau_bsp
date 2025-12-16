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

package at.cgsit.train.java.threads.future_completeble;

public class DemoTasks {

    public static String task1() {
        sleep(1000);
        System.out.println("Task 1 fertig");
        return "Ergebnis Task 1";
    }

    public static String task2() {
        sleep(1500);
        System.out.println("Task 2 fertig");
        return "Ergebnis Task 2";
    }

    public static Integer task3() {
        sleep(1200);
        System.out.println("Task 3 fertig");
        return 42;
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
