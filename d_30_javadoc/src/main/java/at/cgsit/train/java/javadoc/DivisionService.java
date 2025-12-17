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

package at.cgsit.train.java.javadoc;

/**
 * Service for division operations.
 *
 * @apiNote This service is not thread-safe.
 */
public class DivisionService {

    /**
     * Divides two numbers.
     *
     * @param a dividend
     * @param b divisor
     * @return result of division
     * @throws ArithmeticException if {@code b == 0}
     *
     * @implNote Uses integer division.
     */
    public int divide(int a, int b) {
        return a / b;
    }
}
