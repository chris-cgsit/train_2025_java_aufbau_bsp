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
 * Generic container.
 *
 * @param <T> type of value stored
 */
public class Box<T> {

    private final T value;

    /**
     * Creates a new box.
     *
     * @param value value to store
     */
    public Box(T value) {
        this.value = value;
    }

    /**
     * @return stored value
     */
    public T getValue() {
        return value;
    }
}
