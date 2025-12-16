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

package at.cgsit.train.java.patterns.builder;

public class Main {

  static void main() {

    User user = new User.Builder()
        .name("Chris")
        .email("cgs@example.com")
        .age(40)
        .newsletter(true)
        .build();

  }
}
