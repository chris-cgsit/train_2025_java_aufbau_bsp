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

public class ArrayReferenceDemo {

    public static void main(String[] args) {

        int[] a = { 1, 2, 3 };
        int[] b = a;   // KEINE Kopie → gleiche Referenz

        b[1] = 99;

        System.out.println(a[1]); // 99
    }
}
