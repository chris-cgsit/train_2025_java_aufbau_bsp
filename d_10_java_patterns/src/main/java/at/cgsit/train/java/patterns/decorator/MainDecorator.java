package at.cgsit.train.java.patterns.decorator;

public class MainDecorator {

  static void main() {
    Notifier notifier =
        new SMSNotifier(
            new EmailNotifier()
        );

    notifier.send("Willkommen bei CGS!");

  }

}
