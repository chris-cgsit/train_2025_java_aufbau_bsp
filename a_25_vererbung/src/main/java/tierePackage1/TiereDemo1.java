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

package tierePackage1;

import java.time.LocalDate;

public class TiereDemo1 {
    public static void main(String[] args) {
        Haustier minna = new Haustier();
        minna.setKosename("Minna");
        minna.setGeburtsdatum(LocalDate.of(2005, 12, 3));
        // wäre auch möglich, weil im selben Package
        // minna.geburtsdatum = LocalDate.of(2005, 12, 3);
        minna.zeigeDich();
        System.out.printf("%s ist %d Jahre alt\n", minna.getKosename(), minna.getAlter());

        // Objekt der abgeleiteten Klasse:
        Hund rex = new Hund();
        // Setter der Basisklasse
        rex.setKosename("Rex");
        rex.setGeburtsdatum(LocalDate.of(2015, 10,30));
        // Setter aus Hund
        rex.setGewicht(21);
        // Methoden der Basisklasse
        rex.zeigeDich();
        // Methode aus Hund
        rex.belle();
        System.out.printf("%s ist %d Jahre alt\n", rex.getKosename(), rex.getAlter());



    }
}
