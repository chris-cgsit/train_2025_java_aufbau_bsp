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

package at.cgsit.train.java.job_fw.demo;


import at.cgsit.train.java.job_fw.api.Job;
import at.cgsit.train.java.job_fw.api.JobResult;
import at.cgsit.train.java.job_fw.api.SuccessResult;

import java.util.Random;

public class DemoJob implements Job {

    private final String name;
    private final int number;
    private final Random random = new Random();

    public DemoJob(String name, int number) {
        this.name = name;
        this.number = number;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public JobResult execute() throws Exception {
        // Simulierte Arbeit
        Thread.sleep(300 + random.nextInt(800));

        // 20 % Fehlerquote zur Demonstration
        if (random.nextInt(5) == 0) {
            throw new RuntimeException("Simulierter Fehler im Job: " + name);
        }

        // Erfolgreiches Ergebnis
        return new SuccessResult(name + " processed value=" + number);
    }
}
