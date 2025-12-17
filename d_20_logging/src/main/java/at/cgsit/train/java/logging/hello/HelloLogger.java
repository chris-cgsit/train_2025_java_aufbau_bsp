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

package at.cgsit.train.java.logging.hello;

import at.cgsit.train.java.logging.init.InitLogging;

import java.util.logging.Logger;

public class HelloLogger {
    static {
        InitLogging.init();
    }

    private static final Logger LOG =
            Logger.getLogger(HelloLogger.class.getName());

    public static void main(String[] args) {


        LOG.info("Hello Logging");
        LOG.warning("This is a warning");
        LOG.severe("This is a severe error");
    }
}
