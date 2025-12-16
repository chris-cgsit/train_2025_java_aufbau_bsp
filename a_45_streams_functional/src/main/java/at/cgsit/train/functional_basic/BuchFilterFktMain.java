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

public class BuchFilterFktMain {

  static void main() {

    Buch b = new Buch(1, "Java Language");

    BuchFilterInterface filterWithC = new BuchFIImpl();

    boolean trueFor = filterWithC.isTrueFor(b);


      // Alte Schreibweise vor Java 8.
    // Wir erstellen eine anonyme Klasse, die die Methode überschreibt.
    // klassich aber etwas viel Code.
    // anonyme Klasse
    BuchFilterInterface filter = new BuchFilterInterface() {
      @Override
      public boolean isTrueFor(Buch a) {
        return false;
      }
    };

    // Moderne Kurzform für genau dieselbe Logik.
    //  a -> ... bedeutet:
    // „nimm das Buch a und gib zurück, ob Bedingung erfüllt ist“.
    // compiler kennt das BuchFlterInterface und weiss das es eine methode ist,
    // mit einem Buch a als input -> und damit kann man hier die { implementierung enbetten }
    // der returnwert ist hier boolean das wird sofort erkannt
    BuchFilterInterface filterLambda = a -> {
      return a.getName() != null;
    };

    // finally :::
    // !! Wenn nur eine Zeile Code und Direkt-return, dann Klammern und return weglassen. !!
    BuchFilterInterface filterLambda2 = a -> a.getName() != null;

    // funktions referenz:
    BuchFilterInterface filterLambdaMethodRef = BuchFilterFktMain::isTrueFor;

    boolean trueForRef = filterLambdaMethodRef.isTrueFor(b);

    // Warum Lambdas?
    //    Weniger Code
    //    Besser lesbar
    //    Ideal für Streams & moderne Java-Programmierung
  }

    public static boolean isTrueFor(Buch a) {
        return false;
    }


}
