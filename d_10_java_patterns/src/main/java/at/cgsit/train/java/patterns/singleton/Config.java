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

package at.cgsit.train.java.patterns.singleton;

public class Config {

    private static volatile Config INSTANCE;

    private Config() {
        // private Konstruktor
    }

    public static Config getInstance() {
        if (INSTANCE == null) {
            // wenn hier mehrere threads schon warten darf nur der ERSTE in diesen Block rein
            // und erzeugt die Singleton instanz.
            // andere threads die hier dadurch warten müssen, haben aber schon den step
            // instance == null ausgeführt, gehen also davon aus dass es noch keine instanz gibt.
            // wenn sie dann in diesen Block kommen, wird deshalb nocheinmal die Instance prüfung
            // durdchgeführt. spätestens dann erkennen sie die bereits vorhandene Instanz
            // ab dann zieht immer das erste IF und der synchronized block wird nie wieder
            // benötigt. das ist gut, da das syncronized durch die Synchronisierung ein Performance
            // problem sein könnte.
            // Diese Implementierung findent man in vielen verschiedenen Frameworks ähnlich
            synchronized (Config.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Config();
                }
            }
        }
        return INSTANCE;
    }

    public String getConfigValue( String key) {
        // da gibts noch nix
        return "";
    }

}
