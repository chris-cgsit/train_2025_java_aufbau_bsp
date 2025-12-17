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

public class LevelCheckExample {

    private static final Logger LOG =
            Logger.getLogger(LevelCheckExample.class.getName());

    public static void main(String[] args) {

        if (LOG.isLoggable(Level.FINE)) {
            LOG.fine("Expensive debug message");
        }
    }
}
