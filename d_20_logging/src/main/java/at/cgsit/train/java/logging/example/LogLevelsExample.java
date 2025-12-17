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

import java.util.logging.Logger;

public class LogLevelsExample {
    static {
        // nur einmal im main
        InitLogging.init();
    }

    private static final Logger LOG =
            Logger.getLogger(LogLevelsExample.class.getName());

    public static void main(String[] args) {

        LOG.severe("SEVERE message");
        LOG.warning("WARNING message");
        LOG.info("INFO message");
        LOG.fine("FINE message");
        LOG.finest("FINEST message");
    }
}
