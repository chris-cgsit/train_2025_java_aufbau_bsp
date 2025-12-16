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

package at.cgsit.train.a_streams;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SteamBspOne {

  static void main() {

    List<String> namen = List.of("Anna", "Bob", "Alexander", "Carla");

      namen.stream()
        .filter(name -> name.startsWith("A"))
        // .map(name -> name.toUpperCase())
        .map(String::toUpperCase)
        .forEach(System.out::println);
  }
}
