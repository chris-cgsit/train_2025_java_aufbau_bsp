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

package vergleiche;

public class VergleichsBeispiel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// sysout + Strg+' '
		System.out.println("Beispiel zur Vergleichsoperatoren");
		int nr1 = 10;
		int nr2 = 7;
		
		boolean istNr1KleinerNr2;
		
		istNr1KleinerNr2 = nr1 < nr2;
		
		int minimum = 7;
		int maximum = 13;
		int messwert = 3;
		
		boolean wertLiegtDazwischen;
//		if (messwert > minimum && messwert < maximum) {
//			wertLiegtDazwischen = true;
//		}
//		else {
//			wertLiegtDazwischen = false;
//		}
		wertLiegtDazwischen =
				messwert > minimum && messwert < maximum;
		
		if (wertLiegtDazwischen) {
			System.out.println("Wert liegt dazwischen");
		}
		
		int wert1 = 100;
		//if (wert1) { // funktioniert nicht, da kein boolean
		//}
		
		// uerberprueft ob es ausserhalb liegt
		if (messwert < minimum || messwert > maximum) {
			System.out.println("liegt außerhalb");
		}
		
		// XOR - exklusive oder
		if (messwert < minimum ^ messwert > maximum) {
			System.out.println("liegt außerhalb");
		}
	}

}
