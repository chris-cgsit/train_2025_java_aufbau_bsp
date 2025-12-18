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

package at.cgsit.train.java.logging.example;

import at.cgsit.train.java.logging.init.InitLogging;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExceptionLogging {
    static {
        // setzte unser local auf englsih, damit auch english initialisiert wird für den logger
        Locale.setDefault(Locale.ENGLISH);
        System.out.println("static plock");
        InitLogging.init();
    }

    private static final Logger LOG = Logger.getLogger(ExceptionLogging.class.getName());

    public static void main(String[] args) {

        Runtime.getRuntime().maxMemory();

        try {
            int level = 0;
            // recursive error stack overlfow
            // rescursiveMe(level);

            divide(10, 0);

        } catch (Exception e) {
            LOG.severe("Severere Error: [" + e.getMessage() + "]");
            LOG.log(Level.SEVERE, "Calculation failed", e);
        }
    }

    static int divide(int a, int b) {
        return a / b;
    }

    static void rescursiveMe(int level) {
        level += 1;
        if (level % 10 == 0)
            LOG.info("recurcion level erreicht: " + level);
        rescursiveMe(level);
    }

}



