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

package at.cgsit.train.java.logging.hello;

import at.cgsit.train.java.logging.init.InitLogging;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloLogger {

    // STATIC initialize block
    // dieser code wird beim LADEN der Klasse einmal ausgeführt
    // d.h. before die main mehtode her aufgerufen wird
    static {
        // setzte unser local auf englsih, damit auch english initialisiert wird für den logger
        Locale.setDefault(Locale.ENGLISH);

        System.out.println("static plock");
        InitLogging.init();

    }

    private static final Logger LOG = Logger.getLogger(HelloLogger.class.getName());

    public static void main(String[] args) {

        LOG.info("Hello Logging");

        LOG.log(Level.FINE, "my message with level fine");
        LOG.fine("my fine message");

        LOG.warning("This is a warning");

        LOG.severe("This is a severe error");

        // mit logging varablen
        String username = "chris";
        int retryCount = 3;
        boolean enabled = true;

        LOG.log(
                Level.INFO,
                "User login: username={0}, retryCount={1}, enabled={2}",
                new Object[]{username, retryCount, enabled}
        );

        MyService service = new MyService();
        service.doSomething();

    }
}
