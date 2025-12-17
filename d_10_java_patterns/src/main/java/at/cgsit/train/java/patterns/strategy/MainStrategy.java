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

package at.cgsit.train.java.patterns.strategy;

public class MainStrategy {

    // Konstanten statci final und zentral halten
    // NICHT im code verteilen und zig mal denselben string verwenden.
    // das wäre ein refactoring Problem !
    public static final String SCHALTERX = "schalterx";

    /**
     * bei der main funktion immer dokumentieren wie werden wir gestartet
     * aufrufpaurameter oder link darauf ..
     *
     * "java -cp xxx MainStrategy paypal"
     * @param args
     */
    static void main(String[] args) {

        // 1. konstruiere .. und configuriere
        CheckoutService checkout = configAndConstructCheckoutService(args);

        // 2. arbeite damit
        checkout.pay(49.50);

    }



    private static CheckoutService configAndConstructCheckoutService(String[] args) {

        String schalterx = null;
        // zuerst von ENV
        schalterx = getStrVonEnvVariable(schalterx);

        // dann von ARGS
        debugArgs(args);

        String argsParam1 = args[0];
        if(argsParam1 != null) {
            // schalter x mit direktem programm argument übersteuern
            schalterx = argsParam1;
            System.out.printf("Environment Schalter X wurde durch arg übersteuert [%s]\n", schalterx);
        }

        PaymentStrategy strategy = null;

        // mit dem Trim wieder auf NULLPOINTER aufpassen, sonst prüfen,
        if( "paypal".equalsIgnoreCase(schalterx.trim()) ) {
            strategy = new PayPalPayment();
        } else {
            // oder wechseln .. ist default wenn kein Parameter da ist
            strategy = new CreditCardPayment();
        }
        // besser hier EINMAL zentral das Service anlegen
        // Die Variable definiert nur die Payment Strategie
        CheckoutService checkout = new CheckoutService(strategy);
        return checkout;
    }

    private static String getStrVonEnvVariable(String schalterx) {
        String strVonEnvVariable = System.getenv(SCHALTERX);
        System.out.printf("Environment Schalter X ist [%s]\n", schalterx);
        return strVonEnvVariable;
    }

    private static void debugArgs(String[] args) {
        System.out.println("args lenght is " + args.length);
        for (int i = 0; i < args.length; i++) {
            System.out.printf("args[%d] = [%s]%n", i, args[i]);
        }
    }

}
