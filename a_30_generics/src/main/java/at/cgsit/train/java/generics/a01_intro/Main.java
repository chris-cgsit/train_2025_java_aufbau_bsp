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

package at.cgsit.train.java.generics.a01_intro;

import java.io.LineNumberInputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {
  static void main() {

    testListWithRawTypes();

      List<String> list = new ArrayList<String>();
    list.add("Hello");
    String value = list.get(0);


    // list.add(42);   // Compiler-Fehler!

  }

  private static void testListWithRawTypes() {

      List list = null;
      list = new ArrayList();

      list.add("Hello World chat message");
    list.add(42);      // erlaubt!


    String text = (String) list.get(0);   // OK
      // String number = (String) list.get(1); // Laufzeitfehler!

      Object o = list.get(1);
      if (o instanceof String) {
          // ja es ist ein string
          String myString = (String) o;
          System.out.printf("mystring is: %s \n", myString);
      } else {
          System.out.printf("was ist es %s \n", o.getClass().getName());
      }

  }
}

