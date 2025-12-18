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

import java.io.IOException;

/**
 * Generic container.
 *
 * @param <T> type of value stored
 */
public class Box<T> {

    /**
     * was hat dieser Value für einen fachlichen Zweck..
     */
    private final T value;

    /**
     * Creates a new box.
     *
     * @param value das ist der input value mit dem gerachnet wird
     */
    public Box(T value) {
        this.value = value;
    }

    /**
     * be return values immer gut wenn man beschreibt was sonderfälle sind, null, etc
     * @return stored value .. return beschreibt den rückabe wert
     * @throws RuntimeException wenn das und das schieft geht ...
     */
    public T getValue() {
        return value;
    }

    /**
     * wofür ist diese methdoe da
     *
     * @return Integer als ergebenis
     * @throws IOException wenn das file nicht vorhanden ist
     * @since 2.1.0
     * @deprecated diese methode wird iin der nächster version ersetzt
     */
    public Integer doit() throws IOException {

        return 0;
    }

}
