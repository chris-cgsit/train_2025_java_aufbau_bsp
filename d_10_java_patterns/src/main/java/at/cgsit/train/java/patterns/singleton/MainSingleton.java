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

public class MainSingleton {

    static void main() {

        // durch den private Konstruktur ist die Instanzierung hier unmöglicgh
        // Config newConfig = new Config();

        // aber vie static factory Methode kann das singleton Objekt erhalten werden
        Config cfg = Config.getInstance();

        String mykey = cfg.getConfigValue("mykey");
        System.out.println("config objekt verwendet");

    }

}
