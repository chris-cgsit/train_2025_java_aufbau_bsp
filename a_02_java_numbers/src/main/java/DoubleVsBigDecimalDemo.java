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

import java.math.BigDecimal;

public class DoubleVsBigDecimalDemo {

    public static void main(String[] args) {
        System.out.println("=== DOUBLE FEHLER ===");
        double a = 0.1;
        double b = 0.2;
        double sum = a + b;

        System.out.println("0.1 + 0.2 = " + sum);
        System.out.printf("mit printf: %.20f%n%n", sum);

        System.out.println("=== BIGDECIMAL KORREKT ===");

        // Wichtig: Strings verwenden — nicht BigDecimal(double)!
        BigDecimal bdA = new BigDecimal("0.1");
        BigDecimal bdB = new BigDecimal("0.2");
        BigDecimal bdSum = bdA.add(bdB);

        System.out.println("0.1 + 0.2 = " + bdSum);

        // Weiteres Beispiel: 10 Cent * 3
        System.out.println("\n=== Währungsbeispiel ===");

        double preisDouble = 0.10;
        double totalDouble = preisDouble * 3;

        System.out.println("Double 0.10 * 3 = " + totalDouble);
        System.out.printf("mit printf: %.20f%n", totalDouble);

        BigDecimal preisBD = new BigDecimal("0.10");
        BigDecimal totalBD = preisBD.multiply(new BigDecimal("3"));

        System.out.println("BigDecimal 0.10 * 3 = " + totalBD);
    }
}
