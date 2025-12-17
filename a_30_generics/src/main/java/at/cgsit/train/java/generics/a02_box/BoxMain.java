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

package at.cgsit.train.java.generics.a02_box;

import java.io.ObjectStreamException;

public class BoxMain {

  static void main() {

    Box<String> stringBox = new Box<>();
      stringBox.set("öalksdjf");

    String s = stringBox.get(); // kein Cast nötig

    Box<Integer> intBox = new Box<>();
    intBox.set(42);

      Object i = intBox.get();
      if (i instanceof Integer d) {
          int i1 = d.intValue();
          System.out.println("int value " + i1);
      }


  }

}
