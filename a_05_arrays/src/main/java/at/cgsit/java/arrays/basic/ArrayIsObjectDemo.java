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

public class ArrayIsObjectDemo {

    public static void main(String[] args) {

        int[] arr = { 10, 20, 30 };

        Class<?> clazz = arr.getClass();

        System.out.println("Klassenname: " + clazz.getName());
        System.out.println("Ist Array?  " + clazz.isArray());
        System.out.println("Komponententyp: " + clazz.getComponentType());
    }
}
