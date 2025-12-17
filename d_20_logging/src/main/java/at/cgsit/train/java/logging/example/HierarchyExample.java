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

import java.util.logging.Logger;

public class HierarchyExample {

    private static final Logger ROOT =
            Logger.getLogger("at.cgsit");

    private static final Logger CHILD =
            Logger.getLogger("at.cgsit.service");

    public static void main(String[] args) {

        ROOT.info("Root log");
        CHILD.info("Child log");
    }
}
