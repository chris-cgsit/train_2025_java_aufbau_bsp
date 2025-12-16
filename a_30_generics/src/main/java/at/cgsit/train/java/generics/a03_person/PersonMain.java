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

package at.cgsit.train.java.generics.a03_person;

import java.util.UUID;

public class PersonMain {

  static void main() {


    Person<Long, String> p1 = new Person<>(1L, "Max Mustermann");

    // oder
    Person<UUID, Integer> p2 = new Person<>(UUID.randomUUID(), 42);



  }

}
