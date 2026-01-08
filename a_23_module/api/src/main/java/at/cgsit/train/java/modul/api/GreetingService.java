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

package at.cgsit.train.java.modul.api;

import at.cgsit.train.java.modul.dto.PersonDTO;

public interface GreetingService {

    String greet(String name);

    default PersonDTO echoPerson(PersonDTO input) {
        return input;
    }
}

