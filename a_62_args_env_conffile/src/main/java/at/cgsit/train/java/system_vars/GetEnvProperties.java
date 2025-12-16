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

package at.cgsit.train.java.system_vars;

import java.util.Map;

public class GetEnvProperties {

    static void main() {

        String portString = null;
        Integer port = null;
        try {
            // get env mit key liefert spezifischen Wert
            portString = System.getenv("port");
            port = Integer.parseInt(portString);
        } catch (NumberFormatException e) {
            System.out.println("port konnte nicht geparsed werden: " + portString);
            port = 5545;
        }

        String osName = System.getenv("OS");
        // liefert alle Envinroment Varuablen die verfügbar sind
        Map<String, String> envVariablen = System.getenv();
        // iterieren über alle varablen und anzeigen
        // envVariablen.forEach((k, v) -> System.out.println(k + " = " + v));
        // oder mit stream sortiert
        System.getenv().entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(e -> System.out.println(e.getKey() + " = " + e.getValue()));
    }

}
