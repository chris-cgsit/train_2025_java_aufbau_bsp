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

import java.util.logging.Level;
import java.util.logging.Logger;

public class ParameterizedLogging {
    static {
        // nur einmal im main
        InitLogging.init();
    }
    private static final Logger LOG = Logger.getLogger(ParameterizedLogging.class.getName());

    public static void main(String[] args) {

        String user = "chris";
        int items = 5;

        LOG.log(Level.INFO,
                "User {0} ordered {1} items",
                new Object[]{user, items});
    }
}
