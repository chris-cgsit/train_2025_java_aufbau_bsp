package at.cgsit.train.java.patterns.strategy;

public class CreditCardPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Kreditkarte Zahlung: " + amount);
    }
}