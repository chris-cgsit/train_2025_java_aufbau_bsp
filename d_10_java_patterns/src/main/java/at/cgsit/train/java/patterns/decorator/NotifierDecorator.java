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

package at.cgsit.train.java.patterns.decorator;

public class NotifierDecorator implements InfoSender {
    protected final InfoSender wrappee;


    public NotifierDecorator(InfoSender wrappee) {
        this.wrappee = wrappee;
    }


    public void send(String message) {

        System.out.println("before wrapee");

        wrappee.send(message);

        System.out.println("after wrapee");

    }
}
