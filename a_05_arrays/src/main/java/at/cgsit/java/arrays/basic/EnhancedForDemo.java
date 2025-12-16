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

package at.cgsit.java.arrays.basic;

public class EnhancedForDemo {

    public static void main(String[] args) {
        int[] werte = { 4, 8, 15, 16, 23, 42 };

        // for-each: kein Index verfügbar
        for (int w : werte) {
            System.out.println("Wert: " + w);
        }
    }
}
