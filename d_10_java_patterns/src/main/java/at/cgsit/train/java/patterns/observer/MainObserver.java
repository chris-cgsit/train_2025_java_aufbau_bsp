package at.cgsit.train.java.patterns.observer;

public class MainObserver {

  static void main() {
    NewsPublisher publisher = new NewsPublisher();

    publisher.addObserver(new EmailSubscriber());
    publisher.addObserver(msg -> System.out.println("Lambda: " + msg));

    publisher.publish("NEWS: Builder Pattern erklärt!");

  }

}
