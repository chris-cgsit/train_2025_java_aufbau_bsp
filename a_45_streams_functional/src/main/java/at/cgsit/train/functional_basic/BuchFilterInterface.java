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

package at.cgsit.train.functional_basic;


import at.cgsit.train.java.objects.Buch;


// Ein Functional Interface ist ein Interface mit genau einer abstrakten Methode.
// Es beschreibt eine Funktion – hier eine Prüfung für ein Buch.
// isTrueFor() gibt true/false zurück.
// Das Interface sagt nur „ich prüfe ein Buch“, keine Details wie geprüft wird.
@FunctionalInterface
public interface BuchFilterInterface {

  boolean isTrueFor(Buch a);

}
