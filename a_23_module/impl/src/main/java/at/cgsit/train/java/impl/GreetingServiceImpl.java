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

package at.cgsit.train.java.impl;


import at.cgsit.train.java.modul.api.GreetingService;
import at.cgsit.train.java.privates.MyPackageInternalService;

/**
 * wenn eine klasse im modul via package exportiert ist
 * dann muss sie auch hier public sein und nicht package loklal.
 * diese Java Grundlagen Features gelten "natürlich" nach wie vor.
 */
public class GreetingServiceImpl implements GreetingService {

    @Override
    public String greet(String name) {
        MyPackageInternalService internalService = new MyPackageInternalService();

        return "Hello " + internalService.echo(name) + " from impl module";
    }
}
