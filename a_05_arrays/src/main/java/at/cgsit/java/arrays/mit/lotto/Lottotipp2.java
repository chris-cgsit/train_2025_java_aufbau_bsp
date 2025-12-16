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

package at.cgsit.java.arrays.mit.lotto;

public class Lottotipp2 {
    // statt einzelner Werte
    // private int zahl1, zahl2; // ...
    // ein Array für die 6 Lottozahlen erstellen
    private int[] tippZahlen = new int[6];

    public Lottotipp2(char typ){
        if(typ == 'Q')
            quickTipp();
        else
            manuellerTipp();
    }

    private void quickTipp(){
        // TODO:Quicktipp implementieren
    }

    private void manuellerTipp(){
        // TODO: 6 unterschiedliche Zahlen vom Benutzer einlesen
    }

}
