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

package at.cgsit.train.java.generics.a06_methods;

import at.cgsit.train.java.generics.a05_interfaces.Person;

/**
 * Das Ojbkelt selbst hat einen Typ Parameter X
 * aber auch hier önnen die Methode extra weitere Typ Parameter for bestimmte Methoden verwenden
 * <br>
 * wenn geht diese Mischungen vermeiden, da es dann etwas komplex wird ..
 *
 * @param <X>
 */
public class Util<X> {

    public X echo(X input) {
        return input;
    }

    /**
     * Instnaz Methode mit eigenem T
     * @param <ZZZ>
     */
    public <ZZZ> int instanzMethode(ZZZ input) {
        return input.hashCode();
    }

    /**
     * statische Methode mit eigenem T
     */
    public static <T> int identity(T value) {
        return value.hashCode();
    }

    public static <T> boolean checkIfTur(T value) {
        if (value == null) return false;
        else
            return true;
        //return value != null ? true : false;
    }


    public static <YYY extends Person> int identityWithExtends(YYY value) {
        return value.hashCode();
    }
}


