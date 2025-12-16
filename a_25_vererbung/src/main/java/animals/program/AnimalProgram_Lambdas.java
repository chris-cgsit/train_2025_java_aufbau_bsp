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

package animals.program;

import animals.model.Animal;
import animals.model.AnimalFilter;
import animals.model.AnimalList;

public class AnimalProgram_Lambdas {
    public static void main(String[] args) {
        System.out.println("AnimalFilter mit Lambda Expressions");
        AnimalList animals = new AnimalList();
        System.out.println("Alle Tiere:");
        animals.showAll();
        // Lambda expression mit Parametertyp und Block-Klammern
        AnimalFilter filter1 = (Animal a) -> {
            return !a.isHerbivore();
        };

//        // kürzer ginge es so:
//        filter1 = a -> !a.isHerbivore();
        System.out.println("Fleischfresser:");
        animals.showAnimals(filter1);

        // Lambda expression ohne Block-Klammern, direkt als Parameter übergeben
        System.out.println("Leichte Tiere:");
        animals.showAnimals(a -> a.getWeight() < 500);


    }
}
