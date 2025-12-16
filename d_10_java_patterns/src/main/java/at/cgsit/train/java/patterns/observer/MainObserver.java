/*
 *  Copyright © 2025 CGS IT Solutions GmbH.
 *  All rights reserved.
 *
 *  This source code is proprietary intellectual property
 *  of CGS IT Solutions GmbH and is provided solely for use
 *  within licensed training programs.
 *
 *   Any copying, redistribution, modification, or use
 *   beyond the permitted scope is strictly prohibited.
 */

package at.cgsit.train.java.patterns.observer;

public class MainObserver {

  static void main() {
    NewsPublisher publisher = new NewsPublisher();

    publisher.addObserver(new EmailSubscriber());
    publisher.addObserver(msg -> System.out.println("Lambda: " + msg));

    publisher.publish("NEWS: Builder Pattern erklärt!");

  }

}
