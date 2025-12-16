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

package at.cgsit.train.java.threads.synch;

// Gemeinsames Objekt == die instanz hier gibt es nur 1 mal
class SharedCounter {

  private int value = 0;
  private String name = "";

  // synchronized – nur ein Thread darf gleichzeitig diese Methode ausführen
  public void increment(String who) {
    value++;
    this.name = who;
    System.out.println(who + " -> Counter: " + value);
  }
}