package at.cgsit.train.java.patterns.observer;

public class EmailSubscriber implements Observer {
    public void update(String message) {
        System.out.println("Email erhalten: " + message);
    }
}
