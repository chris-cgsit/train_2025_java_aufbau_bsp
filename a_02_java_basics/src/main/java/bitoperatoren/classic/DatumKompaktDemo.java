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

package bitoperatoren.classic;

public class DatumKompaktDemo {

	public static void main(String[] args) {
		DatumKompakt datum = new DatumKompakt(15, 3, 2021);
		// toString wird implizit aufgerufen, wenn ein Objekt
		// an der Konsole angezeigt werden soll
		System.out.println(datum/*.toString()*/);
		
		datum = new DatumKompakt(29, 11, 2099);
		System.out.println(datum/*.toString()*/);
		
		// 1100011101111101

	}

}
