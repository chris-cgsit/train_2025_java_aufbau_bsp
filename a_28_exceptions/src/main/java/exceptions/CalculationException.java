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

package exceptions;

// diese Klasse ist eine unchecked Exception weil sie von RuntimeException ableitet
// -> kann ohne Deklaration geworfen werden
public class CalculationException extends RuntimeException{
    // Konstruktor mit Angabe des Fehlertextes
    public CalculationException(String msg){
        // den Fehlertext an die Basisklasse weitergeben
        super(msg);
    }

    // Konstruktor mit Angabe des Fehlertextes und des auslösenden Fehlers
    public CalculationException(String msg, Exception cause){
        // den Fehlertext und den auslösenden Fehler an die Basisklasse weitergeben
        super(msg, cause);
    }

}
