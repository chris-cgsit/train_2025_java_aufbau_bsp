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

package at.cgsit.train.java.records;

record User(String name, int age) {

  /**
   * kompakte implementierung der Konstruktor Methoden Implementierung
   * parameter sind fix, hier nur die implementierung
   */
  public User {
    if (age < 0) throw new IllegalArgumentException("Age must be >= 0");
  }

  // weitere Konstrukturen.
  // muss aber den Basis Konstruktur aufrufen. eg. default werte
  public User(String name) {
    this(name, 18);
  }

}
