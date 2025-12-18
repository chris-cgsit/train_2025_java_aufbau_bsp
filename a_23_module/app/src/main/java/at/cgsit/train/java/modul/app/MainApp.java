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

package at.cgsit.train.java.modul.app;

import at.cgsit.train.java.impl.GreetingServiceImpl;

public class MainApp {

    public static void main(String[] args) {
        var service = new GreetingServiceImpl();
        System.out.println(service.greet("Chris"));
    }
}
