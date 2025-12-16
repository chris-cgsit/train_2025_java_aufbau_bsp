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

import java.util.List;

public class LambdaMehrereAnweisungen {

    public static void main(String[] args) {

        List<String> namen = List.of("Anna", "Bob", "Carla");

        // Lambda mit mehreren Anweisungen in einem Block { ... }
        namen.forEach(name -> {
            String upper = name.toUpperCase();         // 1: umwandeln
            int length = name.length();                // 2: Länge bestimmen
            System.out.println(upper + " (" + length + ")");
        });

        // alternative: Lambda mit Bedingung und Zwischenschritt
        namen.forEach(name -> {
            if (name.startsWith("A")) {
                System.out.println("Beginnt mit A → " + name.toUpperCase());
            } else {
                System.out.println("Anderer Name: " + name);
            }
        });
    }
}
