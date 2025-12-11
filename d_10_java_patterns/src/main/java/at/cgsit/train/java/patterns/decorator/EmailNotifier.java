package at.cgsit.train.java.patterns.decorator;

public class EmailNotifier implements Notifier {
    public void send(String message) {
        System.out.println("Email: " + message);
    }
}
