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

package at.cgsit.train.java.patterns.sealed.bsp;

public class MainSealedDemo {

  static void main() {

  }

  static int calc(Operation op) {
    return switch (op) {
      case Add a -> a.a() + a.b();
      case Multiply m -> m.a() * m.b();
    };
  }

}
