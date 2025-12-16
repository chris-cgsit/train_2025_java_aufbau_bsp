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

package tierePackageAbstrakt;

import java.time.LocalDate;

public class Dackel extends Hund{
    public Dackel(String kosename, LocalDate gebdatum, int gewicht) {
        super(kosename, gebdatum, gewicht);
        System.out.println("Konstruktor von Dackel");
    }

    @Override
    public void belle() {
        // kein Aufruf der Basisklasse, sondern ganz neue Implementierung
        // super.belle();
        System.out.printf("Dackel %s macht wuff wuff wuff\n", kosename);
    }
}
