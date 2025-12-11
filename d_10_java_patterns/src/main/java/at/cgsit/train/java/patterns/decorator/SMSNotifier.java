package at.cgsit.train.java.patterns.decorator;

public class SMSNotifier extends NotifierDecorator {

    public SMSNotifier(Notifier wrappee) {
        super(wrappee);
    }

    @Override
    public void send(String message) {
        super.send(message);
        System.out.println("SMS: " + message);
    }
}
