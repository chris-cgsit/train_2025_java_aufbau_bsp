package at.cgsit.train.java.app;

import calculator.api.at.cgsit.train.java.calculator.api.Calculator;

// TODO: refactor
public class Main {

    public static void main(String[] args) {

        Calculator calc = new SimpleCalculator();

        System.out.println("3 + 4 = " + calc.add(3, 4));
        System.out.println("10 - 7 = " + calc.sub(10, 7));
    }
}
