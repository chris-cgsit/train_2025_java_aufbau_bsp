package at.cgsit.train.java.patterns.strategy;

public class PayPalPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("PayPal Zahlung: " + amount);
    }
}

