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

package at.cgsit.train.java.javadoc;

/**
 * Legacy service.
 */
public class LegacyService {

    /**
     * Old API method.
     *
     * @deprecated Use {@link #newMethod()} instead.
     */
    @Deprecated(since = "2.0", forRemoval = true)
    public void oldMethod() {
        // legacy logic
    }

    /**
     * New replacement method.
     * @since 2.0.0
     */
    public void newMethod() {
        // new logic
    }
}
