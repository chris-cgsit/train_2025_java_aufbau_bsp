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

import java.util.Arrays;

public class ArraySortSearchDemo {
    public static void main(String[] args) {

        int[] values = { 42, 7, 19, 3, 25 };

        Arrays.sort(values);

        System.out.println("Sortiert: " + Arrays.toString(values));

        // Binäre Suche
        int pos = Arrays.binarySearch(values, 19);
        System.out.println("Position von 19: " + pos);
    }
}
