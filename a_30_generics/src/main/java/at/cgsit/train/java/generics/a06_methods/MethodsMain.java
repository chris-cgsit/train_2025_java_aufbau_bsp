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

package at.cgsit.train.java.generics.a06_methods;

import at.cgsit.train.java.generics.a05_interfaces.Person;

import java.math.BigDecimal;
import java.util.function.Predicate;

public class MethodsMain {

  static void main() {

//    String s = Util.identity("Hello");
//    Integer i = Util.identity(42);
//    Person result = Util.identity(new Person());

      int hashCode = Util.identity("Hello");

      int hashCode2 = Util.identityWithExtends(new Person());

      Util<String> u = new Util();

      int hashCode3 = u.instanzMethode(BigDecimal.valueOf(10L));

      Predicate<String> p;



  }

}
