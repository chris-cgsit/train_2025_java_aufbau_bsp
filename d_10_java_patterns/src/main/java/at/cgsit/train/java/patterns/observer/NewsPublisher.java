package at.cgsit.train.java.patterns.observer;

import java.util.ArrayList;
import java.util.List;

public class NewsPublisher {
    private final List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer obs) {
        observers.add(obs);
    }

    public void publish(String message) {
        observers.forEach(o -> o.update(message));
    }
}
