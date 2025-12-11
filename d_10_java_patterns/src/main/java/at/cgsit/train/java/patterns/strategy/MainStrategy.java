package at.cgsit.train.java.patterns.strategy;

public class MainStrategy {

  static void main() {

    CheckoutService checkout =
        new CheckoutService(new PayPalPayment());

    checkout.pay(99.99);

    // oder wechseln:
    checkout = new CheckoutService(new CreditCardPayment());
    checkout.pay(49.50);

  }

}
