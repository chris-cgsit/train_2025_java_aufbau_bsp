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

package tierePackage2;

import java.time.LocalDate;

public class Katze extends Haustier {

    protected String spielzeug;

    public Katze(String name, LocalDate datum, String spielzeug) {
        super(name, datum);
        System.out.println("Konstruktor von Katze");
        this.spielzeug = spielzeug;
    }

    public String getSpielzeug() {
        return spielzeug;
    }

    public void miaue(){
        System.out.printf("%s macht miauuuu\n", kosename);
    }

    @Override
    public void zeigeDich() {
        super.zeigeDich();
        System.out.printf("Ich bin eine Katze und spiele gern mit %s\n", spielzeug);
    }

    @Override
    public String toString() {
        return super.toString() + ", spielzeug='" + spielzeug + '\'';
    }
}
