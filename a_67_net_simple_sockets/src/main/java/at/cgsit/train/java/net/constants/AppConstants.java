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

package at.cgsit.train.java.net.constants;


/**
 * zentrale Konstanten für unsere netzwerk tests
 * aternative auch ENUM möglich
 */
public final class AppConstants {
    // Privater Konstruktor verhindert Instanziierung
    private AppConstants() {
        throw new AssertionError("Diese Klasse kann nicht instanziiert werden.");
    }

    public static final int SERVER_PORT_IP = 5544;
    public static final int SERVER_PORT_HTTP = 8080;

    public static final String API_ENDPOINT = "/api/v1";

}
