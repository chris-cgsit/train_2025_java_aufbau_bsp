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

public class Calculator {
	public int calculate(char op, int number1, int number2) {
		// Berechnung je nach Operator
		switch (op) {
		case '+':
			return number1 + number2;
		case '-':
			return number1 - number2;
		case '/':
			// handle division by zero
			try {
				return number1 / number2;
			} catch (ArithmeticException e){
				//  ArithmeticException in eine CalculationException einpacken
				throw new CalculationException("Fehler in der Division", e);
			}
		case '*':
			return number1 * number2;
		default:
			// throw exception
			throw new CalculationException("Ungültiger Operator " + op);
			//return 0;
		}

	}
}
