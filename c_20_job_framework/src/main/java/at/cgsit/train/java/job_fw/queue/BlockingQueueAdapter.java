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

package at.cgsit.train.java.job_fw.queue;

public interface BlockingQueueAdapter<T> {

    /**
     * Legt ein Element in die Queue. Kann blockieren (Backpressure).
     */
    void put(T item) throws InterruptedException;

    /**
     * Holt ein Element aus der Queue oder gibt null zurück,
     * wenn innerhalb des Zeitfensters nichts verfügbar ist.
     */
    T poll(long timeoutMillis) throws InterruptedException;

    /**
     * Gibt true zurück, wenn die Queue leer ist.
     */
    boolean isEmpty();
}
