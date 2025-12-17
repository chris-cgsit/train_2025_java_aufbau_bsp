package at.cgsit.train.java.app;


import calculator.api.at.cgsit.train.java.calculator.api.Calculator;

public class SimpleCalculator implements Calculator {

    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int sub(int a, int b) {
        return a - b;
    }
}
