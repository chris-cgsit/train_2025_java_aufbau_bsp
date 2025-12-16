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

package at.cgsit.train.java.generics.a04_extends;

public class ClubMain {

  static void main() {

    Club<Player> club =
        new Club<>(new Player("Max"));


    Club<?> clubTwo =
        new Club<>(new Player("Max"));

    clubTwo.printName();

  }

}
