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

// Klasse Hund leitet von Haustier ab
public class Hund extends Haustier {
    protected int gewicht;

    public Hund() {
        super(); // Aufruf des Basisklasssen-Konstruktors, wird auch implizit ausgeführt
        System.out.println("Konstruktor von Hund");
    }

    public int getGewicht() {
        return gewicht;
    }

    public void setGewicht(int gewicht) {
        this.gewicht = gewicht;
    }

    public void belle() {
        System.out.printf("%s mach wau wau! \n", kosename);
    }
}
