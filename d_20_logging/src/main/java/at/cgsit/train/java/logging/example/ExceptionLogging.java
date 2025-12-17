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

import java.util.logging.Level;
import java.util.logging.Logger;

public class ExceptionLogging {

    private static final Logger LOG =
            Logger.getLogger(ExceptionLogging.class.getName());

    public static void main(String[] args) {

        try {
            divide(10, 0);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Calculation failed", e);
        }
    }

    static int divide(int a, int b) {
        return a / b;
    }
}
